package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaController;

/**
 * An activity representing a single EbsalmodiaPray detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link EbsalmodiaPrayListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link EbsalmodiaPrayDetailFragment}.
 */
public class EbsalmodiaPrayDetailActivity extends TaskBaseDetailActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_agpeyapray_detail;
    }

    @Override
    protected void updateFragmentController(TaskBaseDetailFragment fragment, ControllerFragmentBase controller) {
        ((EbsalmodiaPrayDetailFragment) fragment).setController((EbsalmodiaController) controller);
        controller.setControllerInterface((EbsalmodiaPrayDetailFragment)fragment);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.agpeyapray_detail_container;
    }

    @Override
    protected ControllerFragmentBase getController() {
        return new EbsalmodiaController();
    }

    @Override
    protected TaskBaseDetailFragment getFragment() {
        return new EbsalmodiaPrayDetailFragment();
    }


}
