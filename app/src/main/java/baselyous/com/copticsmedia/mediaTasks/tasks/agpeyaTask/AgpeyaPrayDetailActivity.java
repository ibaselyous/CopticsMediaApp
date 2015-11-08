package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;

/**
 * An activity representing a single AgpeyaPray detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link AgpeyaPrayListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link AgpeyaPrayDetailFragment}.
 */
public class AgpeyaPrayDetailActivity extends TaskBaseDetailActivity {


    @Override
    protected int getFragmentContainerId() {
        return R.id.agpeyapray_detail_container;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_agpeyapray_detail;
    }

    @Override
    protected void updateFragmentController(TaskBaseDetailFragment fragment, ControllerFragmentBase controller) {
        ((AgpeyaPrayDetailFragment) fragment).setController((AgpeyaController) controller);
        controller.setControllerInterface((AgpeyaPrayDetailFragment) fragment);
    }

    @Override
    protected ControllerFragmentBase getController() {
        return  new AgpeyaController();
    }

    @Override
    protected TaskBaseDetailFragment getFragment() {
        return new AgpeyaPrayDetailFragment();
    }
}
