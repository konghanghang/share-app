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
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 将XML数据转换成map数据
     * @param xmlBuffer
     * @return
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */
    public static Map<String, String> xml2Map(StringBuffer xmlBuffer) throws UnsupportedEncodingException, DocumentException {
        return xml2Map(new String(xmlBuffer.toString().getBytes(), "utf-8"));
    }

    public static Map<String, String> xml2Map(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        return xml2Map(document);

    }

    public static Map<String, String> xml2Map(Document xmlDoc){
        Map<String, String> map = new HashMap<String, String>();
        Element root = xmlDoc.getRootElement();
        List<Element> elementList = root.elements();
        for (Element e : elementList)
            map.put(e.getName(), e.getText());
        return map;
    }

    public static Map<String,String> xml2map(InputStream inputStream) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<>(16);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for(Element e:list){
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        return map;
    }

    /**
     * xml转json对象
     * @param xmlBuffer
     * @return
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */
    public static JSONObject xml2Json(StringBuffer xmlBuffer) throws UnsupportedEncodingException, DocumentException {
        return xml2Json(new String(xmlBuffer.toString().getBytes(), "utf-8"));
    }

    public static JSONObject xml2Json(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        return xml2Json(document);
    }

    public static JSONObject xml2Json(Document xmlDoc) {
        JSONObject jsonObject = new JSONObject();
        Element root = xmlDoc.getRootElement();
        List<Element> elementList = root.elements();
        for (Element element : elementList){
            jsonObject.put(element.getName(), element.getText());
        }

        return jsonObject;
    }


}
