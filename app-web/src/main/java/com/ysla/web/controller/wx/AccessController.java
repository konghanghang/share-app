package com.ysla.web.controller.wx;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysla.api.auto.model.WxMp;
import com.ysla.api.model.common.StringEnum;
import com.ysla.api.model.wx.access.CheckVo;
import com.ysla.api.model.wx.access.MessageType;
import com.ysla.api.model.wx.resp.RobotArticle;
import com.ysla.api.model.wx.resp.TextMessage;
import com.ysla.api.module.wx.mp.IWxMpService;
import com.ysla.api.utils.http.HttpClientUtils;
import com.ysla.utils.crypto.CryptoUtils;
import com.ysla.utils.xml.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 接入微信controller
 * @author konghang
 */
@Slf4j
@RestController
@RequestMapping("/wx/access")
public class AccessController {

    /**
     * 图灵机器人接口调用key
     */
    private String key = "";

    /**
     * 微信公众号appId
     */
    @Value("${com.ysla.wechat.appId}")
    private String appId;

    private final String typeText = "100000";
    private final String typeUrl = "200000";
    private final String typeArticle = "302000";

    @Reference(version = "${dubbo.service.version}",check = false, timeout = 10000)
    private IWxMpService mpService;

    /**
     * 接入验证
     * @param checkVo
     * @param response
     * @throws Exception
     */
    @GetMapping
    public void sign(CheckVo checkVo, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        if (checkSignature(checkVo.getSignature(), checkVo.getTimestamp(), checkVo.getNonce())) {
            out.print(checkVo.getEchostr());
        }
    }

    /***
     * 响应消息处理
     * @param request
     * @param response
     */
    @PostMapping
    public void process(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        try(PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String message = processRequest(request);
            out.print(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private boolean checkSignature(String signature, String timestamp, String nonce) {
        WxMp wxMp = mpService.getWxMp(appId);
        String[] arr = new String[]{wxMp.getToken(), timestamp, nonce};
        Arrays.sort(arr);
        //生成字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        //sha1加密
        String temp = CryptoUtils.SHA1(sb.toString());
        return temp.equals(signature);
    }

    /**
     * 处理微信回送消息
     * @param request
     * @return
     */
    private String processRequest(HttpServletRequest request) {
        String message = "异常",content = "";
        try {
            Map<String, String> map = XmlUtils.xml2map(request.getInputStream());
            String fromUserName = map.get("FromUserName");
            log.trace("用户的openid：" + fromUserName);
            //oWda8wvOyn52E_tvOBrhHhpXP5rg kh,oWda8wg7tX9ZFKnsChHuStz8jdTM ml,oWda8wob0R3kEnzYbGgRdBrmf9sE sj
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            // 按照事件类型(msgType)来判断
            switch (msgType){
                case "text":
                    String oldContent = map.get("Content");
                    message = processText(oldContent,toUserName,fromUserName);
                    break;
                //event事件推送
                case "event":
                    message = processEvent(map, toUserName, fromUserName);
                    break;
                case "image":
                    content = "您发送的是图片消息";
                    message = wrapBackMessage(toUserName, fromUserName, content);
                    break;
                case "video":
                    content = "您发送的是视频消息";
                    message = wrapBackMessage(toUserName, fromUserName, content);
                    break;
                case "link":
                    content = "您发送的是链接消息";
                    message = wrapBackMessage(toUserName, fromUserName, content);
                    break;
                default:
                    content = "无法判断您的消息类型,无法处理";
                    message = wrapBackMessage(toUserName, fromUserName, content);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 处理文本消息
     * @param oldContent
     * @param toUserName
     * @param fromUserName
     * @return
     */
    private String processText(String oldContent, String toUserName, String fromUserName){
        String message = "error",ML = "ml";
        if (ML.equals(oldContent)){
            if("oWda8wvOyn52E_tvOBrhHhpXP5rg".equals(fromUserName)
                    || "oWda8wg7tX9ZFKnsChHuStz8jdTM".equals(fromUserName)){
                //mensesService.addMenses("oZPbCv_4HFKnOIncGW1NElOHc_UA");
                message = wrapBackMessage(toUserName, fromUserName, "添加成功!");
            }else {
                message = wrapBackMessage(toUserName, fromUserName, "莫乱搞!!!!!!!");
            }
        } else {
            //tuLing
            String msg = tuLingRobot(oldContent);
            message = wrapBackMessage(toUserName, fromUserName, msg);
        }
        return message;
    }

    /**
     * event事件处理
     * @param map
     * @param toUserName
     * @param fromUserName
     * @return
     */
    private String processEvent(Map<String, String> map, String toUserName, String fromUserName){
        String message = "";
        String eventType = map.get("Event");
        switch (eventType){
            case "subscribe":
                message = wrapBackMessage(toUserName, fromUserName, subscribe());
                break;
            case "CLICK":
                String eventKey = map.get("EventKey");
                String content = "", key01 = "key01", key11="11", key21 = "21";
                if (key01.equals(eventKey)) {
                    content = "01被点击";
                } else if (key11.equals(eventKey)) {
                    content = "clickButton被点击";
                } else if (key21.equals(eventKey)) {
                    content = "21被点击";
                }
                message = wrapBackMessage(toUserName, fromUserName, content);
                break;
            case "poi_check_notify":
                // 创建门店后微信的推送事件------成功与否
                // 门店id
                String poi = map.get("PoiId");
                // 审核结果
                String result = map.get("Result");
                // 成功的通知信息，或审核失败的驳回理由
                String msg = map.get("Msg");
                log.trace("门店id：" + poi + "\n审核结果：" + result + "\n消息：" + msg);
                break;
            case "user_pay_from_pay_cell":
                // 卡券买单后的推送事件
                // 卡券ID
                String cardId = map.get("CardId");
                // 卡券Code码
                String userCardCode = map.get("UserCardCode");
                break;
            case "card_pass_check":
                // 卡券未通过审核
                cardId = map.get("CardId");
                break;
            case "user_get_card":
                // 用户领取卡券
                cardId = map.get("CardId");
                break;
            case "user_del_card":
                // 用户删除卡券
                break;
            case "user_consume_card":
                // 核销事件
                break;
            case "user_view_card":
                // 进入会员卡事件推送
                break;
            case "card_merchant_check_result":
                //子商户审核时间
                break;
        }
        return message;
    }

    /**
     * 封装返回信息
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    private String wrapBackMessage(String toUserName, String fromUserName, String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setMsgType(MessageType.MESSAGE_TEXT.getType());
        textMessage.setContent(content);
        textMessage.setCreateTime(System.currentTimeMillis());

        return XmlUtils.object2Xml(textMessage,textMessage.getClass());
    }

    /**
     * 用户关注事件推送内容
     * @return
     */
    private String subscribe(){
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎你的关注，请按照提示回复：\n");
        sb.append("1、回复'新闻'来查看新闻，例如：'新闻'\n");
        sb.append("2、回复'地区+天气'来查看天气，例如：'广州天气'\n");
        sb.append("3、回复'笑话'来获取笑话，例如：'笑话'\n");
        sb.append("4、回复'历史今日'来获取历史上今天所发生的大事\n");
        sb.append("5、已接入图灵机器人，可以回复除上述任意语句进行调戏。\n");
        return sb.toString();
    }

    /**
     * 图灵机器人
     * @param oldContent
     * @return
     */
    private String tuLingRobot(String oldContent){
        if ("".equals(key)){
            WxMp wxMp = mpService.getWxMp(appId);
            key = wxMp.getAccountName();
        }
        String content = "";
        String info = oldContent;
        String url = "http://www.tuling123.com/openapi/api?key=" + key + "&info=" + info;
        JSONObject jsonObject = HttpClientUtils.httpGet(url);
        if (typeText.equals(jsonObject.getString(StringEnum.CODE.getName()))) {
            content = jsonObject.getString("text");
        } else if (typeUrl.equals(jsonObject.getString(StringEnum.CODE.getName()))) {
            content = jsonObject.getString("text") + "<br>" + jsonObject.getString("url");
        } else if (typeArticle.equals(jsonObject.getString(StringEnum.CODE.getName()))) {
            List<RobotArticle> tArticles = JSON.parseArray(jsonObject.getString("list"), RobotArticle.class);
            String str = "";
            for (int i = 0; i < tArticles.size(); i++) {
                str += i + 1 + "、<a href=\"" + tArticles.get(i).getDetailurl() + "\">" + tArticles.get(i).getArticle() + "</a>   -" + tArticles.get(i).getSource() + "\n\n";
            }
            content = jsonObject.getString("text") + ":\n\n" + str;
        } else {
            content = "对不起，你说的话真是太高深了……";
        }
        return content;
    }

}
