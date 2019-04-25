package com.hearain.example1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 14:55
 * @version: 1.1.0
 * @description:
 */
public class PhoneClock extends Clock{

    protected UtcTime utcTime;

    public PhoneClock(int utc_offset) {
        super(utc_offset);
    }

    @Override
    public void setLocalTime(int localTime) {
        super.localTime = localTime;
        this.utcTime.setUtcZeroTime(localTime - UTC_OFFSET);
    }

    public UtcTime getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(UtcTime utcTime) {
        this.utcTime = utcTime;
    }


}
