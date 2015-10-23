package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import android.support.v4.app.Fragment;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailActivity;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class KholagyDetailActivity extends MediaDetailActivity {

    @Override
    public Fragment getClickedFragment() {
        return new KholagyFragment();
    }

}
