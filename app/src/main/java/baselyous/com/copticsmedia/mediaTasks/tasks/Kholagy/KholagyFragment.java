package baselyous.com.copticsmedia.mediaTasks.tasks.Kholagy;

import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class KholagyFragment extends MediaDetailFragment {
    @Override
    protected int getTask() {
        return 1;
    }

    @Override
    protected MediaContents getMediaContents() {
        return null;
    }

    @Override
    public List<String> getBookContents(String selectedLanguge) {
        return null;
    }
}
