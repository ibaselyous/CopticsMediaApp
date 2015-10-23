package baselyous.com.copticsmedia.mediaTasks.tasks;

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
    private byte[] drawableContent;
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


    public byte[] getDrawableContent() {
        return drawableContent;
    }

    public void setDrawableContent(byte[] drawableContent) {
        this.drawableContent = drawableContent;
    }
}
