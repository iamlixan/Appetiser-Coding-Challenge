package me.com.lixan.appetisercodingchallenge.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.com.lixan.appetisercodingchallenge.R;

/**
 * Created by LeakSun on 03/04/2019.
 */

public class DetailsActivity extends AppCompatActivity {


    ImageView bigPic;
    TextView longDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_details_screen);

        bigPic = findViewById(R.id.bigPic);
        longDesc = findViewById(R.id.longDescription);

        String pictureURL = getIntent().getStringExtra("BIG_PICTURE");
        String longDescription = getIntent().getStringExtra("LONG_DESCRIPTION");

        Picasso.with(getApplicationContext()).load(pictureURL).resize(800, 800).into(bigPic);
        longDesc.setText(longDescription);

    }
}
