package baselyous.com.copticsmedia.recources;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.tasks.Media;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.EbsalmodiaPlaceHolder;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.util.ImageFetcher;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * class used to read from assets folder
 */
public class ResourceManagement {


    public static MediaContents readMediaTaskContents(Context context, String task, String language, String taskContentFolder) {
        MediaContents contents = new MediaContents();
        AssetManager assets = context.getAssets();
        try {

            String filePath = getFilePath(task, language, taskContentFolder);

            Log.i("filepath , " , " " + filePath);
            String[] list = assets.list(filePath);
            if (task.equals("ebsalmodia")){
                contents.setNumberOfPages(list.length / 6);
                //getMediaContentsSizes(contents, filePath, fileName, assets, language);
                //getEbsalmodiaContents(contents, filePath, fileName, assets, language);
                //getCombinedLanguageContents(contents, filePath, fileName, assets, language);
            }
            for (String fileName : list) {
                if (!fileName.equals("salawat.txt")) {
                    if (task.equals("agpeya")) {
                        getOneLangugeContents(contents, filePath, fileName, assets);
                    } else if (task.equals("kholagy")) {
                        //getCombinedLanguageContents(contents, filePath, fileName, assets, language);
                    } else if (task.equals("ebsalmodia")){
                       // getMediaContentsSizes(contents, filePath, fileName, assets, language);
                        //getEbsalmodiaContents(contents, filePath, fileName, assets, language);
                        //getCombinedLanguageContents(contents, filePath, fileName, assets, language);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }

    private static void getMediaContentsSizes(MediaContents contents, String filePath, String fileName, AssetManager assets, String language) {

    }

    public static String getFilePath(String task, String language, String taskContentFolder) {
        return task + "/" +
                (task.equals("ebsalmodia") ? "" : language.isEmpty() ? "" : language + "/") +
                (taskContentFolder.isEmpty() ? "" : taskContentFolder);
    }

    private static void getEbsalmodiaContents(Context context, String taskContentFolder, String language, int index) throws IOException {
        MediaContents contents = new MediaContents();
        AssetManager assets = context.getAssets();
        String filePath = getFilePath("ebsalmodia", language, taskContentFolder);

        Log.i("filepath , " , " " + filePath);
        String[] list = assets.list(filePath);
        String indexString = index < 10 ? "_0"+ (index + 1):"_" + (index + 1);

        for (String fileName : list) {
            if (!fileName.equals("salawat.txt")) {
                if (fileName.endsWith(indexString + "_" + language + ".PNG")) {
                    contents.addToLanguageMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
                } else if (fileName.endsWith(indexString + "_" + language + "_coptic.PNG")) {
                    contents.addToLanguageCopticCombinedMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
                } else if (fileName.endsWith(indexString + "_coptic.PNG")) {
                    contents.addToCopticMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
                }
                    //getEbsalmodiaContents(contents, filePath, fileName, assets, language);
                    //getCombinedLanguageContents(contents, filePath, fileName, assets, language);

            }
        }
    }


    private static void getCombinedLanguageContents(MediaContents contents, String filePath, String fileName, AssetManager assets, String language, int index) throws IOException {
        /*getOneLangugeContents(contents, filePath, fileName, assets);
        contents.addToCopticMediaList(getMedia(filePath + "/coptic_" + fileName, assets));
        contents.addToLanguageCopticCombinedMediaList(getMedia(filePath + "/combined_" + fileName, assets));*/
        Log.i("file name ", filePath + "" + fileName);
        if (fileName.endsWith("_" + language + ".PNG")) {
            contents.addToLanguageMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
        } else if (fileName.endsWith("_" + language + "_coptic.PNG")) {
            contents.addToLanguageCopticCombinedMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
        } else if (fileName.endsWith("_coptic.PNG")) {
            contents.addToCopticMediaList(getDrawableMedia(filePath + "/" + fileName, assets));
        }
    }

    private static Media getDrawableMedia(String filePath, AssetManager assets) {
        InputStream ims = null;
        try {
            ims = assets.open(filePath);
            Drawable d = Drawable.createFromStream(ims, null);
            Media media = new Media();
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] bitmapdata = stream.toByteArray();
            media.setDrawableContent(bitmapdata);
            return media;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ims != null) {
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
                    (task.equals("ebsalmodia")? "" : selectedLanguage.isEmpty() ? "" : selectedLanguage.toLowerCase() + "/") +
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
        } catch (IOException igonre) {
            //e1.printStackTrace();
        }
        return contents;
    }





    public static int getEbsalmodiaPrayNrOfPages (FragmentActivity activity, String praySelected, String language){
        String filePath = getFilePath("ebsalmodia", "", praySelected);
        AssetManager assets = activity.getAssets();
        try {
            String[] list = assets.list(filePath);
            for (String fileName : list) {
                if (fileName.equals("count.txt")) {
                    InputStream is = assets.open(filePath + "/" + fileName);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String str ;
                    return Integer.parseInt((str = reader.readLine()) != null ? str : "0");
                }
            }

        } catch (IOException ignore) {

        }
        return 0;
    }

    public static int getPrayNrOfPages(FragmentActivity activity,String book, String praySelected, String selectedLanguage) {


        String filepath = getFilePath(book, selectedLanguage.toLowerCase(), praySelected);
        AssetManager assets = activity.getAssets();
        try {
            return assets.list(filepath).length;
        } catch (IOException ignore) {

        }
        return 0;
    }

    public static String getTextContent(String book, int pageNr, String praySelected, String language, FragmentActivity activity) {
        AssetManager assets = activity.getAssets();
        String filePath = getFilePath(book.toLowerCase(), language.toLowerCase(), praySelected);
        Log.i("file path " , filePath);
        String str;
        InputStream is = null;
        StringBuilder content = new StringBuilder();
        try {
            String[] list = assets.list(filePath);
            String fileSelected = list[pageNr];
            is = assets.open(filePath + "/" + fileSelected);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String title = reader.readLine();
            content.append(title).append("\n");
            while ((str = reader.readLine()) != null) {
                content.append(str).append("\n");
            }


        } catch (IOException e) {
            return "";
        }finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (Throwable ignore) {

            }
        }
        return content.toString();
    }

    public static List<String> getPraySelectedPageTitleList(Context context, String book, String languageSelected, String praySelected) {
        AssetManager assets = context.getAssets();
        List<String> titleString = new ArrayList<>();
        try {

            String filePath = getFilePath(book, languageSelected, praySelected);
            Log.i("filepath , " , " " + filePath);
            String[] list = assets.list(filePath);

            for (String fileName : list) {
                if (!fileName.equals("salawat.txt")) {
                    titleString .add(getTitle(filePath + "/" + fileName, assets));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titleString;
    }

    private static String getTitle(String filePath, AssetManager assets) {
        InputStream is = null;
        try {
            is = assets.open(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.readLine();
        } catch (IOException ignored) {

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {

                }
            }
        }
        return "error...";
    }

    public static byte[] getDrawableImageResource(Context context, String book, String praySelected, int index, String language, int forWhichCombination) {
        AssetManager assets = context.getAssets();
        String folderPath = getFilePath(book, language, praySelected);
        String indexString = index < 10 ? "_0"+ (index + 1):"_" + (index + 1);
        String filename = "" ;
        switch (forWhichCombination) {
            case EbsalmodiaPlaceHolder.ONLY_LANGUAGE: {
                filename = indexString + "_" + language + ".PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.ONLY_COPTIC: {
                filename = indexString + "_coptic.PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.LANGUAGE_COPTIC:{
                filename = indexString + "_" + language + "_coptic.PNG";
            }
            break;
        }
        try {
            InputStream is = assets.open(folderPath + "/" + filename);
            Drawable d = Drawable.createFromStream(is, null);
            Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            return stream.toByteArray();

        } catch (IOException ignore) {

        }
        return new byte[0];
    }

    public static Bitmap getBitmap(String data, Context context) {

        AssetManager assets = context.getAssets();
        try {
            InputStream is = assets.open(data);
            Drawable d = Drawable.createFromStream(is, null);
            /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);*/
            return ((BitmapDrawable) d).getBitmap();

        } catch (IOException ignore) {

        }
        return null;
    }

    public static boolean updateOutputStream(String urlString, OutputStream outputStream, Context context) {


        BufferedOutputStream out = null;
        BufferedInputStream in = null;

        try {
            Log.e("url string", urlString);
            AssetManager assets = context.getAssets();
            InputStream is = assets.open(urlString);
            in = new BufferedInputStream(is, ImageFetcher.IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream, ImageFetcher.IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            //Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
            }
        }
        return false;
    }
}
