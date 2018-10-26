package com.ysla.web.controller.qiniu;

import com.alibaba.fastjson.JSON;
import com.ysla.api.model.common.JsonApi;
import com.ysla.utils.crypto.Base64Utils;
import com.ysla.utils.crypto.CryptoUtils;
import com.ysla.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/v1/qiniu")
public class QiniuController {

    @Value("${com.ysla.qiniu.ak}")
    private String AK;
    @Value("${com.ysla.qiniu.sk}")
    private String SK;
    @Value("${com.ysla.qiniu.bucket}")
    private String bucket;

    @RequestMapping(value = "/upload/token", method = RequestMethod.GET)
    public JsonApi uploadToken(){
        String token = token(bucket,null,3600l);
        return JsonApi.isOk().data(token);
    }

    private String token(String bucket, String key, Long expires){
        long now = DateUtils.getUnixStamp();
        Long exp = now + expires;
        String scope = bucket;
        if (key != null)
            scope = bucket + ":" + key;
        Map<String,Object> map = new HashMap<>();
        map.put("scope", scope);
        map.put("deadline", exp);

        String putPolicy = JSON.toJSONString(map);
        String encodedPutPolicy = Base64Utils.safeUrlEncode(putPolicy);
        byte[] result = CryptoUtils.HmacSHA1Encrypt(encodedPutPolicy,SK);
        String encodedSign = Base64Utils.safeUrlEncode(result);
        String token = AK + ':' + encodedSign + ':' + encodedPutPolicy;
        Map<String,Object> rs = new HashMap<>();
        rs.put("token",token);
        rs.put("deadLine",exp);
        return JSON.toJSONString(rs);
    }

}
