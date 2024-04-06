package org.ocado.basket;
import java.util.*;

public class BasketSplitter {
    private String absolutePathToConfigFile;
    private List<String> productsNames;
    private JsonBasketReader jsonFileReader;
    private SplitterActions splitterActions;
    /* ... */
    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile=absolutePathToConfigFile;
        this.jsonFileReader = new JsonBasketReader();
        this.productsNames = new ArrayList<>();
        this.splitterActions = new SplitterActions();
    }
    public Map<String, List<String>> split(List<String> items) {
        Map<String, Set<String>> productsAndDeliveryWays = new HashMap<>(jsonFileReader.readDeliveryWaysForProducts(items, absolutePathToConfigFile));
        Map<String,Integer> deliveryWaysAndQuantity = new HashMap<>();
        return null;
    }
}
