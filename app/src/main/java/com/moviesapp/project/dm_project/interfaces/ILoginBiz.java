package com.moviesapp.project.dm_project.interfaces;

import com.moviesapp.project.dm_project.data.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ILoginBiz {
    @POST("mobileLogin.shtml")
    @FormUrlEncoded
    Call<ResponseBody> login(
            @Field("userPhone") String userPhone,
            @Field("password") String password,
            @Field("isRememberMe") Boolean isRememberMe
    );

    @POST("getUserInfo.shtml")
    @FormUrlEncoded
    Call<User> getUserInfo(
            @Field("userPhone") String userPhone
    );

    @POST("mobileUpdatePswd.shtml")
    @FormUrlEncoded
    Call<ResponseBody> changePassword(
            @Field("pswd") String pswd,
            @Field("newPswd") String newPswd
    );

    @GET("logout.shtml")
    Call<ResponseBody> signOut();
}
