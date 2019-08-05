package com.th.ox.cleaver.activiti.config.mvc;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * MVC返回值格式化标准
 */
@SuppressWarnings("serial")
public class MVCResult extends HashMap<String, Object> {

    public class HttpStatus {

        /**
         * 未登录
         */
        public final static int UNAUTHEN = 4401;

        /**
         * 未授权，拒绝访问
         */
        public final static int UNAUTHZ = 4403;

        /**
         * 未找到
         */
        public final static int NOT_FOUND = 4404;

        /**
         * session超时退出登录
         */
        public final static int SESSION_TIMEOUT = 4433;

        /**
         * shiro相关的错误
         */
        public final static int SHIRO_ERR = 4444;

        /**
         * 服务端异常
         */
        public final static int SERVER_ERR = 5500;

    }

    // ///////////////////// 默认的键 ///////////////////////

    public static final String KEY_OPER = "oper";
    public static final String KEY_SUCC = "succ";
    public static final String KEY_CODE = "code";
    public static final String KEY_MSG  = "msg";
    public static final String KEY_DATA = "data";

    // ///////////////////// 默认的值 ///////////////////////

    public static final String DEFAULT_OPER_VAL  = "default";
    public static final int    DEFAULT_SUCC_CODE = 1;
    public static final int    DEFAULT_FAIL_CODE = -1;
    public static final String DEFAULT_SUCC_MSG  = "ok";
    public static final String DEFAULT_FAIL_MSG  = "fail";

    // ///////////////////// 最通用的两个构造函数 ///////////////////////

    /**
     * 无参构造：操作成功返回的响应信息
     */
    public MVCResult() {
        this.put(KEY_OPER, DEFAULT_OPER_VAL);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 操作成功返回的响应信息
     */
    public MVCResult(String oper) {
        this.put(KEY_OPER, oper);
        this.put(KEY_SUCC, true);
        this.put(KEY_CODE, DEFAULT_SUCC_CODE);
        this.put(KEY_MSG, DEFAULT_SUCC_MSG);
    }

    /**
     * 全参构造
     */
    public MVCResult(String operate, boolean success, int code, String msg, Object data) {
        if (!StringUtils.isEmpty(operate)) {
            this.put(KEY_OPER, operate);
        }
        if (!StringUtils.isEmpty(msg)) {
            this.put(KEY_MSG, msg);
        }
        this.put(KEY_SUCC, success);
        this.put(KEY_CODE, code);
        if (data != null) {
            this.put(KEY_DATA, data);
        }
    }

    // ///////////////////// 各种简便的静态工厂方法 ///////////////////////

    // //// 操作成功的：增、删、改操作成功时使用succ返回

    public static MVCResult succ() {
        return succ(null);
    }

    public static MVCResult succ(String operate) {
        return new MVCResult(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null);
    }

    public static MVCResult succ(String operate, String message) {
        return new MVCResult(operate, true, DEFAULT_SUCC_CODE, message, null);
    }

    public static MVCResult succ(String operate, Object data) {
        return new MVCResult(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, data);
    }

    public static MVCResult succ(String operate, String dataKey, Object dataVal) {
        return new MVCResult(operate, true, DEFAULT_SUCC_CODE, DEFAULT_SUCC_MSG, null).data(dataKey, dataVal);
    }

    // //// 操作失败的：

    public static MVCResult fail() {
        return new MVCResult(DEFAULT_OPER_VAL, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    public static MVCResult fail(String operate) {
        return new MVCResult(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null);
    }

    public static MVCResult fail(String operate, String message) {
        return new MVCResult(operate, false, DEFAULT_FAIL_CODE, message, null);
    }

    public static MVCResult fail(String operate, Object data) {
        return new MVCResult(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, data);
    }

    public static MVCResult fail(String operate, String dataKey, Object dataVal) {
        return new MVCResult(operate, false, DEFAULT_FAIL_CODE, DEFAULT_FAIL_MSG, null).data(dataKey, dataVal);
    }

    // //// 操作动态判定成功或失败的：查询结果使用此返回

    public static MVCResult result(boolean success) {
        return result(null, success);
    }

    public static MVCResult result(String operate, boolean success) {
        return new MVCResult(operate, success, (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE), (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG), null);
    }

    public static MVCResult result(String operate, boolean success, Object data) {
        return new MVCResult(operate, success, (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE), (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG), data);
    }

    public static MVCResult result(String operate, boolean success, String dataKey, Object dataVal) {
        return new MVCResult(operate, success, (success ? DEFAULT_SUCC_CODE : DEFAULT_FAIL_CODE), (success ? DEFAULT_SUCC_MSG : DEFAULT_FAIL_MSG), null).data
                (dataKey, dataVal);
    }

    // ///////////////////// 各种链式调用方法 ///////////////////////

    /**
     * 设置操作名称
     */
    public MVCResult oper(String operate) {
        this.put(KEY_OPER, operate);
        return this;
    }

    /**
     * 设置操作结果是否成功的标记
     */
    public MVCResult succ(boolean success) {
        this.put(KEY_SUCC, success);
        return this;
    }

    /**
     * 设置操作结果的代码
     */
    public MVCResult code(int code) {
        this.put(KEY_CODE, code);
        return this;
    }

    /**
     * 设置操作结果的信息
     */
    public MVCResult msg(String message) {
        this.put(KEY_MSG, message);
        return this;
    }

    /**
     * 设置操作返回的数据
     */
    public MVCResult data(Object dataVal) {
        this.put(KEY_DATA, dataVal);
        return this;
    }

    /**
     * 设置操作返回的数据，数据使用自定义的key存储
     */
    public MVCResult data(String dataKey, Object dataVal) {
        this.put(dataKey, dataVal);
        return this;
    }

}
