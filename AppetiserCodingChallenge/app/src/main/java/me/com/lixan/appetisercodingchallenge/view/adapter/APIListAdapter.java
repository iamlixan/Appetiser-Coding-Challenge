package me.com.lixan.appetisercodingchallenge.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.com.lixan.appetisercodingchallenge.R;
import me.com.lixan.appetisercodingchallenge.model.APIModel;

/**
 * Created by LeakSun on 03/04/2019.
 */

public class APIListAdapter extends ArrayAdapter<APIModel.Results> {

    private final String TAG = "APIListAdapter";
    private ArrayList<APIModel.Results> listViewDataModel;
    private Context context;

    private static class ViewHolder{
        ImageView artwork;
        TextView trackName;
        TextView genretxt;
        TextView pricetxt;
    }

    /**
     *
     * @param listViewDataModel ArrayList of models
     * @param context
     */
    public APIListAdapter(ArrayList<APIModel.Results> listViewDataModel, Context context){
        super(context, R.layout.layout_list_item, listViewDataModel);
        this.listViewDataModel = listViewDataModel;
        this.context = context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        APIModel.Results data = getItem(position);

        ViewHolder viewHolder;

        View resultContainer;

        if(convertView == null){

            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.layout_list_item, parent, false);

            viewHolder.artwork = convertView.findViewById(R.id.artWork);
            viewHolder.trackName = convertView.findViewById(R.id.trackName);
            viewHolder.genretxt = convertView.findViewById(R.id.genretxt);
            viewHolder.pricetxt = convertView.findViewById(R.id.pricetxt);

            resultContainer = convertView;
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            resultContainer = convertView;
        }

        if (data != null) {
            Picasso.with(context).load(data.artworkUrl100)
                    .resize(200,200)
                    .into(viewHolder.artwork);
            viewHolder.trackName.setText(data.trackName);
            viewHolder.pricetxt.setText(String.format("%.2f", data.trackPrice));
            viewHolder.genretxt.setText(data.primaryGenreName);

        }else{
            Log.e(TAG, "getView: DATA IS NULL");
        }

        return resultContainer;
    }

}
