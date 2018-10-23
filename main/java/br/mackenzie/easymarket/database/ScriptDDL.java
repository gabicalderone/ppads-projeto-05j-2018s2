package br.mackenzie.easymarket.database;

public class ScriptDDL {

    public static String getCreateTableClient(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS clients ( ");
        sql.append("Name     STRING (64)   NOT NULL,");
        sql.append("Email    STRING (300)  NOT NULL,");
        sql.append("Phone    STRING (11)   NOT NULL,");
        sql.append("User     TEXT (30)     NOT NULL,");
        sql.append("Password VARCHAR (200) NOT NULL )");
        return(sql.toString());
    }

    public static String getCreateTableProducts(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS products (");
        sql.append("name TEXT (64)   NOT NULL,");
        sql.append("tipo STRING (64) NOT NULL)");
        return(sql.toString());
    }
}
