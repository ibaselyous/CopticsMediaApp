package baselyous.com.copticsmedia.mediaTasks.tasks.factory;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.Agpeya.AgpeyaDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.Agpeya.AgpeyaFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.Ebsalmodia.EbsalmodiaDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.Ebsalmodia.EbsalmodiaFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.Kholagy.KholagyDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.Kholagy.KholagyFragment;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 */
public class MediaTaskFactory {
    public static final int AGPEYA = 0;
    public static final int KHOLAGY = 1;
    public static final int EBSALMODIA = 2;

    public static MediaDetailFragment getFragment(int selectedIndex) {
        switch (selectedIndex) {
            case AGPEYA: {
                return new AgpeyaFragment();
            }
            case KHOLAGY: {
                return new KholagyFragment();
            }
            case EBSALMODIA: {
                return new EbsalmodiaFragment();
            }
            default: {
                return new MediaDetailFragment();
            }
        }
    }

    public static MediaDetailActivity getActivity(int selectedIndex) {
        switch (selectedIndex) {
            case AGPEYA: {
                return new AgpeyaDetailActivity();
            }
            case KHOLAGY: {
                return new KholagyDetailActivity();
            }
            case EBSALMODIA: {
                return new EbsalmodiaDetailActivity();
            }
            default: {
                return new MediaDetailActivity();
            }
        }
    }
}
