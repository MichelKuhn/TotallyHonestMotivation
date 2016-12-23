package com.example.michel.totallyhonestmotivation;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.TypedArray;
import java.io.FileOutputStream;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createQuote(View view) {
        QuoteGenerator quoteGenerator = new QuoteGenerator(getApplicationContext());
        TextView quoteTextView = (TextView)findViewById(R.id.quoteView);
        quoteTextView.setText(quoteGenerator.generateQuote());
        ImageView quoteImageView = (ImageView) findViewById(R.id.imageView);
        TypedArray images = getResources().obtainTypedArray(R.array.background);
        int choice = (int) (Math.random() * images.length());
        quoteImageView.setImageResource(images.getResourceId(choice, R.drawable.pic1));
        images.recycle();
    }

    public void saveImage(View view) {

    }
}
