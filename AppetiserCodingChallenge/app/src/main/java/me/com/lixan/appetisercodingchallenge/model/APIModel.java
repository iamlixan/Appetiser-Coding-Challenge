package me.com.lixan.appetisercodingchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeakSun on 03/04/2019.
 */

public class APIModel {

    @SerializedName("resultCount")
     public int resultCount;

    @SerializedName("results")
    public List<Results> results;

   public class Results{

        @SerializedName("wrapperType")
        public String wrapperType;

        @SerializedName("kind")
        public String kind;

        @SerializedName("trackId")
        public Integer trackId;

        @SerializedName("artistName")
        public String artistName;

        @SerializedName("trackName")
        public String trackName;

        @SerializedName("trackCensoredName")
        public String trackCensoredName;

        @SerializedName("trackViewUrl")
        public String trackViewUrl;

        @SerializedName("previewUrl")
        public String previewUrl;

        @SerializedName("artworkUrl30")
        public String artworkUrl30;

        @SerializedName("artworkUrl60")
        public String artworkUrl60;

        @SerializedName("artworkUrl100")
        public String artworkUrl100;

        @SerializedName("collectionPrice")
        public Float collectionPrice;

        @SerializedName("trackPrice")
        public Float trackPrice;

        @SerializedName("trackRentalPrice")
        public Float trackRentalPrice;

        @SerializedName("collectionHdPrice")
        public Float collectionHdPrice;

        @SerializedName("trackHdPrice")
        public Float trackHdPrice;

        @SerializedName("trackHdRentalPrice")
        public Float trackHdRentalPrice;

        @SerializedName("releaseDate")
        public String releaseDate;

        @SerializedName("collectionExplicitness")
        public String collectionExplicitness;

        @SerializedName("trackExplicitness")
        public String trackExplicitness;

        @SerializedName("trackTimeMillis")
        public Integer trackTimeMillis;

        @SerializedName("country")
        public String country;

        @SerializedName("currency")
        public String currency;

        @SerializedName("primaryGenreName")
        public String primaryGenreName;

        @SerializedName("contentAdvisoryRating")
        public String contentAdvisoryRating;

        @SerializedName("shortDescription")
        public String shortDescription;

        @SerializedName("longDescription")
        public String longDescription;

        @SerializedName("hasITunesExtras")
        public Boolean hasITunesExtras;

    }

}
