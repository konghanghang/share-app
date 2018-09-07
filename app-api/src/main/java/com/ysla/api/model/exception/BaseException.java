package com.ysla.api.model.exception;

import com.ysla.api.model.common.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基础异常类
 * @author konghang
 */
public class BaseException extends RuntimeException {

    @Getter @Setter private ErrorCode errorCode;
    @Getter private final Map<String,Object> properties = new TreeMap<String,Object>();

    public BaseException(){
        super(ErrorCode.CORE_SERVICE_FAIL.getErrorMsg());
        this.errorCode = ErrorCode.CORE_SERVICE_FAIL;
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(ErrorCode errorCode){
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public BaseException set(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    public <T> T getPropertiesByName(String name) {
        return (T)properties.get(name);
    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }


    @Override
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            printStackTrace(new PrintWriter(s));
        }
    }

    @Override
    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            printWriter.println(this);
            printWriter.println("\t-------------------------------");
            if (errorCode != null) {
                printWriter.println("\t" + errorCode + ":" + errorCode.getClass().getName());
            }

            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String,Object> entry : properties.entrySet()) {
                sb.append("\t").append(entry.getKey()).append("=[").append(entry.getValue()).append("]");
                printWriter.println(sb.toString());
                sb.delete(0,sb.length());
            }
            printWriter.println("\t-------------------------------");
            StackTraceElement[] trace = getStackTrace();
            for (int i=0; i < trace.length; i++)
                printWriter.println("\tat " + trace[i]);

            Throwable ourCause = getCause();
            if (ourCause != null) {
                ourCause.printStackTrace(printWriter);
            }
            printWriter.flush();
        }
    }

}
