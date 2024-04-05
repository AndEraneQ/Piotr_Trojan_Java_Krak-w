package org.ocado.basket;
import java.util.*;

public class BasketSplitter {
    private String absolutePathToConfigFile;
    private List<String> productsNames;
    private JsonBasketReader jsonFileReader;
    /* ... */
    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile=absolutePathToConfigFile;
        this.jsonFileReader = new JsonBasketReader();
        this.productsNames = new ArrayList<>();
    }
    public Map<String, List<String>> split(List<String> items) {
        Map<String, Set<String>> productsAndDeliveryWays = new HashMap<>();
        Map<String,Integer> deliveryWaysAndQuantity = new HashMap<>();
        return null;
    }
}
