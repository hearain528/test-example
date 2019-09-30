package com.hearain.designpatterns.lsp;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/9/30 0030 16:07
 * @version: 1.1.0
 * @description:
 */
public class Tutu {

    /**
     * 定义要旅游的景点
     * @date 2019/9/30 0030 16:07
     */
    private ViewPoint viewPoint;

    public void setViewPoint(ViewPoint viewPoint){
        this.viewPoint = viewPoint;
    }

    public void travelTo(){
        System.out.println("涂涂要去旅游了");
        viewPoint.where();
    }

}
