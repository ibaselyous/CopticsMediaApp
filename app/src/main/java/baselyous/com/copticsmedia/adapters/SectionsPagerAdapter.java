package baselyous.com.copticsmedia.adapters;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * an adapter for the views
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Stack;

import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragmentStack corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


    private MediaContents mediaContents;
    private List<Fragment> fragmentStack = new Stack<>();


    public SectionsPagerAdapter(FragmentActivity activity, MediaContents mediaContents) {
        super(activity.getSupportFragmentManager());
        this.mediaContents = mediaContents;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment currentFragment = PlaceholderFragment.newInstance(position, mediaContents);
        fragmentStack.add(currentFragment);
        return currentFragment;
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
        if (mediaContents != null && mediaContents.getTitleList() != null && !mediaContents.getTitleList().isEmpty())
            return mediaContents.getTitleList().get(position);

        return null;
    }


    public List<Fragment> getFragmentStack() {
        return fragmentStack;
    }
}
