package main.java.com.jonfast565.sqlextractor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class QueryVisitor extends TSqlParserBaseVisitor<Object> {

    private SelectPortion selectPortion;
    private JoinPortion joinPortion;

    public QueryVisitor() {
        this.selectPortion = new SelectPortion();
        this.joinPortion = new JoinPortion();
    }

    @Override
    public List<SelectClause> visitSelect_list_elem(TSqlParser.Select_list_elemContext ctx) {
        var column_element = ctx.column_elem();
        var expression_element = ctx.expression_elem();

        if (column_element != null) {
            var table_name = column_element.table_name().getText();
            var column_name = column_element.column_name.getText();

            var alias = column_element.as_column_alias();
            if (alias != null) {
                var col_alias = alias.column_alias();
                var simple_id = col_alias.id().getText();
                selectPortion.appendClauseList(new SelectClause(table_name, column_name, simple_id));
            } else {
                selectPortion.appendClauseList(new SelectClause(table_name, column_name, null));
            }
        }

        if (expression_element != null) {
            var column_expression = expression_element;
            var column_expression_value = column_expression.expression();
            var alias = expression_element.as_column_alias();

            String aliasName = null;
            if (alias != null) {
                var col_alias = alias.column_alias();
                var simple_id = col_alias.id();
                aliasName = simple_id.getText();
                System.out.println("Select column: " + column_expression_value.getText() + " as " + Utilities.formatId(aliasName));
            } else {
                System.out.println("Select column: " + column_expression_value.getText());
            }

            var idList = Utilities.findRecursiveParseTree(
                    column_expression_value,
                    x -> x.getClass() == TSqlParser.Full_column_nameContext.class,
                    null)
                    .stream()
                    .map(x -> (TSqlParser.Full_column_nameContext) x)
                    .collect(Collectors.toList());

            for (TSqlParser.Full_column_nameContext nc : idList) {
                var tableName = nc.table_name().table.getText();
                var columnName = nc.column_name.getText();
                selectPortion.appendClauseList(new SelectClause(tableName, columnName, Utilities.formatId(aliasName)));
                System.out.println("With column(s): " + tableName + "." + columnName);
            }
        }

        return null;
    }

    @Override
    public JoinClause visitTable_source_item(TSqlParser.Table_source_itemContext ctx) {
        var table_source = ctx.table_name_with_hint();
        var table_alias = ctx.as_table_alias().table_alias().getText();
        var table_name = table_source.table_name().table.getText();
        var database_name = table_source.table_name().database.getText();
        var schema_name = table_source.table_name().schema.getText();
        System.out.println("Table: " + table_name + " as " + Utilities.formatId(table_alias));

        joinPortion.appendClauseList(new JoinClause(table_name, table_alias, schema_name, database_name));
        return null;
    }

    public SelectPortion getSelectPortion() {
        return selectPortion;
    }

    public void setSelectPortion(SelectPortion selectPortion) {
        this.selectPortion = selectPortion;
    }

    public JoinPortion getJoinPortion() {
        return joinPortion;
    }

    public void setJoinPortion(JoinPortion joinPortion) {
        this.joinPortion = joinPortion;
    }
}
