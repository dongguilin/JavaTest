package com.guilin.java6.aleiye;

import com.aleiye.load.analysis.LoadAnalysisDriver;
import org.junit.Test;

/**
 * Created by guilin1 on 15/7/7.
 */
public class AnalysisTest {

    @Test
    public void test1(){
        LoadAnalysisDriver driver = new LoadAnalysisDriver();
        driver.getIdlePoints();//空闲
        driver.getNormalsPoints();//正常
        driver.getLoadAnalysisVal();//负载值
//        List<Double> list
//        boolean isSucc=driver.run();
    }

}
