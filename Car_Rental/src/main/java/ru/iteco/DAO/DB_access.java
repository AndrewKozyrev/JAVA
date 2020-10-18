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

    public String getPrimaryKeyName(String table_name) {
        String key_name = null;
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet primary_key_description = meta.getPrimaryKeys(null, null, table_name);
            primary_key_description.next();
            key_name = primary_key_description.getString("COLUMN_NAME");

        } catch (SQLException sqlException) {
            try {
                var rs = statement.executeQuery("SELECT * FROM " + table_name);
                var rsm = rs.getMetaData();
                key_name = rsm.getColumnName(1);

            } catch (Exception exception) {
                System.out.println("Andrew, check function getPrimaryKey");
                exception.printStackTrace();
                System.out.println(exception);
                System.exit(14);
            }
        }



        return key_name;
    }

    public Map<String, ArrayList<String>> SELECT(String SQL) {
        Map<String, ArrayList<String>> map = null;

        try {
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

    public String INSERT(String SQL) {

        /*
        * Building insert statement to insert into table_name key:value
        * pairs in data variable
        * */

        String generatedKey = null;
        ResultSet resultSet = null;
        try {
            statement.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedKey = resultSet.getObject(1).toString();
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.exit(13);

        } catch (Exception exception) {
            exception.printStackTrace();
        }



        return generatedKey;
    }
}
