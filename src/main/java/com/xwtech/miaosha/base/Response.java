package com.xwtech.miaosha.base;

public class Response<T> {
    private int code;
    private String msg;
    private Object data;

    /**
     * 成功时候的调用
     * */
    public static Response success(Object data){
        return  new Response(data);
    }

    /**
     * 失败时候的调用
     * */
    public static  Response error(CodeMsg cm){
        return new  Response(cm);
    }

    public Response(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Response(CodeMsg cm) {
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public Object getData() {
        return data;
    }

}
