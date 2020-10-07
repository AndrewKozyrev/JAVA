package ru.iteco.DAO;
import ru.iteco.Model.Car;
import ru.iteco.Model.Customer;
import ru.iteco.Model.Employee;

import java.util.Calendar;
import java.util.Map;

public class CRUD {

    public static DB_access database = new DB_access();

    public static Map<String, String> getData(Object o) {
        return getData(o, 1);
    }

    public static Map<String, String> getData(Object o, int key) {
        Map<String, String> result = null;
        if (o instanceof Customer)
            result = database.getData_PK("customer", key);
        else if (o instanceof Employee)
            result = database.getData_PK("employee", key);
        else if (o instanceof Car)
            result = database.getData_PK("vehicle", key);
        return result;
    }

    public static Object getDataByQuery(String SQL) {
        return database.getData_query(SQL);
    }

    public static boolean setData() {
        return true;
    }

    public static boolean updateData() {
        return true;
    }

    public static boolean deleteData() {
        return true;
    }
}
