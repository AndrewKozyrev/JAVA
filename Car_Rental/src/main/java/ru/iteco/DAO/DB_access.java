package ru.iteco.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB_access {

    private Connection connection;
    private Statement statement;
    private String url = "jdbc:mysql://localhost/carrental?serverTimezone=Europe/Moscow";
    private String user = "root";
    private String password = "b2e3t8a2";

    public DB_access() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(2);
        }

        try {
            statement = connection.createStatement(
                                            ResultSet.TYPE_SCROLL_SENSITIVE,
                                            ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(3);
        }
    }

    public Map<String, String> getData_PK(String table_name, int key) {
        Map<String, String> map = null;
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet primary_key_description = meta.getPrimaryKeys(null, null, table_name);
            primary_key_description.next();
            String key_name = primary_key_description.getString("COLUMN_NAME");
            String SQL = "SELECT * FROM " + table_name + " WHERE " + key_name + " = " + key;

            ResultSet resultSet = statement.executeQuery(SQL);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int count = rsMetaData.getColumnCount();
            map = new HashMap<>();
            resultSet.next();
            for (int i = 1; i <= count; i++) {
                String columnName = rsMetaData.getColumnName(i);
                String value = resultSet.getObject(i).toString();
                map.put(columnName, value);
            }
        } catch (Exception exception) {
            System.out.println("Andrew, check function getData_PK");
            exception.printStackTrace();
            System.out.println(exception);
        }

        return map;
    }

    public Map<String, ArrayList<String>> getData(String table_name) {
        Map<String, ArrayList<String>> map = null;

        try {
            String SQL = "SELECT * FROM " + table_name;
            var resultSet = statement.executeQuery(SQL);
            var resultSetMetaData = resultSet.getMetaData();
            int col_count = resultSetMetaData.getColumnCount();
            map = new HashMap<>();

            int row_count = resultSet.last() ? resultSet.getRow() : 0;
            resultSet.beforeFirst();

            for (int i = 1; i <= col_count; i++) {
                String column_name = resultSetMetaData.getColumnName(i);
                ArrayList<String> values = new ArrayList<>(row_count);
                while (resultSet.next()) {
                    values.add(resultSet.getObject(i).toString());
                }
                resultSet.beforeFirst();
                map.put(column_name, values);
            }

        } catch (SQLException exception) {
            System.out.println("Andrew, check function getData");
            exception.printStackTrace();
            System.out.println(exception);
        }

        return map;
    }

    public Object getData_query(String SQL) {
        Object result = null;

        try {
            var resultSet = statement.executeQuery(SQL);
            resultSet.next();
            result = resultSet.getObject(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
