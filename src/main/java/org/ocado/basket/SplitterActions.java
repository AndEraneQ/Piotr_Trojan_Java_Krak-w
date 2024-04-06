/**
 * @file SplitterActions.java
 * @brief This file contains the definition of the SplitterActions class.
 */
package org.ocado.basket;

import java.util.*;
/**
 * @class SplitterActions
 * @brief The SplitterActions class contains methods for performing various actions related to splitting baskets.
 */
public class SplitterActions {
    /**
     * @brief Computes the quantity of delivery ways for each product.
     * @param namesOfProductsAndDeliveryWays A map containing product names as keys and sets of delivery ways as values.
     * @return A map containing delivery ways as keys and the quantity of each delivery way as values.
     */
    public Map<String, Integer> takeQuantityOfDeliveryWays(Map<String, Set<String>> namesOfProductsAndDeliveryWays) {
        Map<String, Integer> quantityOfDeliveryWays = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : namesOfProductsAndDeliveryWays.entrySet()) {
            entry.getValue().forEach(deliveryWay -> {
                quantityOfDeliveryWays.merge(deliveryWay, 1, Integer::sum);
            });
        }
        return quantityOfDeliveryWays;
    }
    /**
     * @brief Finds the most repeated delivery way.
     * @param quantityOfDeliveryWays A map containing delivery ways as keys and their quantities as values.
     * @return The most repeated delivery way.
     */
    public String takeMostRepeatedDeliveryWay(Map<String, Integer> quantityOfDeliveryWays) {
        Integer max = Integer.MIN_VALUE;
        String mostPopularDeliveryOption = null;
        for (Map.Entry<String, Integer> entry : quantityOfDeliveryWays.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostPopularDeliveryOption = entry.getKey();
            }
        }
        return mostPopularDeliveryOption;
    }
    /**
     * @brief Removes products by a given set of product names.
     * @param namesOfProductsAndDeliveryWays A map containing product names as keys and sets of delivery ways as values.
     * @param productsToRemove A set of product names to remove.
     * @return The updated map after removing the specified products.
     */
    public Map<String, Set<String>> removeProductsBySet(Map<String, Set<String>> namesOfProductsAndDeliveryWays, Set<String> productsToRemove) {
        for (Iterator<Map.Entry<String, Set<String>>> iterator = namesOfProductsAndDeliveryWays.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            if (productsToRemove.contains(entry.getKey())) {
                iterator.remove();
            }
        }
        return namesOfProductsAndDeliveryWays;
    }
    /**
     * @brief Finds the set of products that can be delivered by a given delivery way.
     * @param namesOfProductsAndDeliveryWays A map containing product names as keys and sets of delivery ways as values.
     * @param deliveryWay The delivery way to consider.
     * @return The set of products that can be delivered by the specified delivery way.
     */
    public Set<String> takeSetOfProductPossibleToDeliverByOneWay(Map<String, Set<String>> namesOfProductsAndDeliveryWays, String deliveryWay) {
        Set<String> setOfProducts = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : namesOfProductsAndDeliveryWays.entrySet()) {
            Set<String> deliveryWays = entry.getValue();
            if (deliveryWays.contains(deliveryWay)) {
                setOfProducts.add(entry.getKey());
            }
        }
        return setOfProducts;
    }
}
