package com.example.GameBusiness.entity;

//用于封装服务器返回的json数据
public class JsonResult<T>{
    private Integer state; //相应状态码
    private String msg; //相应消息
    private T data; //相应数据

    //创建一个正常相应，不携带数据的jsonResult对象
    public static JsonResult<Void> getSuccessJR(){
        return new JsonResult<>(1000,"OK");
    }
    //创建一个正常相应，携带数据的jsonResult对象
    public static<T> JsonResult<T> getSuccessJR(T data){
        return new JsonResult<>(1000,"OK",data);
    }

    //创建构造器，get/set方法，tostring方法

    public JsonResult() {
    }

    public JsonResult(Integer state, String msg, T data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
