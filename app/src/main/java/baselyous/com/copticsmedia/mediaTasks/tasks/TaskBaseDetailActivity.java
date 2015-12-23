package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaPrayListActivity;

/**
 * Created by Ihab Baselyous on 29.10.2015.
 */
public abstract class TaskBaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());


        // Show the Up button in the action bar.
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(TaskBaseListActivity.ARG_ITEM_ID,
                    getIntent().getExtras().getInt(TaskBaseListActivity.ARG_ITEM_ID));

            TaskBaseDetailFragment fragment = getFragment();
            ControllerFragmentBase controller = getController();
            updateFragmentController(fragment, controller);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(getFragmentContainerId(), fragment)
                    .add(R.id.taskContentController, controller)
                    .commit();
        }
    }

    protected abstract int getFragmentContainerId();

    protected abstract int getLayoutResourceId();

    protected abstract void updateFragmentController(TaskBaseDetailFragment fragment, ControllerFragmentBase controller);

    protected abstract ControllerFragmentBase getController();

    protected abstract TaskBaseDetailFragment getFragment();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, AgpeyaPrayListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}