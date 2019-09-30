package com.hearain.designpatterns.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/9/30 0030 16:15
 * @version: 1.1.0
 * @description:
 */
public class Son extends Father {

    public Collection say(HashMap map) {
        System.out.println("子类被执行...");
        return map.values();
    }

//    @Override
//    public Collection say(Map hashMap) {
//        System.out.println("子类Override被执行...");
//        return hashMap.values();
//    }
}
