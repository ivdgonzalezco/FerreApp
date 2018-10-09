package com.ferreapp.ferreapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NuevasPedidas extends AppCompatActivity {

    private ListView mList;
    private TextView mBuyerName, mTotalPrice;
    private Button mButtonUpdateOrder;
    private DatabaseReference mDatabaseRef;
    private FirebaseFirestore db;
    private static final String TAG = "MyActivity";
    private Order mOrder;
    private ArrayList<String> mProducts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevas_pedidas);
        setTitle("Nuevas pedidas");

        mButtonUpdateOrder = (Button) findViewById(R.id.updateOrder);
        mBuyerName = (TextView) findViewById(R.id.usuarioComprador);
        mTotalPrice = (TextView) findViewById(R.id.price);
        mList = (ListView) findViewById(R.id.list_view);



        mButtonUpdateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(NuevasPedidas.this, mButtonUpdateOrder);
                popupMenu.getMenuInflater().inflate(R.menu.state_order_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(NuevasPedidas.this, ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();

            }
        });


        //firestore
        db = FirebaseFirestore.getInstance();

        db.collection("orders")
                .whereEqualTo("state", null)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            mOrder = task.getResult().getDocuments().get(0).toObject(Order.class);
                            mBuyerName.setText(mOrder.getBuyer());
                            mTotalPrice.setText(String.valueOf(mOrder.getTotalPrice())+ " $");
                            mProducts = new ArrayList<String>();
                            Iterator<Product> it = mOrder.getProducts().iterator();
                            while(it.hasNext()){
                                mProducts.add(it.next().getName());
                            }
                            final ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, mProducts);
                            mList.setAdapter(adapter);


                        }else {
                            Log.d(TAG, "Error getting document: "+ task.getException());
                        }
                    }
                });


        //tests pour la listView





    }
}
