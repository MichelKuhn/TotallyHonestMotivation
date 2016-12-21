package com.example.michel.totallyhonestmotivation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuoteGenerator quoteGenerator = new QuoteGenerator();
        TextView quoteTextView = (TextView)findViewById(R.id.quoteView);
        quoteTextView.setText(quoteGenerator.generateQuote());
    }
}
