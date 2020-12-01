package ru.iteco.View;

import ru.iteco.DAO.CRUD;

import java.util.Map;

public class CarImpl {
    private Map<String, String> description;

    public CarImpl() {
        this.description = getCar();
    }

    public CarImpl(int car_id) {
        this.description = getCar(car_id);
    }

    public CarImpl(Map<String, String> description) {
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
