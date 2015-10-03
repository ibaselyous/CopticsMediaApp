package baselyous.com.copticsmedia.mediaTasks.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * <p/>
 * Agpeya has
 * 1. every hour contents
 * a.
 * 1. hours pray
 * 2. each hour contains contents Mazamir, bible
 * 3. each content has text with phrases
 */
public class MediaContents {

    private List<Media> mediaList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public void addToMediaList(Media media) {
        mediaList.add(media);
        titleList.add(media.getTitle());
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }
}
