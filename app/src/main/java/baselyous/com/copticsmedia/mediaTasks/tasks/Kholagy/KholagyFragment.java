package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class KholagyFragment extends MediaDetailFragment {

    public static final String[] directoryList = new String[]{
            "01_ashia",  "_2_paker", "_3_al_est3dad",  "_4_takdeem_el_hamal", "_5_salat_el_shokr",
            "_6_dawert_el_bekhor",  "_7_el_awashi",  "_8_kodas_baseli",  "_9_kodas_gergori",  "_10_kodas_kerolosi"
    };

    @Override
    protected MediaContents getMediaContents(String language, String itemClicked) {
        return ResourceManagement.readMediaTaskContents(getActivity(), getTaskName(), language.toLowerCase(), itemClicked);//itemClicked item from the directory list
    }

    @NonNull
    @Override
    public String getRootElement() {
        return directoryList[0];
    }

    @Override
    protected String getItemAssetDirectory(int index) {
        return directoryList[index];
    }

    @Override
    protected int getTaskIndex() {
        return 1;
    }

    @Override
    public List<String> getBookContents(String selectedLanguage) {
        return ResourceManagement.readTaskContentsFile(getActivity(), getTaskName(), selectedLanguage, "", "salawat");
    }

    @Override
    public List<Integer> getBookContentIconList() {
        List<Integer> iconList = new ArrayList<>();
        iconList.add(R.drawable.kiama);
        iconList.add(R.drawable.heilige_geist);
        iconList.add(R.drawable.cross_way);
        iconList.add(R.drawable.crossing);
        iconList.add(R.drawable.jesus_down_of_the_cross_3);
        iconList.add(R.drawable.jesus_in_the_grave);
        iconList.add(R.drawable.jesus_walking_on_the_water);
        iconList.add(R.drawable.virgin);
        iconList.add(R.drawable.gulti_woman);
        iconList.add(R.drawable.jesus_coming_);
        return iconList;
    }

    @Override
    public String getTaskName() {
        return "kholagy";
    }

}
