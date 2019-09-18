package com.dynamicProxy;

public class Test {

    public static void main(String[] arg0){

        DynamicHlper dynamicHlper = new DynamicHlper();
        SellWine sellWine = (SellWine) dynamicHlper.newProxyInstance(new MaoTai());
        sellWine.maiJiu();


        IAnimal animal = (IAnimal) dynamicHlper.newProxyInstance(new Person());
        animal.eat();
        animal.sleep();
    }


}
