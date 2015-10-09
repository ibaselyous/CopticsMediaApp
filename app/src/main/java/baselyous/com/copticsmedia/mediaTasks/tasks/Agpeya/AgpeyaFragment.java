package baselyous.com.copticsmedia.mediaTasks.tasks.Agpeya;

import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class AgpeyaFragment extends MediaDetailFragment {

    @Override
    protected int getTask() {
        return 0;
    }

    @Override
    protected MediaContents getMediaContents() {
        return ResourceManagement.readAgpeyaContents(getActivity(), "arabic");

    }

    @Override
    public List<String> getBookContents(String selectedLanguge) {
        return ResourceManagement.readAgeyaContentsByLanguage(getActivity(), selectedLanguge);
    }


}
