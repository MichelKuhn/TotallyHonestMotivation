package com.example.michel.totallyhonestmotivation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.res.TypedArray;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createQuote(View view) {
        QuoteGenerator quoteGenerator = new QuoteGenerator();
        TextView quoteTextView = (TextView)findViewById(R.id.quoteView);
        quoteTextView.setText(quoteGenerator.generateQuote());
        ImageView quoteImageView = (ImageView) findViewById(R.id.imageView);
        TypedArray images = getResources().obtainTypedArray(R.array.background);
        int choice = (int) (Math.random() * images.length());
        quoteImageView.setImageResource(images.getResourceId(choice, R.drawable.pic1));
        images.recycle();
    }
}
