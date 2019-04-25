package com.hearain.example1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 15:16
 * @version: 1.1.0
 * @description:
 */
public class ExampleRunner {

    public static void main(String[] args) {
        UtcTime utcTime = new UtcTime();
        utcTime.attach("beijing", new CityClock(8));
        utcTime.attach("london", new CityClock(0));
        utcTime.attach("mescow", new CityClock(4));
        utcTime.attach("sydeny", new CityClock(10));
        utcTime.attach("newYork", new CityClock(-5));
        PhoneClock phoneClock = new PhoneClock(8);
        phoneClock.setUtcTime(utcTime);

        phoneClock.setLocalTime(9);

        utcTime.printUtcTimeOfAllClocks();

    }
}
