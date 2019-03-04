package me.com.lixan.appetisercodingchallenge.interfaces;

import java.util.List;

import me.com.lixan.appetisercodingchallenge.model.APIModel;

/**
 * Created by LeakSun on 03/04/2019.
 */

public interface DataInterface {

    void onAPIResults(List<APIModel.Results> resultsList);


}
