package com.ferreapp.ferreapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Confirmar_Compra extends AppCompatActivity {


    private String[][] carrito = {
            {"Tornillos", "100", "10", "1000"},
            {"Chasos", "100", "10", "1000"},
            {"Broca 1/2", "4500", "20", "9000"},
            {"Pintura", "23000", "1", "23000"}
    };

    private int NUM_COLS=carrito.length;
    private int NUM_ROWS=carrito[0].length;
    String Total = "";
    Bundle bundle;
    private TableLayout tablapos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_compra);
        tablapos = (TableLayout) findViewById(R.id.tablaCompras);
        llenar_tabla();
        calc_total();
    }

    public void comprar(View view) {

        Intent compra=new Intent(this,Pagos.class);
        compra.putExtra("ValorCompra",Total);
        bundle = new Bundle();
        bundle.putSerializable("carrito", carrito);
        compra.putExtras(bundle);
        startActivity(compra);
    }


    public void llenar_tabla() {

        for(int i = 0; i<NUM_ROWS; i++){
            TableRow tableRow = new TableRow(this);
            tablapos.addView(tableRow);

            for(int j= 0; j<NUM_COLS; j++){
                TextView tv1 = new TextView(this);
                tv1.setText(carrito[i][j]);
                tv1.setTextColor(Color.parseColor("#3f51b5"));
                Log.i("fslog","carrito["+i+"]["+j+"]="+carrito[i][j]);
                tableRow.addView(tv1);
            }
        }

    }

    public void calc_total() {
        double suma = 0;

        for (int i = 0; i <NUM_ROWS; i++) {
            suma = suma + Double.parseDouble(carrito[i][3]);
        }
        Log.i("fslog","Pagar: "+suma);
        Total=""+suma;
        TextView tot= (TextView) findViewById(R.id.totalPagar);
        tot.setText("Total a Pagar: $"+ Total);
    }
}