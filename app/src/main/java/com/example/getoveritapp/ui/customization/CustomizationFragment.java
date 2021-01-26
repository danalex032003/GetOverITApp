package com.example.getoveritapp.ui.customization;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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

import com.example.getoveritapp.R;
import com.example.getoveritapp.SignalsCustomizationActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomizationFragment extends Fragment {

    private ListView listView;

    private int[] stopSignalImages = {R.drawable.stop_signal, R.drawable.stop_signal, R.drawable.stop_signal};

    private String[] optionNames = {"Option 1", "Option 2", "Option 3"};

    private String[] subscriptionTypes = {"Bronze", "Silver", "Gold"};

    private String[] blinkerSignalGifs = {"file:///android_asset/blinker_signal.gif", "file:///android_asset/blinker_signal.gif", "file:///android_asset/blinker_signal.gif"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomizationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomizationFragment newInstance(String param1, String param2) {
        CustomizationFragment fragment = new CustomizationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customization, container, false);

        listView = rootView.findViewById(R.id.signalsListView);

        final CustomizationFragment.CustomAdaptor customAdaptor = new CustomAdaptor();
        listView.setAdapter(customAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), customAdaptor.getItem(i).toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
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