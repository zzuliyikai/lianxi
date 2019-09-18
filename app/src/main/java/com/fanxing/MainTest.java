package com.fanxing;

import java.util.Collection;

public class MainTest {

    public static void main(int[] args){


    }



    public static void test(Collection<? extends Base> collection){


    }

    public static void test1(Collection<? super Sub> collection){

        collection.add(new Sub());
    }

}
