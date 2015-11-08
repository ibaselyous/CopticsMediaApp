package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import baselyous.com.copticsmedia.MainActivityFragment;
import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.adapters.SectionsPagerAdapter;

/**
 * Created by Ihab Baselyous on 28.10.2015.
 *
 */
public abstract class TaskBaseDetailFragment extends Fragment {

    public static final String APP_SHARED_PREFERENCES_NAME = "com.baselyous.copticMedia.shared_preferences";

    private ViewPager mViewPager;
    private ImageView previewControl;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static Context currentContext ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_task_previewer, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentContext = getContext();
        findViews(view);
        updateViewPager(getFirstPrayInBook(), getLanguage());
    }

    @Override
    public void onPause() {
        super.onPause();
        currentContext = null;
    }

    private String getLanguage() {
        String language;
        try {
            language = getSelectedLanguage();
        }catch (NullPointerException e) {
            language = MainActivityFragment.languageSelected(getActivity());
        }
        return language;
    }

    public void updateViewPager(String praySelected, String language) {
        if (mSectionsPagerAdapter != null) {
            mSectionsPagerAdapter = null;
            mViewPager.setAdapter(null);
        }

        int nrOfPages = getPrayNrOfPages(praySelected, language);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity(),nrOfPages , getTaskIndex(), praySelected,language);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                applyChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    private void findViews(View rootView) {

        mViewPager = (ViewPager) rootView.findViewById(R.id.fullscreen_content);
        previewControl = (ImageView) rootView.findViewById(R.id.previewControl);
        previewControl.setOnClickListener(new ViewHideControllerOnClick(View.VISIBLE));
    }

    public class ViewHideControllerOnClick implements View.OnClickListener {

        private int visibility;

        public ViewHideControllerOnClick(int visibility) {
            this.visibility = visibility;
        }

        @Override
        public void onClick(View v) {
            setControllerVisibility(visibility);
            if (visibility == View.VISIBLE) {
                previewControl.setVisibility(View.INVISIBLE);
            } else {
                previewControl.setVisibility(View.VISIBLE);
            }
        }
    }

    public ImageView getPreviewControl(){
        return previewControl;
    }

    public void setViewPagerPosition(int position) {
        if(mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }
    }


    public abstract void setControllerVisibility(int visibility) ;

    protected abstract String getFirstPrayInBook();

    protected abstract int getTaskIndex();

    protected abstract int getPrayNrOfPages(String praySelected, String language);

    protected abstract String getSelectedLanguage();

    public static SharedPreferences getMyAppSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    protected abstract void applyChange(int position);

    public List<PlaceholderFragment> getCurrentPlaceholderFragments() {
        return mSectionsPagerAdapter.getFragmentStack();
    }
}
