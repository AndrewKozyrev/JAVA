package ru.iteco.View;

import ru.iteco.DAO.CRUD;

import java.util.Map;

public class RateImpl {
    private int rate_id;
    private char category;
    private int daily;
    private int weekly;
    private int monthly;

    public RateImpl(int rate_id) {
        this.rate_id = rate_id;
        Map<String, String> map = CRUD.getData(this, rate_id);
        this.category = map.get("category").charAt(0);
        this.daily = Integer.parseInt(map.get("daily"));
        this.monthly = Integer.parseInt(map.get("monthly"));
        this.weekly = Integer.parseInt(map.get("weekly"));
    }

    public char getCategory() {
        return category;
    }

    public int getDaily() {
        return daily;
    }

    public int getWeekly() {
        return weekly;
    }

    public int getMonthly() {
        return monthly;
    }
}
