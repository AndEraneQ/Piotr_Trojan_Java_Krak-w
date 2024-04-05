package org.example;

import org.ocado.basket.BasketSplitter;
import org.ocado.basket.JsonBasketReader;

import java.util.HashMap;
import java.util.List;
import  java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //BasketSplitter basketSplitter = new BasketSplitter();
        JsonBasketReader jsonBasketReader = new JsonBasketReader();
        List<String> a = jsonBasketReader.readListOfProduct("src/main/java/org/ocado/basket/jsonFiles/basket-1.json");
        for(int i = 0; i< a.size();i++){
            System.out.println(a.get(i));
        }
        Map<String,List<String>> b = jsonBasketReader.readDeliveryWaysForProducts(a,"src/main/java/org/ocado/basket/jsonFiles/config.json");
        for (Map.Entry<String, List<String>> entry : b.entrySet()) {
            System.out.print(entry.getKey());
            List <String> values = entry.getValue();
            for(String value : values){
                System.out.print(value + ",");
            }
            System.out.println();
        }
    }
}