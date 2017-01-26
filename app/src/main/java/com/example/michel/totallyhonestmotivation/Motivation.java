package com.example.michel.totallyhonestmotivation;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class Motivation {
    private Context context;
    private ImageView imageView;
    private TextView textView;
    private View activityView;
    private String quote;
    private QuoteGenerator generator;
    private TypedArray images;

    Motivation(Context context, TextView textView, ImageView imageView, View activityView) {
        this.context = context;
        this.textView = textView;
        this.imageView = imageView;
        this.activityView = activityView;
        generator = new QuoteGenerator(context);
        images = context.getResources().obtainTypedArray(R.array.background);
    }

    void generateMe() {
        int choice = (int) (Math.random() * images.length());
        imageView.setImageResource(images.getResourceId(choice, R.drawable.pic1));

        quote = generator.generateQuote();
        textView.setText(quote);
    }

    private static Bitmap getBitmapFromView(View view, View pictureView) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        pictureView.draw(canvas);
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
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
    }

    private void storeImage(final Bitmap image) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground( final Void ... params ) {
                File pictureFile = getOutputMediaFile();
                if (pictureFile == null) {
                    Log.d("ERR",
                            "Error creating media file, check storage permissions: ");// e.getMessage());
                    return null;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    image.compress(Bitmap.CompressFormat.JPEG, 90, fos);
                    byte[] bytes = new byte[32 * 1024];
                    for (long l = 0; l < 500 * 1000 * 1000; l += bytes.length)
                        fos.write(bytes);
                    fos.close();
                    MediaScannerConnection.scanFile(context,
                            new String[] { pictureFile.toString() }, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("ExternalStorage", "Scanned " + path + ":");
                                    Log.i("ExternalStorage", "-> uri=" + uri);
                                }
                            });
                } catch (FileNotFoundException e) {
                    Log.d("ERR", "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d("ERR", "Error accessing file: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute( final Void result ) {

            }
        }.execute();
    }

    void saveMe() {
        storeImage(getBitmapFromView(imageView, activityView));
    }
}
