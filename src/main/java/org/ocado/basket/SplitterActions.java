package org.ocado.basket;

import java.util.*;

public class SplitterActions {
    public Map<String, Integer> takeQuantityOfDeliveryWays(Map<String, Set<String>> namesOfProductsAndDeliveryWays) {
        Map<String, Integer> quantityOfDeliveryWays = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : namesOfProductsAndDeliveryWays.entrySet()) {
            entry.getValue().forEach(deliveryWay -> {
                quantityOfDeliveryWays.merge(deliveryWay, 1, Integer::sum);
            });
        }
        return quantityOfDeliveryWays;
    }

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

    public Map<String, Set<String>> removeProductsBySet(Map<String, Set<String>> namesOfProductsAndDeliveryWays, Set<String> productsToRemove) {
        for (Iterator<Map.Entry<String, Set<String>>> iterator = namesOfProductsAndDeliveryWays.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            if (productsToRemove.contains(entry.getKey())) {
                iterator.remove();
            }
        }
        return namesOfProductsAndDeliveryWays;
    }

    public Set<String> takeSetOfProductPossibleToDeliverByOneWay(Map<String, Set<String>> namesOfProductsAndDeliveryWays, String DeliveryWay) {
        Set<String> setOfProducts = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : namesOfProductsAndDeliveryWays.entrySet()) {
            Set<String> deliveryWays = entry.getValue();
            if (deliveryWays.contains(DeliveryWay)) {
                setOfProducts.add(entry.getKey());
            }
        }
        return setOfProducts;
    }
}
