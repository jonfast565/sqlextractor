package main.java.com.jonfast565.sqlextractor;

public class JoinClause {
    private String tableName;
    private String tableAlias;
    private String optionalSchemaName;
    private String optionalDatabaseName;

    public JoinClause(String tableName, String tableAlias, String optionalSchemaName, String optionalDatabaseName) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.optionalSchemaName = optionalSchemaName;
        this.optionalDatabaseName = optionalDatabaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getOptionalSchemaName() {
        return optionalSchemaName;
    }

    public void setOptionalSchemaName(String optionalSchemaName) {
        this.optionalSchemaName = optionalSchemaName;
    }

    public String getOptionalDatabaseName() {
        return optionalDatabaseName;
    }

    public void setOptionalDatabaseName(String optionalDatabaseName) {
        this.optionalDatabaseName = optionalDatabaseName;
    }
}
