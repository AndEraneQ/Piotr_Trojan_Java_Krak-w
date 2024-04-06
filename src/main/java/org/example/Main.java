package org.example;

import org.ocado.basket.BasketSplitter;
import org.ocado.basket.JsonBasketReader;
import org.ocado.basket.SplitterActions;

import java.util.HashMap;
import java.util.List;
import  java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        //BasketSplitter basketSplitter = new BasketSplitter();
        JsonBasketReader jsonBasketReader = new JsonBasketReader();
        List<String> a = jsonBasketReader.readListOfProduct("src/main/java/org/ocado/basket/jsonFiles/basket-1.json");
        for(int i = 0; i< a.size();i++){
            //System.out.println(a.get(i));
        }
        Map<String, Set<String>> b = jsonBasketReader.readDeliveryWaysForProducts(a,"src/main/java/org/ocado/basket/jsonFiles/config.json");
        for (Map.Entry<String, Set<String>> entry : b.entrySet()) {
            System.out.print(entry.getKey());
            Set <String> values = entry.getValue();
            for(String value : values){
                System.out.print(value + ",");
            }
            System.out.println();
        }
        SplitterActions splitterActions = new SplitterActions();
        Map<String,Integer> c = splitterActions.takeQuantityOfDeliveryWays(b);
        for (Map.Entry<String, Integer> entry : c.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        String d = splitterActions.takeMostRepeatedDeliveryWay(c);
        System.out.println(d);
        Map<String, Set<String>> e = splitterActions.removeProductsByDeliveryWay(b,d);
        for (Map.Entry<String, Set<String>> entry : b.entrySet()) {
            System.out.print(entry.getKey());
            Set <String> values = entry.getValue();
            for(String value : values){
                System.out.print(value + ",");
            }
            System.out.println();
        }
    }
}