package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import android.support.v4.app.Fragment;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseListActivity;

/**
 * An activity representing a list of AgpeyaPrayes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link AgpeyaPrayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link AgpeyaPrayListFragment} and the item details
 * (if present) is a {@link AgpeyaPrayDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link AgpeyaPrayListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class AgpeyaPrayListActivity extends TaskBaseListActivity {


    @Override
    protected Fragment getFragment() {
        return new AgpeyaPrayDetailFragment();
    }

    @Override
    protected int getListFragmentId() {
        return R.id.agpeyapray_list;
    }

    @Override
    protected int getLayoutContainterId() {
        return R.id.agpeyapray_detail_container;
    }

    @Override
    protected int getTaskbarLayoutResources() {
        return R.layout.activity_agpeyapray_app_bar;
    }

    @Override
    protected void updateFragments(Fragment fragment, Fragment controller) {
        AgpeyaPrayDetailFragment agpeyaPrayDetailFragment = (AgpeyaPrayDetailFragment) fragment;
        AgpeyaController agpeyaController = (AgpeyaController) controller;
        agpeyaPrayDetailFragment.setController(agpeyaController);
        agpeyaController.setControllerInterface(agpeyaPrayDetailFragment);
    }

    @Override
    protected Fragment getControllerFragment() {
        return new AgpeyaController();
    }


    protected Class<? extends TaskBaseDetailActivity> getActivity() {
        return  AgpeyaPrayDetailActivity.class;
    }
}
