package baselyous.com.copticsmedia.adapters;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * an adapter for the views
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.Locale;

import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final FragmentActivity activity;
    private int task;
    private MediaContents mediaContents;

    public SectionsPagerAdapter(FragmentActivity activity, int task, MediaContents mediaContents) {
        super(activity.getSupportFragmentManager());
        this.activity = activity;
        this.task = task;
        this.mediaContents = mediaContents;
        for (String title : mediaContents.getTitleList()) {
            Log.i("title found ", "" + title);
        }
    }


    @Override
    public Fragment getItem(int position) {
        return PlaceholderFragment.newInstance(position, mediaContents);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        if (mediaContents != null && !mediaContents.getMediaList().isEmpty())
            return mediaContents.getMediaList().size();
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        /*switch (position) {
            case 0:
                return activity.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return activity.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return activity.getString(R.string.title_section3).toUpperCase(l);
        }
        */
        if (mediaContents != null && mediaContents.getTitleList() != null && !mediaContents.getTitleList().isEmpty())
            return mediaContents.getTitleList().get(position);

        return null;
    }
}
