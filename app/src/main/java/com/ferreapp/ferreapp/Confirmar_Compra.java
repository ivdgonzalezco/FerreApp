package com.ferreapp.ferreapp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Confirmar_Compra extends AppCompatActivity {

    private String totalPayment;

    Resources res;
    Locale currentLocale = Locale.getDefault();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

    private TableLayout tablapos;

    ArrayList<Product> products = new ArrayList<>();

    ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_compra);

        tablapos = findViewById(R.id.tablaCompras);

        products = ProductSingleton.getInstance().getProducts();

        orders = OrderSingleton.getInstance().getOrders();

        llenar_tabla();
        calc_total();
    }

    public void comprar(View view) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        String orderDate = formatter.format(date);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email;

        if (user != null) {
            // Name, email address, and profile photo Url
            email = user.getEmail();
        } else {
            email = "";
        }

        Order order = new Order(email, totalPayment, orderDate,products);

        orders.add(order);

        OrderSingleton.getInstance().setOrders(orders);

        Intent compra=new Intent(this,Pagos.class);
        startActivity(compra);
    }

    public void llenar_tabla() {

        for(int i = 0; i<products.size(); i++){
            TableRow tableRow = new TableRow(this);
            tablapos.addView(tableRow);

            for(int j= 0; j < 4; j++){
                TextView tv1 = new TextView(this);
                tv1.setTextColor(Color.BLACK);
                if (j == 0){
                    tv1.setText(products.get(i).getProductName());
                }else if(j == 1){
                    tv1.setText(products.get(i).getProductPrice());
                }else if(j == 2){
                    tv1.setText(products.get(i).getProductAmount());
                }else{
                    res = getResources();

                    Integer count = Integer.parseInt(products.get(i).getProductPrice().replace("$", "").replace(" ", "").replace(".", "")) * Integer.parseInt(products.get(i).getProductAmount());
                    tv1.setText(currencyFormatter.format(count));
                }
                tableRow.addView(tv1);
            }
        }

    }

    public void calc_total() {
        Double suma = 0.0;

        for (int i = 0; i <products.size(); i++) {
            suma = suma + (Integer.parseInt(products.get(i).getProductPrice()
                    .replace("$", "")
                    .replace(" ", "").replace(".", "")) * Integer.parseInt(products.get(i).getProductAmount()));
        }

        TextView tot= findViewById(R.id.totalPagar);

        res = getResources();

        tot.setText(String.format(res.getString(R.string.total_payment), currencyFormatter.format(suma)));

        totalPayment = currencyFormatter.format(suma);
    }

}