/**
 * @class BasketSplitter
 * @brief The BasketSplitter class is responsible for splitting a basket of items based on delivery options.
 */
package org.ocado.basket;

import java.util.*;
public class BasketSplitter {
    private String absolutePathToConfigFile; // The absolute path to the configuration file.
    private JsonBasketReader jsonFileReader; // The JSON file reader object.
    private SplitterActions splitterActions; // The splitter actions object.
    /**
     * @brief Constructs a new BasketSplitter object.
     * @param absolutePathToConfigFile The absolute path to the configuration file.
     */
    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile = absolutePathToConfigFile;
        this.jsonFileReader = new JsonBasketReader();
        this.splitterActions = new SplitterActions();
    }
    /**
     * @brief Splits the basket of items based on delivery options.
     * @param items The list of items in the basket.
     * @return A map containing delivery options as keys and lists of products as values.
     */
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
