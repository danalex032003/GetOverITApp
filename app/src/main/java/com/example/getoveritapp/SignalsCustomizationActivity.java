package com.example.getoveritapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SignalsCustomizationActivity extends AppCompatActivity {

    private ListView listView;

    private int[] stopSignalImages = {R.drawable.stop_signal, R.drawable.stop_signal, R.drawable.stop_signal};

    private String[] optionNames = {"Option 1", "Option 2", "Option 3"};

    private String[] subscriptionTypes = {"Bronze", "Silver", "Gold"};

    private String[] blinkerSignalGifs = {"file:///android_asset/blinker_signal.gif", "file:///android_asset/blinker_signal.gif", "file:///android_asset/blinker_signal.gif"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signals_customization);

        listView = findViewById(R.id.signalsListView);

        final CustomAdaptor customAdaptor = new CustomAdaptor();
        listView.setAdapter(customAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SignalsCustomizationActivity.this, customAdaptor.getItem(i).toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return stopSignalImages.length;
        }

        @Override
        public Object getItem(int i) {
            return optionNames[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.single_signals_customization_item, null);

            ImageView stopSignalImageView = view1.findViewById(R.id.stopSignalImageView);
            TextView optionNameTextView = view1.findViewById(R.id.optionTextView);
            TextView subscriptionTypeTextView = view1.findViewById(R.id.subscriptionTypeTextView);
            WebView gifWebView = view1.findViewById(R.id.blinkerSignalWebView);

            stopSignalImageView.setImageResource(stopSignalImages[i]);
            optionNameTextView.setText(optionNames[i]);
            subscriptionTypeTextView.setText(subscriptionTypes[i]);

            WebSettings webSettings = gifWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            gifWebView.loadUrl(blinkerSignalGifs[i]);
            gifWebView.setBackgroundColor(Color.TRANSPARENT);
            gifWebView.setVisibility(View.VISIBLE);

            switch (subscriptionTypeTextView.getText().toString()) {
                case "Bronze":
                    subscriptionTypeTextView.setTextColor(Color.parseColor("#B08D57"));
                    break;
                case "Silver":
                    subscriptionTypeTextView.setTextColor(Color.parseColor("#C0C0C0"));
                    break;
                case "Gold":
                    subscriptionTypeTextView.setTextColor(Color.parseColor("#FFD700"));
            }

            return view1;
        }
    }
}