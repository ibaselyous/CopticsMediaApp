package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class KholagyFragment extends MediaDetailFragment {

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
        return "kholagy";
    }
}
