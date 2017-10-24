package com.hearain.chapter1;

/**
*@author hearain
*@date 2017/10/25
*@description 如果想从外部类的非静态方法之外的任意位置创建某个内部类的对象，那么必须像在main方法中那样
*具体指明对象类型：OuterClassName.InnerCLassName
*
*/
public class Outer {

    class Inner{
        private int i = 12;
        public int value(){
            return i;
        }
    }

    public Inner getInner(){
        return new Inner();
    }

    public static void main(String[] args) {
        Outer.Inner inner = new Outer().getInner();
    }

}
