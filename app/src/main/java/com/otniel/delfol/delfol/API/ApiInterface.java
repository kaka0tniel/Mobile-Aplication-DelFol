package com.otniel.delfol.delfol.API;

/**
 * Created by Otniel on 5/22/2018.
 */
import com.otniel.delfol.delfol.Model.ResponsBerita;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiInterface {
    @FormUrlEncoded
    @POST("insertDelfol.php")
    Call<ResponsBerita> setResult(@Field("title") String title,
                                    @Field("content") String content);

    @GET("readDelfol.php")
    Call<ResponsBerita> getResult();

    @FormUrlEncoded
    @POST("updateDelfol.php")
    Call<ResponsBerita> updateData( @Field("id_news") String id_news,
                                   @Field("title") String title,
                                   @Field("content") String content);

    @FormUrlEncoded
    @POST("deleteDelfol.php")
    Call<ResponsBerita> deleteData(@Field("id_news") String id_news);
}
