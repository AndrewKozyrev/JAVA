package ru.iteco.DAO;
import ru.iteco.Model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CRUD {

    public static DB_access database = new DB_access();

    public static Map<String, String> getData(Object o) {
        return getData(o, 1);
    }

    public static Map<String, String> getData(Object o, int key) {
        Map<String, String> result = new HashMap<>();
        StringBuilder SQL = new StringBuilder("SELECT * FROM ");
        String table_name = null;
        if (o instanceof Customer)
            table_name = "customer";
        else if (o instanceof Employee)
            table_name = "employee";
        else if (o instanceof Car)
            table_name = "vehicle";
        else if (o instanceof Order)
            table_name = "`order`";
        else if (o instanceof Rate)
            table_name = "rate";

        SQL.append(table_name).append(" WHERE ").append(database.getPrimaryKeyName(table_name)).append(" = ").append(key);
        Map<String, ArrayList<String>> temp = database.SELECT(SQL.toString());
        for (Map.Entry<String, ArrayList<String>> entry : temp.entrySet()) {
            result.put(entry.getKey(), entry.getValue().get(0));
        }
        return result;
    }

    public static Object getDataByQuery(String SQL) {
        return database.SELECT(SQL);
    }

    public static String setData(Object o, Map<String, String> data) {
        StringBuilder SQL = new StringBuilder("INSERT INTO ");
        String table_name = null;
        if (o instanceof Customer)
            table_name = "customer";
        else if (o instanceof Employee)
            table_name = "employee";
        else if (o instanceof Car)
            table_name = "vehicle";
        else if (o instanceof Order)
            table_name = "`order`";
        else if (o instanceof Rate)
            table_name = "rate";

        SQL.append(table_name).append(" (");
        for (String colname : data.keySet()) {
            SQL.append(colname).append(",");
        }
        SQL.deleteCharAt(SQL.length()-1);
        SQL.append(")");

        SQL.append(" VALUES (");
        for (String value : data.values()) {
            SQL.append(value).append(",");
        }
        SQL.deleteCharAt(SQL.length()-1);
        SQL.append(")");

        return database.INSERT(SQL.toString());
    }

    public static boolean updateData() {
        return true;
    }

    public static boolean deleteData() {
        return true;
    }
}
