package baselyous.com.copticsmedia.recources;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.tasks.Media;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * class used to read from assets folder
 */
public class ResourceManagement {


    public static MediaContents readMediaTaskContents(Context context, String task, String language, String taskContentFolder) {
        MediaContents contents = new MediaContents();
        AssetManager assets = context.getAssets();
        try {

            String filePath = "" +
                    (task.isEmpty() ? "" : task + "/") +
                    (language.isEmpty() ? "" : language + "/") +
                    (taskContentFolder.isEmpty() ? "" : taskContentFolder);
            Log.i("reached get media ", filePath);
            String[] list = assets.list(filePath);
            for (String fileName : list) {
                if (!fileName.equals("salawat.txt")) {
                    InputStream is = assets.open(filePath + "/" + fileName);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }


    // used to read a file an convert it to arrayList of stings.

    /**
     * for example Agpeya, arabic, file
     *
     * @param context          context used to acquire service
     * @param task             task (book)
     * @param selectedLanguage language
     * @param taskFileName     file name
     * @return file contents as a list
     */
    public static List<String> readTaskContentsFile(Context context, String task, String selectedLanguage, String taskContentFolder, String taskFileName) {

        AssetManager assets = context.getAssets();
        List<String> contents = new ArrayList<>();
        try {

            String filePath = "" +
                    (task.isEmpty() ? "" : task + "/") +
                    (selectedLanguage.isEmpty() ? "" : selectedLanguage.toLowerCase() + "/") +
                    (taskContentFolder.isEmpty() ? "" : taskContentFolder.toLowerCase() + "/") +
                    (taskFileName.isEmpty() ? "" : taskFileName.toLowerCase() + ".txt");

            Log.i("bath to file ", filePath);

            InputStream is = assets.open(filePath);
            String str;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                while ((str = reader.readLine()) != null) {
                    contents.add(str);
                }
            } finally {
                try {
                    is.close();
                } catch (Throwable ignore) {

                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return contents;
    }


}
