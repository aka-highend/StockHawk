package com.rfachrur.stockhawk.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.rfachrur.stockhawk.R;

/**
 * Created by rfachrur on 11/24/16.
 */

public class SecondFragment extends Fragment {

    WebView webView;
    String stock;


    public SecondFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.second_fragment, container, false);

        Bundle symbol = getArguments();
        stock = symbol.getString(getResources().getString(R.string.symbol_bundle));
        webView = (WebView) rootView.findViewById(R.id.web_chart);
        webView.getSettings().setJavaScriptEnabled(true);
        String s = "http://empyrean-aurora-455.appspot.com/charts.php?symbol="+stock;
        webView.loadUrl(s);
        return rootView;
    }

}
