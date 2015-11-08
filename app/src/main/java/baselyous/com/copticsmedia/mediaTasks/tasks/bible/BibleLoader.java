package baselyous.com.copticsmedia.mediaTasks.tasks.bible;

import android.os.AsyncTask;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Ihab Baselyous on 25.10.2015.
 */
public class BibleLoader extends AsyncTask<String, String, String> {

    private WebView webView;
    private String url;

    BibleLoader(WebView view, String url) {
        this.webView = view;
        this.url = url;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Document document = Jsoup.connect(params[0]).get();
            document.getElementsByClass("vcenter").remove();
            document.getElementsByClass("widget").remove();
            document.getElementById("footer").remove();
            document.getElementsByClass("mobile-client").remove();
            return document.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s != null){
            webView.loadDataWithBaseURL(url, s, "text/html", "utf-8", "");
        }

    }

}
