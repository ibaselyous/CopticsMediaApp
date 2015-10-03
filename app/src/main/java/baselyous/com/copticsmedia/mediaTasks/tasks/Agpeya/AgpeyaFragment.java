package baselyous.com.copticsmedia.mediaTasks.tasks.Agpeya;

import android.util.Log;

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
        MediaContents mediaContents = ResourceManagement.readAgpeyaContents(getActivity(), "arabic");


        for (String title : mediaContents.getTitleList()) {
            Log.i("title found ", "" + title);
        }

        return mediaContents;

    }


}
