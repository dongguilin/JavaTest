package com.guilin.java6.designpattern.builder.s2;

/**
 * Created by T57 on 2016/10/13 0013.
 * 具体建造者
 */
public class ConcreteProduct extends Builder {

    private Product product = new Product();

    @Override
    public void setPart() {
        //产品内的逻辑处理
    }

    @Override
    public Product buildProduct() {
        return product;
    }
}
