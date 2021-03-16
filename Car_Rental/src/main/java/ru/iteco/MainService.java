package ru.iteco;

import java.util.Map;

public class MainService {
    public static void main(String[] args) {
        Client client = new Client();
        Worker worker = new Worker();
        Map<String, String> product_description = client.getMenu();
        int car_id = client.chooseVehicle(product_description);
        Deal deal = new Deal(client, client);
        client.makeOrder(car_id, 30, deal);
        worker.calculateCost(deal);
        worker.registerOrder(deal);
        worker.checkOrder(deal);
    }
}
