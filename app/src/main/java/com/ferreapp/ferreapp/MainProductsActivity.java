package com.ferreapp.ferreapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainProductsActivity extends AppCompatActivity{

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

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

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    Intent intent = new Intent(MainProductsActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(MainProductsActivity.this, R.style.MenuStyle);
                PopupMenu popup = new PopupMenu(wrapper, button);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.SC:
                                Intent intent = new Intent(MainProductsActivity.this, ShoppingCartActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.Orders:
                                Intent intent1 = new Intent(MainProductsActivity.this, SeeOrderActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.Comments:
                                Intent intent2 = new Intent(MainProductsActivity.this, CommentsActivity.class);
                                startActivity(intent2);
                                return true;
                            case R.id.Exit:
                                mAuth.signOut();
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popup.show(); //showing popup menu
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
                                        document.getString("amount"),
                                        document.getString("imageURL"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SC:
                Intent intent = new Intent(MainProductsActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
