package com.example.michel.totallyhonestmotivation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.TypedArray;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static com.example.michel.totallyhonestmotivation.R.id.imageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createQuote(View view) {
        QuoteGenerator quoteGenerator = new QuoteGenerator(getApplicationContext());
        TextView quoteTextView = (TextView) findViewById(R.id.quoteView);
        quoteTextView.setText(quoteGenerator.generateQuote());
        ImageView quoteImageView = (ImageView) findViewById(imageView);
        TypedArray images = getResources().obtainTypedArray(R.array.background);
        int choice = (int) (Math.random() * images.length());
        quoteImageView.setImageResource(images.getResourceId(choice, R.drawable.pic1));
        images.recycle();
    }

    public static Bitmap getBitmapFromView(View view, View pictureView) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        // draw the view on the canvas
        pictureView.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Motivation");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Motivation", "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.GERMAN).format(new Date());
        File mediaFile;
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        return mediaFile;
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("ERR",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("ERR", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("ERR", "Error accessing file: " + e.getMessage());
        }
    }

    public void saveMyQuote(View view) {
        storeImage(getBitmapFromView(findViewById(R.id.imageView), findViewById(R.id.activity)));
    }
}