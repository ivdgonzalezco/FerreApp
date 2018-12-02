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
    String Total = "";
    Bundle bundle;
    private TableLayout tablapos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_compra);
        tablapos = (TableLayout) findViewById(R.id.tablaCompras);
        //llenar_tabla();
        //calc_total();
    }

    public void comprar(View view) {

        Intent compra=new Intent(this,Pagos.class);
        //compra.putExtra("ValorCompra",Total);
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("carrito", carrito);
        //compra.putExtras(bundle);
        startActivity(compra);
    }


    public void llenar_tabla() {
        if (tablapos.getChildCount() > 1) {
            Log.i("fslog", "nrows=" + tablapos.getChildCount());
            int filas = tablapos.getChildCount();
            tablapos.removeViews(1, filas - 1);
            for (int i = 0; i < carrito[i].length; i++) {
                Log.i("fslog", "matriz[i].length=" + carrito[i].length);
                TableRow fila = new TableRow(this);
                fila.setBackgroundColor(Color.parseColor("#FFFFFF"));

                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);
                TextView tv4 = new TextView(this);

                tv1.setText(carrito[0][i]);
                tv2.setText(carrito[1][i]);
                tv3.setText(carrito[2][i]);
                tv4.setText(carrito[3][i]);

                Log.i("fslog",carrito[0][i]+" - "+carrito[1][i]+
                        " - "+carrito[2][i]+" - "+carrito[3][i]);

                tablapos.addView(fila);
            }

        } else {
            for (int i = 0; i < carrito[i].length; i++) {
                Log.i("fslog", "matriz[i].length=" + carrito[i].length);
                TableRow fila = new TableRow(this);
                fila.setBackgroundColor(Color.parseColor("#FFFFFF"));

                TextView tv1 = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);
                TextView tv4 = new TextView(this);

                tv1.setText(carrito[0][i]);
                tv2.setText(carrito[1][i]);
                tv3.setText(carrito[2][i]);
                tv4.setText(carrito[3][i]);

                Log.i("fslog",carrito[0][i]+" - "+carrito[1][i]+
                        " - "+carrito[2][i]+" - "+carrito[3][i]);

                tablapos.addView(fila);

            }

        }

    }

    public void calc_total() {
        double suma = 0;

        for (int i = 0; i < carrito[i].length; i++) {
            suma = suma + Double.parseDouble(carrito[3][i]);

        }
        Total=""+suma;
        TextView tot= (TextView) findViewById(R.id.totalPagar);
        tot.setText("Total a Pagar: $"+ Total);
    }
}
