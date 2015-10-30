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



    private final int prayNrOfPages;
    private final int taskIndex;
    private final String praySelected;
    private final String selectedLanguage;
    private MediaContents mediaContents;
    private List<PlaceholderFragment> fragmentStack = new Stack<>();


    public SectionsPagerAdapter(FragmentActivity activity, MediaContents mediaContents, int task, String folderInBook) {
        super(activity.getSupportFragmentManager());
        this.mediaContents = mediaContents;
        this.prayNrOfPages = 0;
        this.taskIndex = task;
        this.praySelected = folderInBook;
        selectedLanguage = null;
    }

    public SectionsPagerAdapter(FragmentActivity activity, int prayNrOfPages, int taskIndex, String praySelected, String selectedLanguage) {
        //super(activity, prayNrOfPages, taskIndex, praySelected);
        super(activity.getSupportFragmentManager());

        this.prayNrOfPages = prayNrOfPages;
        this.taskIndex = taskIndex;
        this.praySelected = praySelected;
        this.selectedLanguage = selectedLanguage;
    }


    @Override
    public Fragment getItem(int position) {
        //Fragment currentFragment = PlaceholderFragment.newInstance(position, mediaContents, taskIndex, praySelected);
        PlaceholderFragment currentFragment =  PlaceholderFragment.newInstance(position, taskIndex, praySelected, selectedLanguage);
        fragmentStack.add(currentFragment);
        return currentFragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        /*switch(taskIndex){
            case 0 : {
                if (mediaContents != null && !mediaContents.getLanguageContentMediaList().isEmpty()){
                    return mediaContents.getLanguageContentMediaList().size();
                }
            }break;
            case 1:
            case 2: {
                return mediaContents.getNumberOfPages();
            }
        }*/

        return prayNrOfPages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mediaContents != null && mediaContents.getTitleList() != null && !mediaContents.getTitleList().isEmpty())
            return mediaContents.getTitleList().get(position);

        return "title";
    }


    public List<PlaceholderFragment> getFragmentStack() {
        return fragmentStack;
    }
}
