package com.ysla.api.utils.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * url工具类
 * @author konghang
 */
public class UrlUtils {

    /**
     * 将URL参数统一编码成UTF-8
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFormalize(String url) {
        if (!"".equals(url) && null != url) {
            try {
                url = new String(url.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                url = "";
            }
        }
        return url;
    }


    public static String urlEncode(String url) {
        return urlEncode(url, "utf-8");
    }

    public static String urlEncode(String url, String enc) {
        if (!"".equals(url) && null != url) {
            try {
                url = URLEncoder.encode(url, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public static String urlDecode(String url) {
        return urlDecode(url, "utf-8");
    }

    public static String urlDecode(String url, String enc) {
        if (url != null && !"".equals(url)) {
            try {
                return URLDecoder.decode(url, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;

    }

    public static Map<String, String> queryStringToMap(String url) {
        Map<String, String> map = new HashMap<String, String>();
        if (url == null || "".equals(url)) {
            return map;
        }
        try {
            List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
            for (NameValuePair param : params) {
                map.put(param.getName(), param.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static String mapToQueryString(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        return map.entrySet().stream()
                .map(p -> urlEncode(p.getKey()) + "=" + urlEncode(p.getValue()))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");

    }

    public static String removeParams(String url, String[] params) {
        return url;
    }
}
