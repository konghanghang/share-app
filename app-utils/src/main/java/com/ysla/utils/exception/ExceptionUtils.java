package com.ysla.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具
 * @author konghang
 */
public class ExceptionUtils {

    public static String getStackMsg(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static String getStackMsg(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
