package com.hearain.example1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 15:05
 * @version: 1.1.0
 * @description:
 */
public class CityClock extends Clock {

    public CityClock(int utc_offset) {
        super(utc_offset);
    }

    @Override
    public void setLocalTime(int localTime) {
        super.localTime = localTime;
    }
}
