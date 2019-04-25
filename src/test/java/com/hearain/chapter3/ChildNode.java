package com.hearain.chapter3;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/4/22 0022 21:10
 * @version: 1.1.0
 * @description:
 */
public class ChildNode {

    private String id;

    private String text;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ChildNode{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
