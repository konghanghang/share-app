package com.ysla.utils.xml;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * XML通用处理类
 * @author konghang
 */
public class XmlUtils {

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @Override
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


    /**
     * 将对象数据转换为xml数据
     * @param object
     * @param clazz
     * @return
     */
    public static String object2Xml(Object object, Class<?> clazz) {
        xstream.alias("xml", clazz);
        return xstream.toXML(object);
    }

    /**
     * xml转fastjson对象
     * @param xmlBuffer
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Fastjson(StringBuffer xmlBuffer) throws DocumentException {
        return xml2Fastjson(new String(xmlBuffer.toString().getBytes(StandardCharsets.UTF_8),
                StandardCharsets.UTF_8));
    }

    /**
     * xml字符串转fastjson对象
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Fastjson(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        return xml2Fastjson(document);
    }

    /**
     * document对象转fastjson对象
     * @param document
     * @return
     */
    public static JSONObject xml2Fastjson(Document document) {
        JSONObject jsonObject = new JSONObject();
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        for (Element element : elementList){
            jsonObject.put(element.getName(), element.getText());
        }
        return jsonObject;
    }


}
