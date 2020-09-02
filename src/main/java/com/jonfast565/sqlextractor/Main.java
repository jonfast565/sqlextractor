package main.java.com.jonfast565.sqlextractor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("--- SQL Extractor ---");

        Options options = new Options();
        Option input = new Option("f", "input-file", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new GnuParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String inputFilePath = cmd.getOptionValue("input-file");
            Path path = Paths.get(inputFilePath);
            var results = driveParser(inputFilePath);
            var resultsPath = path.getParent() + File.separator + path.getFileName().toString().split("\\.")[0] + ".json";
            FileWriter f = new FileWriter(resultsPath);
            Gson g = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();
            var json = g.toJson(results);
            f.write(json);
            f.close();
            System.out.println("Done!");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.out.println("Failed :(");
            System.exit(1);
        }
    }

    private static QueryResults driveParser(String inputFilename) throws IOException {
        CharStream s = CharStreams.fromPath(Paths.get(inputFilename));
        CaseChangingCharStream upper = new CaseChangingCharStream(s, true);
        Lexer lexer = new TSqlLexer(upper);
        TSqlParser p = new TSqlParser(new CommonTokenStream(lexer));
        ParseTree pt = p.tsql_file();
        QueryVisitor v = new QueryVisitor();
        v.visit(pt);
        var joins = v.getJoinPortion();
        var selects = v.getSelectPortion();
        var query = new QueryResults(joins, selects);
        return query;
    }
}
