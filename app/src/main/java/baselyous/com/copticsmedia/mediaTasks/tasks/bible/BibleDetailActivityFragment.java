package baselyous.com.copticsmedia.mediaTasks.tasks.bible;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import baselyous.com.copticsmedia.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BibleDetailActivityFragment extends Fragment {

    private WebView webView;

    public BibleDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bible_detail, container, false);
        inflateViews(view);
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void inflateViews(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        BibleLoader loader = new BibleLoader(webView, getUrl());

        loader.execute(getUrl());


    }

    private String getUrl() {
        return null;
    }

}
