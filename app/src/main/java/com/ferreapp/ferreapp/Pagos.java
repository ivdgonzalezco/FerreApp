package com.ferreapp.ferreapp;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pagos extends AppCompatActivity {

    String Total;
    String [][] carrito;
    Bundle extras;
    private int NUM_COLS;
    private int NUM_ROWS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos);
        extras = getIntent().getExtras();
        Total = extras.getString("ValorCompra");
        Intent este = this.getIntent();
        Bundle bundle = este.getExtras();
        carrito = (String[][]) bundle.getSerializable("carrito");

        NUM_COLS=carrito.length;
        NUM_ROWS=carrito[0].length;

    }

    private String Generar_factura(){
        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(612, 792, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(50, 50, 30, paint);

        // finish the page
        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

        return targetPdf;
    }
/*
    public String Generar_factura() {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
            String fechaHora=sdf.format(Calendar.getInstance().getTime());
            String pdfName = "Comprobante_"+ fechaHora;
            String fpath = "/sdcard/ferrapp/" + pdfName+ ".pdf";
            File file = new File(fpath);

            if (!file.exists()) {
                file.createNewFile();
            }

            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Document document = new Document();

            PdfWriter.getInstance(document,new FileOutputStream(file.getAbsoluteFile()));
            document.open();

            document.add(new Paragraph("FerraApp"));
            document.add(new Paragraph("Comprobante de Pago Nro. "+fechaHora));

            String load="";
            for(int i = 0; i<NUM_ROWS; i++){
                for(int j= 0; j<NUM_COLS; j++){
                    Log.i("fslog","Carga de carrito a PDF");
                    load=load+carrito[i][j]+"  ";
                }
                document.add(new Paragraph(load));
            }
            document.add(new Paragraph("Total Pagado: $ "+Total));
            document.close();

            return fpath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (DocumentException e) {
               e.printStackTrace();
            return "";
        }
    }
*/
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


