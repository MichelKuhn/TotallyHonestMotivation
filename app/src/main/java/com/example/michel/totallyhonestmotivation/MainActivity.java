package com.example.michel.totallyhonestmotivation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Motivation motivation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        motivation = new Motivation(getApplicationContext(), (TextView) findViewById(R.id.quoteView), (ImageView) findViewById(R.id.imageView), findViewById(R.id.activity));
        motivation.generateMe();
    }

    public void createQuote(View view) {
        motivation.generateMe();
    }

    public void saveMyQuote(View view) {
        motivation.saveMe();
    }
}