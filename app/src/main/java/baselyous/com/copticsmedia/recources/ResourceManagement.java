package baselyous.com.copticsmedia.recources;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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

/*            String filePath ="";

            if (task.equals("agpeya")) {
                filePath = getAgpeyaFilePath(language, taskContentFolder);
            }
            else if (task.equals("kholagy")){
                filePath = getKholagyFilesPath(language, taskContentFolder);
            }*/

            String filePath = task + "/" +
                    (language.isEmpty() ? "" : language + "/") +
                    (taskContentFolder.isEmpty() ? "" : taskContentFolder) ;


            String[] list = assets.list(filePath);
            for (String fileName : list) {
                if (!fileName.equals("salawat.txt")) {

                    if (task.equals("agpeya")) {
                        getOneLangugeContents(contents, filePath, fileName, assets);
                    } else if (task.equals("kholagy")) {
                        getCombinedLanguageContents(contents, filePath, fileName, assets, language);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    private static String getKholagyFilesPath(String language, String taskContentFolder) {

        return null;
    }

    public static String getAgpeyaFilePath (String language, String subTaskFolder){
        return "agpeya/" +
                (language.isEmpty() ? "" : language + "/") +
                (subTaskFolder.isEmpty() ? "" : subTaskFolder) ;
    }


    private static void getCombinedLanguageContents(MediaContents contents, String filePath, String fileName, AssetManager assets, String language) throws IOException {
        /*getOneLangugeContents(contents, filePath, fileName, assets);
        contents.addToCopticMediaList(getMedia(filePath + "/coptic_" + fileName, assets));
        contents.addToLanguageCopticCombinedMediaList(getMedia(filePath + "/combined_" + fileName, assets));*/
        if(fileName.endsWith("_" + language + ".png")) {
            contents.addToLanguageMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
        }
        else if (fileName.endsWith("_" + language + "_coptics.png")) {
            contents.addToLanguageCopticCombinedMediaList(getDrawableMedia(filePath + "/combined_" + fileName, assets));
        }
        else if (fileName.endsWith("_coptic.png")){
            contents.addToCopticMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
        }
    }

    private static  Media getDrawableMedia (String filePath, AssetManager assets) {
        InputStream ims = null;
        try {
            ims = assets.open(filePath);
            Drawable d = Drawable.createFromStream(ims, null);
            Media media = new Media() ;
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] bitmapdata = stream.toByteArray();
            media.setDrawableContent(bitmapdata);
            return media;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ims != null){
                try {
                    ims.close();
                } catch (IOException ignored) {

                }
            }
        }
        return null;
    }


    private static Media getMedia(String filePath, AssetManager assets) {
        String str;
        InputStream is = null;
        try {
            Media media = new Media();
            is = assets.open(filePath);

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

        } catch (IOException ignored) {

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    private static void getOneLangugeContents(MediaContents contents, String filePath, String fileName, AssetManager assets) throws IOException {
        contents.addToLanguageMediaList(getMedia(filePath + "/" + fileName, assets));
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
