package com.guilin.java.designpattern.factory.s1;

/**
 * Created by T57 on 2016/10/29 0029.
 * 具体工厂类
 */
public class ConcreteCreator extends Creator {
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
        }
        return (T) product;
    }
}
