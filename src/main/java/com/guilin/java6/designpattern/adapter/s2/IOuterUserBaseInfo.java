package com.guilin.java6.designpattern.adapter.s2;

import java.util.Map;

/**
 * Created by T57 on 2016/9/12 0012.
 * 用户基本信息接口
 */
public interface IOuterUserBaseInfo<K, V> {

    Map<K, V> getUserBaseInfo();
}
