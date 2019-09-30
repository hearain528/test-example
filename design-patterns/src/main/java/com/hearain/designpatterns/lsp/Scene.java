package com.hearain.designpatterns.lsp;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/9/30 0030 16:09
 * @version: 1.1.0
 * @description:
 */
public class Scene {

    public static void main(String[] args) {
        Tutu tutu = new Tutu();
        tutu.setViewPoint(new Lijiang());
        tutu.travelTo();
    }

}
