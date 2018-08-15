package com.ysla.api.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysla.utils.exception.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * see -http://blog.csdn.net/wangpeng047/article/details/19624529
 * see -http://blog.csdn.net/leeo1010/article/details/41801509
 * see -http://blog.51cto.com/linhongyu/1538672
 * see -http://www.yeetrack.com/?p=773
 * @author konghang 
 */
@Slf4j
public class HttpClientUtils {

    /**
     * 连接超时时间，默认10秒(数据传输过程中数据包之间间隔的最大时间)
     */
    private static final int SOCKET_TIMEOUT = 10000;
    /**
     * 传输超时时间，默认30秒(连接建立时间，三次握手完成时间)
     */
    private static final int CONNECT_TIMEOUT = 30000;
    /**
     * 从连接池获取连接的超时时间，可以想象下数据库连接池
     */
    private static final int CONNECTION_REQUEST_TIMEOUT = 10000;


    /**
     * @desciption 发送get请求
     * @param url
     * @return
     */
    public static JSONObject httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();
        httpGet.setConfig(requestConfig);
        //如果不设置user-agent,用默认值User-Agent: Apache-HttpClient/4.5.2 (Java/1.8.0_144)可能会导致访问不成功。
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36");
        try(CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return responseHandle(response);
        } catch (IOException e) {
            log.error(ExceptionUtils.getStackMsg(e));
        }
        return null;
    }

    /**
     * @description post请求发送,请求参数为formData
     * @param url
     * @param para
     * @return
     */
    public static JSONObject httpPostForm(String url, JSONObject para) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36");
        CloseableHttpResponse response = null;
        //拼接参数
        List<NameValuePair> list = new ArrayList<>();
        for(Map.Entry<String, Object> entry: para.entrySet()){
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpClient.execute(httpPost);
            return responseHandle(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @description 发送post请求,请求参数为json字符串
     * @param url
     * @param postData
     * @return
     */
    public static JSONObject httpPostJson(String url, String postData) {
        return httpPostJson(url, postData, "application/json");
    }

    /**
     * @description 发送post请求,请求参数为json字符串
     * @param url 请求的url
     * @param postData 请求的数据,json字符串
     * @param contentType 可以取值application/json,text/xml等
     * @return
     */
    public static JSONObject httpPostJson(String url, String postData, String contentType) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            StringEntity entity = new StringEntity(postData,"UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType(contentType);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            return responseHandle(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建SSLClient,信任所有证书
     * @return
     */
    public static CloseableHttpClient createSSLClient(){
        try {
            try {
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    //信任所有证书
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                }).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description 创建SSLClient,需要加载证书
     * @param certificatePath
     * @param certificatePassword
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient createSSLClient(String certificatePath, String certificatePassword){
        return createSSLClient(certificatePath, certificatePassword, KeyStore.getDefaultType());
    }

    /**
     * @description 创建SSLClient,需要加载证书
     * @param certificatePath 证书存放的路径
     * @param certificatePassword 证书的密码
     * @param keyStoreType keystore的类型(PKCS12等)
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient createSSLClient(String certificatePath, String certificatePassword, String keyStoreType){
        //证书存放位置通过request拿到web-inf的位置
        /*String path = request.getSession().getServletContext().getRealPath("/");*/
        try {
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            FileInputStream instream = new FileInputStream(new File(certificatePath));
            try {
                //加载证书和密码
                keyStore.load(instream,certificatePassword.toCharArray());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } finally {
                instream.close();
            }
            // 相信自己的CA和所有自签名的证书
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).build();
            /*SSLContext sslcontext = org.apache.http.conn.ssl.SSLContexts.custom()
                    .loadKeyMaterial(keyStore, certificatePassword.toCharArray()).build();*/
            // 只允许使用TLSv1协议
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description 统一处理httpclient请求返回的数据,转为JSONObject
     * @param response
     * @return JSONObject
     * @throws IOException
     */
    public static JSONObject responseHandle(CloseableHttpResponse response) throws IOException {
        JSONObject jsonObject = null;
        //if(response.getStatusLine().getStatusCode() == 200){
        try{
            HttpEntity httpEntity = response.getEntity();
            if(httpEntity != null){
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                jsonObject = JSON.parseObject(result);
                //消耗掉response
                EntityUtils.consume(httpEntity);
            }
        }finally {
            response.close();
        }
        return jsonObject;
    }

    /**
     * 上传文件
     * MultipartEntityBuilder 默认contentType为 multipart_form_data
     * @param url 上传地址
     * @param path 要上传的文件路径
     */
    public static JSONObject postFile(String url, String path,String token) {
        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httppost = new HttpPost(url);
            File file = new File(path);
            //FileBody bin = new FileBody(file);
            //StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    //.addPart("file", bin)
                    .setCharset(Charset.forName("UTF-8"))
                    //.setContentType(ContentType.MULTIPART_FORM_DATA)
                    .addBinaryBody("file", file)
                    .addTextBody("token", token)
                    .addTextBody("filename", file.getName());
            httppost.setEntity(builder.build());
            CloseableHttpResponse response = httpclient.execute(httppost);
            JSONObject jsonObject = responseHandle(response);
            return jsonObject;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 七牛云 post Base64File
     * @param url
     * @param base64File
     * @param token
     * @return
     */
    public static JSONObject postBase64File(String url, String base64File,String token) {
        try(CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Authorization","UpToken " + token);
            httpPost.addHeader("Content-Type", "application/octet-stream");
            StringEntity entity = new StringEntity(base64File,"UTF-8");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            JSONObject jsonObject = responseHandle(response);
            return jsonObject;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
