package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.support.v4.app.Fragment;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseListActivity;

/**
 * An activity representing a list of EbsalmodiaPrays. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link EbsalmodiaPrayDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link EbsalmodiaPrayListFragment} and the item details
 * (if present) is a {@link EbsalmodiaPrayDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link EbsalmodiaPrayListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class EbsalmodiaPrayListActivity extends TaskBaseListActivity {
    //implements EbsalmodiaPrayListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
/*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebsalmodiapray_app_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.ebsalmodiapray_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((EbsalmodiaPrayListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.ebsalmodiapray_list))
                    .setActivateOnItemClick(true);
        }


    }
*/

    @Override
    protected int getListFragmentId() {
        return R.id.ebsalmodiapray_list;
    }

    @Override
    protected int getLayoutContainterId() {
        return R.id.ebsalmodiapray_detail_container;
    }

    @Override
    protected int getTaskbarLayoutResources() {
        return R.layout.activity_ebsalmodiapray_app_bar;
    }


    @Override
    protected void updateFragments(Fragment fragment, Fragment controller) {
        EbsalmodiaPrayDetailFragment ebsalmodiaPrayDetailFragment = (EbsalmodiaPrayDetailFragment) fragment;
        EbsalmodiaController ebsalmodiaController = (EbsalmodiaController) controller;
        ebsalmodiaPrayDetailFragment.setController(ebsalmodiaController);
        ebsalmodiaController.setControllerInterface(ebsalmodiaPrayDetailFragment);
    }

    @Override
    protected Fragment getControllerFragment() {
        return new EbsalmodiaController();
    }

    @Override
    protected Class<? extends TaskBaseDetailActivity> getActivity() {
        return EbsalmodiaPrayDetailActivity.class;
    }

    @Override
    protected Fragment getFragment() {
        return new EbsalmodiaPrayDetailFragment();
    }
}
