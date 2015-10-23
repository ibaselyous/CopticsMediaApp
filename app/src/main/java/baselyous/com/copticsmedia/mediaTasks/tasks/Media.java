package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * media contents
 */
@SuppressWarnings("ALL")
public class Media {

    private String title;
    private String content;
    private Drawable drawableContent;
    private List<String> contentList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public void addToContentList(String phrase) {
        contentList.add(phrase);
    }


    public Drawable getDrawableContent() {
        return drawableContent;
    }

    public void setDrawableContent(Drawable drawableContent) {
        this.drawableContent = drawableContent;
    }
}
