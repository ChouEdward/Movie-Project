package com.moviesapp.project.dm_project.data.bean;

public class BaseResponse {

    public int code = -1;
    public String msg = "";

    public BaseResponse(int code, String msg){
        this.code= code;
        this.msg = msg;
    }

}
