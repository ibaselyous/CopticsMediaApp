package baselyous.com.copticsmedia.mediaTasks.tasks.factory;


import android.support.v4.app.FragmentActivity;

import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaPrayListActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.EbsalmodiaPrayListActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.kholagy.KholagyDetailActivity;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 */
public class MediaTaskFactory {
    public static final int AGPEYA = 0;
    public static final int KHOLAGY = 1;
    public static final int EBSALMODIA = 2;

        /*public static MediaDetailFragment getFragment(int selectedIndex) {
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
                    return null;
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
                    return null;
                }
            }
        }*/

    public static FragmentActivity getTask(int selectedIndex) {
        switch (selectedIndex) {
            case AGPEYA: {
                return new AgpeyaPrayListActivity();
            }
            case KHOLAGY: {
                return new KholagyDetailActivity();
            }
            case EBSALMODIA: {
               return new EbsalmodiaPrayListActivity();
            }
            default: {
                return null;
            }
        }
    }
}
