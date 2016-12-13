package com.test.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by NehaRege on 12/11/16.
 */
public class CustomBaseAdapter extends BaseAdapter {

//    private ArrayList<Report> data;
//    private Context context;
//    private ViewHolder viewHolder;

    private ArrayList<Report> reportArrayList;
    private Context context;
    private ViewHolder viewHolder;

    String currentUserEmail;


    DatabaseReference mRef;
    DatabaseReference dRef;

    public CustomBaseAdapter(Context context, ArrayList<Report> reportArrayList){
        this.reportArrayList = reportArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return reportArrayList.size();

    }

    @Override
    public Object getItem(int i) {
        return reportArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if( view == null ) {

            view = LayoutInflater.from(context).inflate(R.layout.activity_custom_view,viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Report report = reportArrayList.get(i);

        currentUserEmail = "spurshujjawal@gmail.com";

        if(report.getEmailId().equals(currentUserEmail)) {


            viewHolder.textViewTitle.setText(report.getEmailId());
//        viewHolder.imageView.setImageDrawable(reportArrayList.get(i).getImage());
//        viewHolder.imageView.setImageResource(R.drawable.logo);


            byte[] decodedString = Base64.decode(report.getImg(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            viewHolder.imageView.setImageBitmap(decodedByte);

        }

//        Bundle extras = data.getExtras();
//        Bitmap imageBitmap = (Bitmap) extras.get("data");
//        mImageLabel.setImageBitmap(imageBitmap);
//        encodeBitmapAndSaveToFirebase(imageBitmap);




//        byte[] decodedimg = Base64.decode(imgReport,Base64.DEFAULT);
//         Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedimg, 0, imgReport.length());
//         ((ImageView) rootView.findViewById(R.id.imageView)).setImageBitmap(decodedByte);

        return view;

    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        /**
//         * Convert view will be the top level view of our list item layout.
//         *
//         * For us this means its a LinearLayout.
//         *
//         * We check if its null, and non existent then we inflate the view. Otherwise
//         * we want to re-use the current layout.
//         */
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        /**
//         * We can pull out our data object from the data list using its position.
//         *
//         * In this case, we grab the current animal for this view based on its position
//         * in the data list.
//         */
//        final Animal currentAnimal = data.get(position);
//
//        /**
//         * Now we can bind our data values from the currentAnimal object instance into our
//         * views, which are saved inside the ViewHolder!
//         */
//        viewHolder.firstTextView.setText(currentAnimal.getName());
//        viewHolder.secondTextView.setText(currentAnimal.getSound());
//
//        /**
//         * Lastly, we create a button click listener that will toast the animal name and sound.
//         *
//         * First we store the String `animalName says: animalSound` inside a variable to use inside
//         * the Toast
//         */
//        final String toastText = currentAnimal.getName() + " says: "+ currentAnimal.getSound();
//        viewHolder.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        /**
//         * Return the whole item layout here so it can be drawn by the ListView
//         */
//        return convertView;
//    }


    private class ViewHolder {
        TextView textViewTitle;
        ImageView imageView;

        public ViewHolder(View itemLayout){

            this.imageView = (ImageView) itemLayout.findViewById(R.id.custom_image);
            this.textViewTitle = (TextView) itemLayout.findViewById(R.id.custom_title);

//            this.firstTextView = (TextView) itemLayout.findViewById(R.id.list_item_tv_first);
//            this.secondTextView = (TextView) itemLayout.findViewById(R.id.list_item_tv_second);
//            this.button = (Button) itemLayout.findViewById(R.id.list_item_button);
        }
    }
}
