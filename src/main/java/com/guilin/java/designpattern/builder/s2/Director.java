package com.guilin.java.designpattern.builder.s2;

/**
 * Created by T57 on 2016/10/13 0013.
 * 导演类（起到封装的作用，避免高层模块深入到建造者内部的实现类）
 */
public class Director {
    private Builder builder = new ConcreteProduct();

    public Product getAProduct() {
        builder.setPart();

        //设置不同的零件，产生不同的产品
        return builder.buildProduct();
    }
}
