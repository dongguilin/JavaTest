package com.guilin.java.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 */
public class TestMethodReference {

    @Test
    public void test1() {
        // 第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
        Car car = Car.create(Car::new);

        // 第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
        List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        // Error
        //cars.forEach(Car::collide2);

        // 第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
        cars.forEach(Car::repair);
        // Error
        //cars.forEach(Car::repair2);

        // 第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);

    }

    private static class Car {
        public static Car create(Supplier<Car> supplier) {
            return supplier.get();
        }

        public static void collide(Car car) {
            System.out.println("Collided " + car.toString());
        }

        public static void collide2() {
            System.out.println("Collided2");
        }

        public void follow(Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        public void repair2(Car car) {
            System.out.println("Repaired2 " + car.toString());
        }
    }
}
