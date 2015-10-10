package baselyous.com.copticsmedia.mediaTasks.tasks.Agpeya;

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
public class AgpeyaFragment extends MediaDetailFragment {

    public static final String[] directoryList = new String[]{"paker", "third", "sixth", "nineth", "groob", "nom", "setar", "midnight_1", "midnight_2", "midnight_3"};


    @Override
    protected int getTask() {
        return 0;
    }

    @Override
    protected MediaContents getMediaContents(String language, String itemClicked) {


        return ResourceManagement.readMediaTaskContents(getActivity(), getTaskName(), language.toLowerCase(), itemClicked);

    }

    @Override
    protected String getItemAssetDirectory(int index) {
        return directoryList[index];
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
        return "agpeya";
    }




}
