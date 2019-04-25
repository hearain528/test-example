package com.hearain.example1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2018/9/12 0012 14:35
 * @version: 1.1.0
 * @description:
 */
public abstract class TimeSubject {

    protected Map<String, Clock> clockMap = new HashMap<String, Clock>();

    public void attach(String cityName, Clock clock){
        clockMap.put(cityName, clock);
    }

    public void detach(String cityName){
        clockMap.remove(cityName);
    }

    public abstract void notifyAllClocks();


}
