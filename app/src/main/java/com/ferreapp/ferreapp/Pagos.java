package com.ferreapp.ferreapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Pagos extends AppCompatActivity {

    String Total;
    List<String> carrito;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos);
        extras = getIntent().getExtras();
        Total=extras.getString("Total");
        Intent este = this.getIntent();
        Bundle bundle = este.getExtras();
        carrito= (List<String>) bundle.getSerializable("carrito");


    }

    public String Generar_factura(){

        PdfDocument document = new PdfDocument();

        // repaint the user's text into the page
        View content = findViewById(R.id.tablaCompras);

        // crate a page description
        int pageNumber = 1;
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(),
                content.getHeight() - 20, pageNumber).create();

        // create a new page from the PageInfo
        PdfDocument.Page page = document.startPage(pageInfo);

        content.draw(page.getCanvas());

        // do final processing of the page
        document.finishPage(page);

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String pdfName = "pdfdemo"
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

        File outputFile = new File("/sdcard/FerreApp/", pdfName);

        try {
            outputFile.createNewFile();
            OutputStream out = new FileOutputStream(outputFile);
            document.writeTo(out);
            document.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile.getPath();
    }

    public void generar(View view) {

        String mensaje= Generar_factura();
        Toast.makeText(this, mensaje,Toast.LENGTH_LONG).show();
    }

    public void calificar(View view) {
        Intent i= new Intent(this,Calificar_Pedido.class);
        startActivity(i);
    }
}


