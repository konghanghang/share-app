package com.ysla.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author konghang
 */
public class RegexUtils {

    private static Pattern PHONE_NUM_PATTERN = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

    /**
     * 是否是电话号码
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {
        Matcher m = PHONE_NUM_PATTERN.matcher(mobiles);
        return m.matches();
    }

}
