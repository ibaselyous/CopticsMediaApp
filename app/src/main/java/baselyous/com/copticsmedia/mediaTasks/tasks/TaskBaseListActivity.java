package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaPrayDetailActivity;


/**
 * Created by Ihab Baselyous on 28.10.2015.
 *
 */
public abstract class TaskBaseListActivity extends AppCompatActivity
        implements TaskBaseListFragment.Callbacks {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static final String ARG_ITEM_ID = "item_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getTaskbarLayoutResources());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(getLayoutContainterId()) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((TaskBaseListFragment) getSupportFragmentManager()
                    .findFragmentById(getListFragmentId()))
                    .setActivateOnItemClick(true);
        }


    }

    protected abstract int getListFragmentId();


    protected abstract int getLayoutContainterId();

    protected abstract int getTaskbarLayoutResources();

    /**
     * Callback method from {@link TaskBaseListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     * @param prayClicked
     */
    @Override
    public void onItemSelected(int prayClicked) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Log.i("bae activity sdfdfassdfdadfs ", "getActivity: " + prayClicked);
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_ITEM_ID, prayClicked);
            Fragment fragment = getFragment() ;
            Fragment controller = getControllerFragment();
            updateFragments(fragment, controller);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(getLayoutContainterId(), fragment)
                    .replace(R.id.taskContentController, controller)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.

            Intent detailIntent = new Intent(this, getActivity());
            detailIntent.putExtra(ARG_ITEM_ID, prayClicked);
            startActivity(detailIntent);
        }
    }

    protected abstract void updateFragments(Fragment fragment, Fragment controller);

    protected abstract Fragment getControllerFragment();

    protected abstract Class<? extends TaskBaseDetailActivity> getActivity();

    protected abstract Fragment getFragment();


}
