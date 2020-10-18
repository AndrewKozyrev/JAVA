package ru.iteco.Model;

import ru.iteco.DAO.CRUD;

import java.util.Map;

public class Car {
    private Map<String, String> description;

    public Car() {
        this.description = getCar();
    }

    public Car(int car_id) {
        this.description = getCar(car_id);
    }

    public Car(Map<String, String> description) {
        this.description = description;
    }

    public Map<String, String> getCar() {
        return CRUD.getData(this);
    }

    public Map<String, String> getCar(int car_id) {
        return CRUD.getData(this, car_id);
    }

    public Map<String, String> getDescription() {
        return this.description;
    }

    public char getCategory(int car_id) {
          return description.get("category").charAt(0);
    }
}
