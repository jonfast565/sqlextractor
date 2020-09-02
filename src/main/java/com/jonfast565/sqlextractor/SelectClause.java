package main.java.com.jonfast565.sqlextractor;

public class SelectClause {

    private String tableName;
    private String columnName;
    private String columnAlias;

    public SelectClause(String tableName, String columnName, String columnAlias) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }

    public void setColumnAlias(String columnAlias) {
        this.columnAlias = columnAlias;
    }
}
