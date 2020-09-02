package main.java.com.jonfast565.sqlextractor;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Utilities {
    public static String formatId(String id) {
        return id.replace("[", "").replace("]", "");
    }

    public static List<ParseTree> findRecursiveParseTree(ParseTree parent, Function<ParseTree, Boolean> matcher, List<ParseTree> exprList) {
        if (exprList == null) {
            exprList = new LinkedList<ParseTree>();
        }
        var childrenCount = parent.getChildCount();
        var newList = new LinkedList<ParseTree>();
        for (int i = 0; i < childrenCount; i++) {
            var child = parent.getChild(i);
            if (matcher.apply(child)) {
                newList.addAll(findRecursiveParseTree(child, matcher, exprList));
                newList.add(child);
            } else {
                newList.addAll(findRecursiveParseTree(child, matcher, exprList));
            }
        }
        return newList;
    }
}
