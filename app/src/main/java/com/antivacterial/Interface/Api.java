package com.antivacterial.Interface;

import com.antivacterial.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("public/dispensadores")
    Call<List<Posts>> getPosts();

    @PUT("public/dispensadores/{id}")
    Call<Posts> putPost(@Path("id") int id, @Body Posts post );

    @PATCH("public/dispensadores/{id}")
    Call<Posts> patchPost(@Path("id") int id, @Body Posts post );

}
