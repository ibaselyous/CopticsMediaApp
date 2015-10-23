package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import android.support.annotation.NonNull;

import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 *
 */
public class KholagyFragment extends MediaDetailFragment {

    public static final String[] directoryList = new String[]{
            "_1_groob",  "_2_paker", "_3_al_est3dad",  "_4_takdeem_el_hamal", "_5_salat_el_shokr",
            "_6_dawert_el_bekhor",  "_7_el_awashi",  "_8_kodas_baseli",  "_9_kodas_gergori",  "_10_kodas_kerolosi"
    };

    @Override
    protected MediaContents getMediaContents(String language, String itemClicked) {
        return ResourceManagement.readMediaTaskContents(getActivity(), getTaskName(), language.toLowerCase(), itemClicked);
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
        return null;
    }

    @Override
    public String getTaskName() {
        return "kholagy";
    }

}
