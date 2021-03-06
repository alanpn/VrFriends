package com.socialmedia.friends;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class profile extends Fragment {
    String name,email,bio_data,dob_data;
    Uri photoUrl;
    ProgressBar pb;
    TextView bio;
    int friend_count = 0;
    TextView name_f,email_f,friend,dob;
    int imagecode=1;
    ImageView imageView;  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference server = FirebaseDatabase.getInstance().getReference();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        server.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot childl : dataSnapshot.getChildren()) {

                    if(!childl.child("data").child("uid").getValue().equals("nulll")) {
                        if(childl.child("data").child("uid").getValue().toString().equals(user.getUid())) {

                              imagecode = Integer.parseInt(childl.child("data").child("image_code").getValue().toString());
                            friend_count = Integer.parseInt(childl.child("frined_count").child("count").getValue().toString());
                            bio_data = childl.child("data").child("bio").getValue().toString();
                             name = childl.child("data").child("name").getValue().toString();
                             dob_data = childl.child("data").child("dob").getValue().toString();
                             friend.setText(friend_count+"");
                             dob.setText(dob_data);
                             name_f.setText(name);
                            bio.setText(bio_data);

                            if(imagecode == 1)
                            {
                                imageView.setImageResource(R.drawable.boy);
                            }else  if(imagecode == 2)
                            {imageView.setImageResource(R.drawable.btw);

                            }else if(imagecode == 3)
                            {imageView.setImageResource(R.drawable.girl);

                            }else if(imagecode == 4)
                            { imageView.setImageResource(R.drawable.gtw);

                            }else if(imagecode == 5)
                            {imageView.setImageResource(R.drawable.man);

                            }else if(imagecode ==6 )
                            {
                                imageView.setImageResource(R.drawable.manfive);
                            }else if(imagecode == 7)
                            {
                                imageView.setImageResource(R.drawable.manth);
                            }else if(imagecode ==8 )
                            {
                                imageView.setImageResource(R.drawable.mantw);
                            }else if(imagecode ==9 )
                            {
                                imageView.setImageResource(R.drawable.mfo);
                            }
                            pb.setVisibility(View.INVISIBLE);
                        }

                    }



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return inflater.inflate(R.layout.frame_profile,container,false);


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = getView().findViewById(R.id.imageView);
        bio = getView().findViewById(R.id.bio);
        dob = getView().findViewById(R.id.dob);
        friend = getView().findViewById(R.id.frn);

        pb=getView().findViewById(R.id.p);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        name_f = getView().findViewById(R.id.name);
        email_f =getView().findViewById(R.id.email);




        email_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),login_firebase.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}
