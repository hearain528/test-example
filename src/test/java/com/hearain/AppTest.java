package com.hearain;

import com.hearain.chapter3.XMLParse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testXmlParse() throws Exception{
        XMLParse xmlParse = new XMLParse();
        xmlParse.parseXml("F:\\xml-import.xml");
    }

    public void test2(){
        int maxIdx = 0;
        List<String> pathNameList = new ArrayList<>();
        pathNameList.add("nihao");
        pathNameList.add("nihao-123");
        pathNameList.add("nihao(1)");
        pathNameList.add("nihao(4)");
        pathNameList.add("nihao(3)");
        pathNameList.add("nihao(2)");
        pathNameList.add("nihao-123(1)");
        String pathName = "nihao";
        for (String path : pathNameList) {
            if(path.contains(pathName)){
                //判断是否有重名文件存在 有的话文件夹名称后增加(n)
                String regX = "^"+pathName+"(\\([0-9]+\\))?$";
                Pattern pattern = Pattern.compile(regX);
                Matcher matcher = pattern.matcher(path);
                while (matcher.find()){
                    if(pathName.equals(matcher.group(0)) && matcher.group(1) == null){
                        maxIdx = 1;
                    }else{
                        int idx = Integer.parseInt(matcher.group(1).replace("(", "").replace(")",""));
                        if(idx > maxIdx){
                            maxIdx = idx;
                        }
                    }
                }
            }
        }
        pathName = maxIdx > 0 ? pathName + "("+ (++maxIdx) +")" : pathName;
        System.out.println(pathName);
    }
}
