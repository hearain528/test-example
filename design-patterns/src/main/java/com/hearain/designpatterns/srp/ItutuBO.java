package com.hearain.designpatterns.srp;

/**
 * 单一职责模式
 * 优点：消除耦合,减小因需求变化引起代码僵化的局面
 * 原则：1.合理的类应该仅有一个引起他变化的原因
 * @author qijun
 * @date 2019/9/30 0030 15:04
 */
public interface ItutuBO {

    void setShengao(double height);

    double getShengao();

    void setTizhong(double weight);

    double getTizhong();


}
