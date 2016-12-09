package com.test.myapplication;
import android.content.SharedPreferences;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.test.myapplication.DummyContent;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.test.myapplication.R.id.imageView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    private ArrayList<String> mUsernames;
    private DatabaseReference listdatabase;
    private DatabaseReference itemdatabase;
    private FirebaseAuth firebaseAuth;

    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    public String latReport = sharedPreferences.getString("lat_string",null);
    public String longiReport = sharedPreferences.getString("long_string",null);
    public String streetReport = sharedPreferences.getString("street_string",null);
    public String descriptionReport = sharedPreferences.getString("description_string",null);
    public String imgReport = sharedPreferences.getString("img_string",null);

    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

//        editor.putString("lat_string",lat.getText().toString());
//        editor.putString("long_string",longi.getText().toString());
//        editor.putString("street_string",street.getText().toString());
//        editor.putString("description_string",editTextDescription.getText().toString());
//        editor.putString("img_string",b64Image);

        firebaseAuth=FirebaseAuth.getInstance();
        listdatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();

       // .
        itemdatabase = listdatabase.child("user");

       // final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUsernames);

        itemdatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
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

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
        // Show the dummy content as text in a TextView.
      //  if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textView7)).setText("12/08/2016");
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(descriptionReport);
            ((TextView) rootView.findViewById(R.id.textView13)).setText(latReport);
            ((TextView) rootView.findViewById(R.id.textView17)).setText(longiReport);
            ((TextView) rootView.findViewById(R.id.textView22)).setText(streetReport);
            ((TextView) rootView.findViewById(R.id.textView24)).setText("Medium");
            ((TextView) rootView.findViewById(R.id.textView26)).setText("Medium");

       // byte[] decodedimg = Base64.decode(imgReport,Base64.DEFAULT);
       // Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedimg, 0, imgReport.length());
       // ((ImageView) rootView.findViewById(R.id.imageView)).setImageBitmap(decodedByte);

        //bitmap = ((BitmapDrawable) imgPreview.getDrawable()).getBitmap();


        //     Picasso.with(getActivity().getApplicationContext()).load(mItem.imageurl).into((ImageView) rootView.findViewById(R.id.imageView));
      //  }
        return rootView;
    }
}