package com.ferreapp.ferreapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.LongHashtable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class Pagos extends AppCompatActivity {

    private TableLayout tablapos;

    ArrayList<Product> products = new ArrayList<>();

    Resources res;
    Locale currentLocale = Locale.getDefault();
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos);

        products = ProductSingleton.getInstance().getProducts();

    }

    private String Generar_factura(){

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fechaHora  = sdf.format(Calendar.getInstance().getTime());

        res = getResources();

        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(612, 792, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.bill_layout, null);

        int measureWidth = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getWidth(), View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(page.getCanvas().getHeight(), View.MeasureSpec.EXACTLY);

        content.measure(measureWidth, measuredHeight);
        content.layout(0, 0, page.getCanvas().getWidth(), page.getCanvas().getHeight());

        TextView date = content.findViewById(R.id.dateBill);
        date.setText(fechaHora);

        tablapos = content.findViewById(R.id.tablaComprasBill);

        for(int i = 0; i<products.size(); i++) {
            TableRow tableRow = new TableRow(content.getContext());
            tablapos.addView(tableRow);
            TextView tv1 = new TextView(content.getContext());
            tv1.setTextColor(Color.BLACK);
            tv1.setText("YOLO");
            tableRow.addView(tv1);
        }

        Integer suma = 0;

        for (int k = 0; k <products.size(); k++) {
            suma = suma + (Integer.parseInt(products.get(k).getProductPrice()
                    .replace("$", "")
                    .replace(" ", "").replace(".", "")) * Integer.parseInt(products.get(k).getProductAmount()));
        }

        TextView tot= content.findViewById(R.id.totalPagarBill);

        tot.setText(String.format(res.getString(R.string.total_payment), currencyFormatter.format(suma)));

        content.draw(page.getCanvas());

        // finish the page
        document.finishPage(page);

        // write the document content
        String pdfName = "Comprobante_"+ fechaHora;
        String fpath = "/sdcard/" + pdfName+ ".pdf";
        File filePath = new File(fpath);
        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

        return fpath;
    }

    public void generar(View view) {

        String mensaje = Generar_factura();
        if (!mensaje.equals("")){
            Toast.makeText(this, "Comprobante generado: "+mensaje, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "I/O error", Toast.LENGTH_SHORT).show();
        }
    }

    public void calificar(View view) {
        Intent i = new Intent(this, Calificar_Pedido.class);
        startActivity(i);
    }
}


