package com.scs.web.blog.util;

import lombok.Data;

/**
 * @author ke
 * @ClassName ResponseObject
 * @Description DOTO
 * @Date 2019/10/11
 * @Version 1.0
 **/
@Data
public class ResponseObject {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseObject() {
    }

    public static ResponseObject success(Integer code, String msg, Object data) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        ro.setData(data);
        return ro;
    }

    public static ResponseObject success(Integer code, String msg) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }
    /**
    *静态方法，对外提供该类的对象，请求失败
    * */
    public static ResponseObject error(Integer code, String msg) {
        ResponseObject ro = new ResponseObject();
        ro.setCode(code);
        ro.setMsg(msg);
        return ro;
    }
}
