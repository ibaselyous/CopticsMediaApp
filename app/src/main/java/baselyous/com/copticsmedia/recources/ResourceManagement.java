package baselyous.com.copticsmedia.recources;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import baselyous.com.copticsmedia.mediaTasks.tasks.Media;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 */
public class ResourceManagement {


    public static MediaContents readAgpeyaContents(Context context, String language) {
        MediaContents contents = new MediaContents();


        AssetManager assets = context.getAssets();
        try {
            String[] list = assets.list("agpeya/" + language);
            Log.i("list retrieved", "" + list.length);
            for (String fileName : list) {
                Log.i("file name ", "" + fileName);

                InputStream is = assets.open("agpeya/" + language + "/" + fileName);
                String str;
                try {
                    Media media = new Media();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder content = new StringBuilder();

                    String title = reader.readLine();
                    media.setTitle(title);

                    content.append(title).append("\n");
                    while ((str = reader.readLine()) != null) {
                        content.append(str).append("\n");
                        media.addToContentList(str);
                    }
                    media.setContent(content.toString());
                    contents.addToMediaList(media);
                } finally {
                    try {
                        is.close();
                    } catch (Throwable ignore) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
}
