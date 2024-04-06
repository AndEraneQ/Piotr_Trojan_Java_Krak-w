package org.ocado.basket;

import java.util.*;

public class SplitterActions {
    public Map<String,Integer> takeQuantityOfDeliveryWays(Map<String, Set<String>> namesOfProductsAndDeliveryWays){
        Map<String,Integer> quantityOfDeliveryWays = new HashMap<>();
        for(Map.Entry<String,Set<String>> entry : namesOfProductsAndDeliveryWays.entrySet()){
            Set <String> deliveryWays = new HashSet(entry.getValue());
            for(String deliveryWay : deliveryWays){
                quantityOfDeliveryWays.merge(deliveryWay, 1, Integer::sum);
            }
        }
        return quantityOfDeliveryWays;
    }
    public String takeMostRepeatedDeliveryWay(Map<String,Integer> quantityOfDeliveryWays){
        Integer max = 0;
        String mostPopularDeliveryOption = null;
        for(Map.Entry<String, Integer> entry : quantityOfDeliveryWays.entrySet()){
            if(entry.getValue()>max){
                max=entry.getValue();
                mostPopularDeliveryOption = entry.getKey();
            }
        }
        return mostPopularDeliveryOption;
    }
    public Map<String, Set<String>> removeProductsByList(Map<String, Set<String>> namesOfProductsAndDeliveryWays, String DeliveryWay){
        Iterator<Map.Entry<String, Set<String>>> iterator = namesOfProductsAndDeliveryWays.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            Set<String> deliveryWays = entry.getValue();
            if (deliveryWays.contains(DeliveryWay)) {
                iterator.remove();
            }
        }
        return namesOfProductsAndDeliveryWays;
    }
}
