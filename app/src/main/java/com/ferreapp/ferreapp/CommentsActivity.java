package com.ferreapp.ferreapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Iterator;

public class CommentsActivity extends AppCompatActivity {

    private static final String TAG = "CommentsActivity";
    private FirebaseFirestore db;
    private ArrayList<Order> mOrders;
    private TextView mText;
    private ListView mList;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        mOrders = new ArrayList<Order>();
        mList = (ListView) findViewById(R.id.list_comments);
        mText = (TextView) findViewById(R.id.usuario_name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            email = user.getEmail();
            Log.d(TAG, "onCreate: user = "+email);
        } else
            email = "";

        mText.setText("Comentarios del usuario "+email);

        db = FirebaseFirestore.getInstance();

        db.collection("orders")
                .whereEqualTo("seller", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: data fetched");

                            for(int i=0; i< task.getResult().getDocuments().size();i++) {
                                mOrders.add(task.getResult().getDocuments().get(i).toObject(Order.class));
                            }



                            CommentArrayAdapter adap = new CommentArrayAdapter(CommentsActivity.this,mOrders);
                            mList.setAdapter(adap);



                        }else {
                            Log.d(TAG, "Error getting document: "+ task.getException());
                        }
                    }
                });


    }

}
