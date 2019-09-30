package com.hearain.designpatterns.lsp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/9/30 0030 16:16
 * @version: 1.1.0
 * @description:
 */
public class Home {

    public static void main(String[] args) {
        invoke();
    }

    public static void invoke(){
        Father f = new Father();
        Father s = new Son();
        HashMap hashMap = new HashMap();
        f.say(hashMap);
        s.say(hashMap);
    }
}
