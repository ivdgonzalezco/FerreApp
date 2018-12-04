package com.ferreapp.ferreapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainProductsActivity extends AppCompatActivity{

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ImageButton button;
    private EditText mSearchEditText;

    RecyclerView mRecyclerView;
    RecyclerViewFBAdapter recyclerViewAdapter;

    ArrayList<Product> products = new ArrayList<>();

    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_products_activity);

        button = findViewById(R.id.imageView);
        mSearchEditText = findViewById(R.id.searchEditText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainProductsActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });

        db = FirebaseFirestore.getInstance();

        mRecyclerView = findViewById(R.id.RecyclerViewHighlighted);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDataFromFirebase();

        recyclerViewAdapter = new RecyclerViewFBAdapter(this, products);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchFilter(s.toString());
            }
        });

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                ArrayList<Product> productToSingleton= ProductSingleton.getInstance().getProducts();

                Log.d("WTF", "Size: " + productToSingleton.size());
                productToSingleton.add(recyclerViewAdapter.getProductList().get(position));
                ProductSingleton.getInstance().setProducts(productToSingleton);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    private void loadDataFromFirebase() {

        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Product product = new Product(
                                        document.getString("name"),
                                        document.getString("brand"),
                                        document.getString("description"),
                                        document.getString("price"),
                                        document.getString("amount"));
                                products.add(product);
                            }
                        } else {
                            Log.d("Error fiding the docs", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void searchFilter(String searchParameter){
        ArrayList<Product> filteredProducts = new ArrayList<>();

        for(Product product : products){
            if(product.getProductName().toLowerCase().contains(searchParameter.toLowerCase())){
                filteredProducts.add(product);
            }
        }

        recyclerViewAdapter.filteredList(filteredProducts);
    }
}
