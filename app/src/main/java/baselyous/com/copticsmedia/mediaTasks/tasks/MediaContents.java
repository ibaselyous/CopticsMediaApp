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

    private List<Media> languageContentMediaList = new ArrayList<>(); //for arabic english german french
    private List<Media> copticContentMediaList = new ArrayList<>();
    private List<Media> languageCopticCombinedMediaList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private int numberOfPages;

    public List<Media> getCopticContentMediaList() {
        return copticContentMediaList;
    }

    public void setCopticContentMediaList(List<Media> copticContentMediaList) {
        this.copticContentMediaList = copticContentMediaList;
    }

    public List<Media> getLanguageCopticCombinedMediaList() {
        return languageCopticCombinedMediaList;
    }

    public void setLanguageCopticCombinedMediaList(List<Media> languageCopticCombinedMediaList) {
        this.languageCopticCombinedMediaList = languageCopticCombinedMediaList;
    }

    public List<Media> getLanguageContentMediaList() {
        return languageContentMediaList;
    }

    public void setLanguageContentMediaList(List<Media> languageContentMediaList) {
        this.languageContentMediaList = languageContentMediaList;
    }

    public void addToLanguageMediaList(Media media) {
        languageContentMediaList.add(media);
        titleList.add(media.getTitle());
    }

    public void addToCopticMediaList(Media media) {
        copticContentMediaList.add(media);
    }

    public void addToLanguageCopticCombinedMediaList(Media media) {
        languageCopticCombinedMediaList.add(media);
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
