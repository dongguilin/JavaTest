package com.guilin.java6.designpattern.builder.s1;

import java.util.List;

/**
 * Created by T57 on 2016/10/13 0013.
 * 奔驰车组装者
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel model = new BenzModel();

    @Override
    public void setSequence(List<String> list) {
        this.model.setSequence(list);
    }

    @Override
    public CarModel getCarModel() {
        return model;
    }
}
