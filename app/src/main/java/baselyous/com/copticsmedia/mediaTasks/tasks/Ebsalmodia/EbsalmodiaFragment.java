package baselyous.com.copticsmedia.mediaTasks.tasks.Ebsalmodia;

import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class EbsalmodiaFragment extends MediaDetailFragment {


    @Override
    protected int getTask() {
        return 2;
    }

    @Override
    protected MediaContents getMediaContents(String language, String itemClicked) {
        return null;
    }

    @Override
    protected String getItemAssetDirectory(int index) {
        return null;
    }


    @Override
    public List<String> getBookContents(String selectedLanguge) {
        return null;
    }

    @Override
    public List<Integer> getBookContentIconList() {
        return null;
    }

    @Override
    public String getTaskName() {
        return "ebsalmodia";
    }
}
