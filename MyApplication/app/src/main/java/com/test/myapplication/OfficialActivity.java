package com.test.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by NehaRege on 12/8/16.
 */
public class OfficialActivity extends AppCompatActivity {

    private static final String TAG = "OfficialActivity";


    ListView mListView;

    DatabaseReference mRef;
    DatabaseReference dRef;

    ArrayList<Report> reportArrayList;

    ArrayAdapter<Report> arrayAdapter;

    Report value;

    Bitmap bitmap;

    String currentUserEmail;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_official);

        Toast.makeText(OfficialActivity.this, "Official", Toast.LENGTH_SHORT).show();

        reportArrayList = new ArrayList<>();


        dRef = FirebaseDatabase.getInstance().getReference();


        mRef = dRef.child("report");

        mListView = (ListView) findViewById(R.id.listView);


        final CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(OfficialActivity.this, reportArrayList);

        mListView.setAdapter(customBaseAdapter);




//        arrayAdapter = new ArrayAdapter<Report>(this,android.R.layout.simple_list_item_1, reportArrayList);
//
//
//        mListView.setAdapter(arrayAdapter);






        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                Map<String,String> map = dataSnapshot.getValue(Map.class);
//
//                String description = map.get("description");
//
//                String email = map.get("emailId");

                value = dataSnapshot.getValue(Report.class);

                Log.i(TAG, "onChildAdded: "+value.getEmailId());

                reportArrayList.add(value);

//                Log.i(TAG, "onChildAdded: "+reportArrayList.get(0).getEmailId());

//                reportArrayList.get(0).getEmailId();

                customBaseAdapter.notifyDataSetChanged();

//                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(OfficialActivity.this, "Item clicked", Toast.LENGTH_SHORT).show();

//                public  String longitude;
//                public  String latitude;
//                public  String street;
//                public  String currentstatus;
//                public  String severity;
//                public String img;
//                public Drawable image;
//                public  String size;
//                public  String time;
//                public  String date;
//                public  String description;
//                public List<User> user;
//                public String emailId;






                Intent intent = new Intent(OfficialActivity.this,OfficialDetail.class);

                currentUserEmail = "spurshujjawal@gmail.com";

                if(reportArrayList.get(i).getEmailId().equals(currentUserEmail)) {
                    intent.putExtra("email_of", reportArrayList.get(i).getEmailId());
                    intent.putExtra("long_of", reportArrayList.get(i).getLongitude());
                    intent.putExtra("lat_of", reportArrayList.get(i).getLatitude());
                    intent.putExtra("size_of", reportArrayList.get(i).getSize());
                    intent.putExtra("severity_of", reportArrayList.get(i).getSeverity());
                    intent.putExtra("descrip_of", reportArrayList.get(i).getDescription());
                    intent.putExtra("street_of", reportArrayList.get(i).getStreet());
                    intent.putExtra("img_of",reportArrayList.get(i).getImg());

                }


//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,50,bs);
//
//
//

//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream); // 'bitmap' is the image returned
//                byte[] b = stream.toByteArray();

//                String b64Image = Base64.encodeToString(b, Base64.DEFAULT);



//                Intent _intent = new Intent(this, newscreen.class);
//                Bitmap _bitmap; // your bitmap
//                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
//                _bitmap.compress(Bitmap.CompressFormat.PNG, 50, _bs);
//                i.putExtra("byteArray", _bs.toByteArray());
//                startActivity(i);

                startActivity(intent);


            }
        });


//        listView.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
//                // When clicked, show a toast with the TextView text
//                Toast.makeText(getApplicationContext(),
//                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
//            }
//        });








    }
}
