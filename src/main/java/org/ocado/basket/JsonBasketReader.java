/**
 * @file JsonBasketReader.java
 * @brief This file contains the definition of the JsonBasketReader class.
 */
package org.ocado.basket;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * @class JsonBasketReader
 * @brief The JsonBasketReader class is responsible for reading basket information from JSON files.
 */
public class JsonBasketReader {
    /**
     * @brief Reads a list of product names from a JSON file.
     * @param fileName The name of the JSON file.
     * @return A list of product names.
     */
    public List<String> readListOfProduct(String fileName) {
        List<String> productsList = new ArrayList<>();
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = new JSONArray(jsonContent);
        for (int i = 0; i < jsonArray.length(); i++) {
            String product = jsonArray.getString(i);
            productsList.add(product);
        }
        return productsList;
    }
    /**
     * @brief Reads delivery options for products from a JSON file.
     * @param productsName The list of product names.
     * @param absolutePathToConfigFile The absolute path to the JSON configuration file.
     * @return A map containing product names as keys and sets of delivery options as values.
     */
    public Map<String, Set<String>> readDeliveryWaysForProducts(List<String> productsName, String absolutePathToConfigFile) {
        Map<String, Set<String>> productsAndDeliveryWays = new HashMap<>();
        Set<String> setOfProducts = new HashSet<>(productsName);
        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get(absolutePathToConfigFile)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = new JSONObject(jsonContent);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String productName = keys.next();
            if (setOfProducts.contains(productName)) {
                Set<String> deliveryOptions = new HashSet<>();
                JSONArray optionsArray = jsonObject.getJSONArray(productName);
                for (int i = 0; i < optionsArray.length(); i++) {
                    deliveryOptions.add(optionsArray.getString(i));
                }
                productsAndDeliveryWays.put(productName, deliveryOptions);
            }
        }
        return productsAndDeliveryWays;
    }
}
