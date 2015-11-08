package baselyous.com.copticsmedia.mediaTasks;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.bible.BibleDetailActivity;
import baselyous.com.copticsmedia.mediaTasks.tasks.bible.BibleDetailActivityFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.factory.MediaTaskFactory;
import baselyous.com.copticsmedia.preferences.LanguagePreference;

/**
 * An activity representing a list of Medias. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MediaDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MediaListFragment} and the item details
 * (if present) is a {@link MediaDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link MediaListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MediaListActivity extends ActionBarActivity
        implements MediaListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);


        if (findViewById(R.id.media_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MediaListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.media_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link MediaListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     * @param id item selected
     */
    @Override
    public void onItemSelected(int id) {

        FragmentActivity activity = MediaTaskFactory.getTask(id);
        Log.i("reachege TB list activity sdfdfassdfdadfs ", "getActivity: " +activity.getClass().getName());
        Intent detailIntent = new Intent(this, activity.getClass());
        startActivity(detailIntent);
        /*if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.

            //BibleDetailActivityFragment mediaInterface = new  BibleDetailActivityFragment();
            MediaDetailFragment mediaInterface = MediaTaskFactory.getFragment(id);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.media_detail_container, mediaInterface)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.

            MediaDetailActivity activity = MediaTaskFactory.getActivity(id);
            if (activity != null) {
                Intent detailIntent = new Intent(this, activity.getClass());
                startActivity(detailIntent);
            }
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_root, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(this, LanguagePreference.class);
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
