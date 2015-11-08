package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseListAdapterFragment;


/**
 * A list fragment representing a list of AgpeyaPrayes. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link AgpeyaPrayDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class AgpeyaPrayListFragment extends TaskBaseListAdapterFragment {

    @Override
    protected String getTaskName() {
        return getResources().getString(R.string.agpya).toLowerCase();
    }


    @Override
    public List<Integer> getIconList() {
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

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgpeyaPrayListFragment() {

    }


}
