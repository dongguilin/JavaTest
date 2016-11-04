package com.guilin.java.designpattern.builder.s1;

import java.util.List;

/**
 * Created by T57 on 2016/10/13 0013.
 * 宝马车组装者
 */
public class BMWBuilder extends CarBuilder {

    private BMWModel model = new BMWModel();

    @Override
    public void setSequence(List<String> list) {
        this.model.setSequence(list);
    }

    @Override
    public CarModel getCarModel() {
        return model;
    }
}
