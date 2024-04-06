package org.ocado.basket;

import java.util.*;

public class BasketSplitter {
    private String absolutePathToConfigFile;
    private List<String> productsNames;
    private JsonBasketReader jsonFileReader;
    private SplitterActions splitterActions;

    /* ... */
    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile = absolutePathToConfigFile;
        this.jsonFileReader = new JsonBasketReader();
        this.productsNames = new ArrayList<>();
        this.splitterActions = new SplitterActions();
    }

    public Map<String, List<String>> split(List<String> items) {
        Map<String, List<String>> deliveryWayAndProductsDeliverdByIt = new HashMap<>();
        Map<String, Set<String>> productsAndDeliveryWays = new HashMap<>(jsonFileReader.readDeliveryWaysForProducts(items, absolutePathToConfigFile));
        Map<String, Integer> quantityOfDeliveryWays = new HashMap<>();
        Set<String> productsPossibleToDeliveryByOneWay = new HashSet<>();
        String mostRepeatableDeliveryWay = null;
        while (!productsAndDeliveryWays.isEmpty()) {
            quantityOfDeliveryWays = splitterActions.takeQuantityOfDeliveryWays(productsAndDeliveryWays);
            mostRepeatableDeliveryWay = splitterActions.takeMostRepeatedDeliveryWay(quantityOfDeliveryWays);
            productsPossibleToDeliveryByOneWay = splitterActions.takeSetOfProductPossibleToDeliverByOneWay(productsAndDeliveryWays, mostRepeatableDeliveryWay);
            if (!productsPossibleToDeliveryByOneWay.isEmpty()) {
                List<String> productsList = new ArrayList<>(productsPossibleToDeliveryByOneWay);
                deliveryWayAndProductsDeliverdByIt.put(mostRepeatableDeliveryWay, productsList);
            }
            productsAndDeliveryWays = splitterActions.removeProductsBySet(productsAndDeliveryWays, productsPossibleToDeliveryByOneWay);
        }
        return deliveryWayAndProductsDeliverdByIt;
    }
}
