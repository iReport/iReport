package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 *
 * Created by NehaRege on 12/8/16.
 */
public class OfficialDetail extends AppCompatActivity {

    TextView textViewStreet;
    TextView textViewLat;
    TextView textViewLong;
    TextView textViewDescription;
    TextView textViewSeverity;
    TextView textViewSize;
    TextView textViewEmail;

    ImageView imgView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_item_detail);

        Intent intent = getIntent();

        initialize();

//        Intent intent = new Intent(OfficialActivity.this,OfficialDetail.class);
//        intent.putExtra("email_of",reportArrayList.get(i).getEmailId());
//        intent.putExtra("long_of",reportArrayList.get(i).getLongitude());
//        intent.putExtra("lat_of",reportArrayList.get(i).getLatitude());
//        intent.putExtra("size_of",reportArrayList.get(i).getSize());
//        intent.putExtra("severity_of",reportArrayList.get(i).getSeverity());
//        intent.putExtra("descrip_of",reportArrayList.get(i).getDescription());
//        intent.putExtra("street_of",reportArrayList.get(i).getStreet());
//        intent.putExtra("img_of",reportArrayList.get(i).getImg());

        if(intent!=null) {

            textViewStreet.setText(intent.getStringExtra("street_of"));
            textViewLat.setText(intent.getStringExtra("lat_of"));
            textViewLong.setText(intent.getStringExtra("long_of"));
            textViewDescription.setText(intent.getStringExtra("descrip_of"));
            textViewSeverity.setText(intent.getStringExtra("severity_of"));
            textViewSize.setText(intent.getStringExtra("size_of"));


            byte[] decodedString = Base64.decode(intent.getStringExtra("img_of"), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imgView.setImageBitmap(decodedByte);

//            imgView.setImageDrawable(intent.get);
//            textView.setText(intent.getStringExtra("street_of"));

//            Bitmap bitmap = BitmapFactory.decodeResource(get)
//
//            imgView.setImageDrawable(intent.getStringExtra("img_of").);
//
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.elephant);



        }










    }

    private void initialize(){


        textViewStreet = (TextView) findViewById(R.id.textView22);
        imgView = (ImageView) findViewById(R.id.imageView);
        textViewDescription = (TextView) findViewById(R.id.item_detail);
        textViewSeverity = (TextView) findViewById(R.id.textView24);
        textViewSize = (TextView) findViewById(R.id.textView26);
        textViewLat = (TextView) findViewById(R.id.textView13);
        textViewLong = (TextView) findViewById(R.id.textView17);

    }
}
