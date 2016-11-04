package com.guilin.java.designpattern.factory.s6;

import com.guilin.java.designpattern.factory.s1.ConcreteProduct1;
import com.guilin.java.designpattern.factory.s1.ConcreteProduct2;
import com.guilin.java.designpattern.factory.s1.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T57 on 2016/10/31 0031.
 * 延迟加载的工厂类
 */
public class ProductFactory {

    private static final Map<String, Product> prMap = new HashMap<>();

    public static synchronized Product createProduct(String type) throws Exception {
        Product product = null;
        if (prMap.containsKey(type)) {
            product = prMap.get(type);
        } else {
            if (type.equals("product1")) {
                product = new ConcreteProduct1();
            } else {
                product = new ConcreteProduct2();
            }
            prMap.put(type, product);
        }
        return product;
    }
}
