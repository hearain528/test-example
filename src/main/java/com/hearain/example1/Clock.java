package com.hearain.example1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 14:36
 * @version: 1.1.0
 * @description:
 */
public abstract class Clock {

    public int UTC_OFFSET;

    protected int localTime;



    public Clock(int utc_offset){
        UTC_OFFSET = utc_offset;
    }

    public abstract void setLocalTime(int localTime);

    public void setLocalTimeFromUtcZeroTime(int utcZeroTime){
        this.localTime = makeHourWithIn0To23(utcZeroTime + this.UTC_OFFSET);
    }

    private static int makeHourWithIn0To23(int hour){
        return (24 + hour) % 24;
    }


    public int getLocalTime() {
        return localTime;
    }
}
