package me.com.lixan.appetisercodingchallenge.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import me.com.lixan.appetisercodingchallenge.BuildConfig;
import me.com.lixan.appetisercodingchallenge.interfaces.APIInterface;
import me.com.lixan.appetisercodingchallenge.interfaces.DataInterface;
import me.com.lixan.appetisercodingchallenge.model.APIModel;
import me.com.lixan.appetisercodingchallenge.presenter.manager.RetroFitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LeakSun on 03/04/2019.
 */

public class DataPresenter  {

    private final String TAG = "DataManager";

    private APIModel apiModel;
    private List<APIModel.Results> resultsArrayList;
    private String jsonValue;
    private Gson gson;
    private DataInterface dataInterface;
    private Context context;

    public DataPresenter(DataInterface dataInterface, Context context){
        this.dataInterface = dataInterface;
        this.context = context;
    }

    /**
     * chooses the method of getting the data.
     * API - if the device is connected to the Internet.
     * Shared Preference - if the device is not connected to the internet.
     */
    public void getAPIData(){
        if(isNetworkCOnnected()){
            getDataFromAPI();
        }else {
            getSharedPrefData();
        }

    }

    /**
     * sends a request to the server to get the data from the API
     */
    public void getDataFromAPI() {
        gson = new Gson();
        APIInterface apiInterface = RetroFitManager.startRetroFit(BuildConfig.API_URL).create(APIInterface.class);
        Call<APIModel> call = apiInterface.getAPIData();
        call.enqueue(new Callback<APIModel>() {
            @Override
            public void onResponse(Call<APIModel> call, Response<APIModel> response) {

                apiModel = response.body();
                if(apiModel != null){

                    resultsArrayList = apiModel.results;

                    //save JSON to Shared Preferences
                    jsonValue = gson.toJson(apiModel);
                    SharedPreferences.Editor shareEditor = context.getSharedPreferences("json_file", 0).edit();
                    shareEditor.putString("jsonValue", jsonValue);
                    shareEditor.apply();

                    DataPresenter.this.dataInterface.onAPIResults(resultsArrayList);


                }else{
                    Log.e(TAG, "onResponse: apimodel is NULL");
                }

            }

            @Override
            public void onFailure(Call<APIModel> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());
                call.cancel();
            }
        });
    }

    /**
     * retrieves the saved json file on Shared Preference
     */
    public void getSharedPrefData() {
       SharedPreferences preferences = context.getSharedPreferences("json_file", 0);
       gson = new Gson();

       if(preferences.contains("jsonValue")){
           String jsonData = preferences.getString("jsonValue", "");
           Log.d(TAG, "getSharedPrefData: " + jsonData);
           APIModel jsonValue = gson.fromJson(jsonData, APIModel.class);

           resultsArrayList = jsonValue.results;

           DataPresenter.this.dataInterface.onAPIResults(resultsArrayList);
       }else {
           Toast.makeText(context, "Network Error. Please check connection settings", Toast.LENGTH_LONG).show();
       }

    }

    /**
     * Checks the network connectivity.
     * @return boolean returns true if the device is connected to a network, false if not.
     */
    public boolean isNetworkCOnnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean wifiConnected = false;
        boolean mobileConnected = false;
        boolean ethernetConnected = false;

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if(networkInfo != null){
                if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI ){
                    wifiConnected = true;
                }
                else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
                    mobileConnected = true;
                }
                else if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET){
                    ethernetConnected = true;
                }
            }

            return wifiConnected || mobileConnected || ethernetConnected;
        }else {
            return false;
        }
    }

}
