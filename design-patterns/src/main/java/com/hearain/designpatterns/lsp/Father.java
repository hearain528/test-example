package com.hearain.designpatterns.lsp;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/9/30 0030 16:14
 * @version: 1.1.0
 * @description:
 */
public class Father {

    public Collection say(Map hashMap){
        System.out.println("父类被执行...");
        return hashMap.values();
    }

}
