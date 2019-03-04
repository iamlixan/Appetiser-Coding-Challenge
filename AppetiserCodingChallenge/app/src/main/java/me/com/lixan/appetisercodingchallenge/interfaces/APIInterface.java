package me.com.lixan.appetisercodingchallenge.interfaces;

import me.com.lixan.appetisercodingchallenge.model.APIModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LeakSun on 03/04/2019.
 */

public interface APIInterface {

    @GET("/search?term=star&country=au&media=movie")
    Call<APIModel> getAPIData();

}
