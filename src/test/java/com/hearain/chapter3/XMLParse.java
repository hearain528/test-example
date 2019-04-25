package com.hearain.chapter3;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: qijun
 * @email: 18353367683@163.com
 * @date: 2019/4/22 0022 18:42
 * @version: 1.1.0
 * @description:
 */
public class XMLParse {

    public void parseXml(String xmlpath) throws Exception{
        File file = new File(xmlpath);
        SAXReader saxReader = new SAXReader();
        Document read = saxReader.read(file);
        //获取根节点
        Element rootElement = read.getRootElement();
        Element leaf = rootElement.element("leaf");
//        System.out.println(leaf.elementText("id"));
//        System.out.println(leaf.elementText("text"));
        List<ChildNode> childNodeList = new ArrayList<>();
        getChildNodes(leaf, new ChildNode(), childNodeList);
//        System.out.println(childNodeList);
        for (ChildNode childNode : childNodeList) {
            System.out.println(childNode);
        }
//        Element childNodes = leaf.element("childNodes");
//        if(childNodes != null){
//            List<Element> childLeaf = childNodes.elements("leaf");
//            for(Element element : childLeaf){
//                System.out.println(element.elementText("id"));
//                System.out.println(element.elementText("text"));
//            }
//        }
    }

    public void getChildNodes(Element leaf, ChildNode childNode, List<ChildNode> nodeList) throws Exception{
        childNode.setId(leaf.elementText("id"));
        childNode.setText(leaf.elementText("text"));
        nodeList.add(childNode);
        Element childNodes = leaf.element("childNodes");
        if(childNodes != null){
            List<Element> childLeaf = childNodes.elements("leaf");
            for(Element element : childLeaf){
                childNode = new ChildNode();
                childNode.setParentId(leaf.elementText("id"));
                getChildNodes(element, childNode, nodeList);
            }
        }else{
            return;
        }
    }

}
