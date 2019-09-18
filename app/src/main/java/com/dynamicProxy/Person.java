package com.dynamicProxy;

public class Person implements IAnimal{

    @Override
    public void eat() {
        System.out.println("人在吃饭！");
    }

    @Override
    public void sleep() {
        System.out.println("人在睡觉！");

    }
}
