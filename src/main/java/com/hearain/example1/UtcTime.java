package com.hearain.example1;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 14:42
 * @version: 1.1.0
 * @description:
 */
public class UtcTime extends TimeSubject {

    private int utcZeroTime;

    @Override
    public void notifyAllClocks() {
        for(Clock clock : super.clockMap.values()){
            clock.setLocalTimeFromUtcZeroTime(this.utcZeroTime);
        }
    }

    public int getUtcZeroTime() {
        return utcZeroTime;
    }

    public void setUtcZeroTime(int utcZeroTime) {
        this.utcZeroTime = utcZeroTime;
        notifyAllClocks();
    }

    public void printUtcTimeOfAllClocks(){
        for(String cityName : clockMap.keySet()){
            System.out.println(cityName + ": " + clockMap.get(cityName).getLocalTime());
        }
    }
}
