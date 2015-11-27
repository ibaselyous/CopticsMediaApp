package baselyous.com.copticsmedia.mediaTasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import baselyous.com.copticsmedia.MainActivityFragment;
import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.adapters.SectionsPagerAdapter;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaContents;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * A fragment representing a single Media detail screen.
 * This fragment is either contained in a {@link MediaListActivity}
 * in two-pane mode (on tablets) or a {@link MediaDetailActivity}
 * on handsets.
 */
public abstract class MediaDetailFragment extends BaseTask {


    public static final String CONTROLLER_BACKGROUND_COLOR = "com.baselyous.copticMedia.shared.preferences.controller.view.background.color";
    private ViewPager mViewPager;
    private Spinner languageSpinner;
    private Spinner bookContentsSpinner;
    private Spinner bookSubContentsSpinner;
    private View controlsView;
    private ImageView previewControl;
    private ImageView hideControl;
    private TextView increaseFontsize;
    private TextView decreaseFontsize;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ImageView sun;
    private ImageView moon;
    private ImageView updateContents;


    public MediaDetailFragment() {

    }

    public static float getPx(Context activity, int value) {
        Resources r = activity.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, r.getDisplayMetrics());
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */


    protected abstract MediaContents getMediaContents(String language, String itemClicked);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_task_previewer, container, false);
        getViews(rootView);
        initController();
        initSpinnersWithDefaults();
        setOnSpinnerChangeListener();
        setOnUpdateBtnClickListener();
        setViewsOnClickListener();
        return rootView;
    }

    private void initSpinnersWithDefaults() {
        String defaultLanguage = MainActivityFragment.languageSelected(getActivity());
        updateContents(defaultLanguage, "paker");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        selectTaskContent();
    }

    @Override
    protected void loadBookItemSelected(String language, String itemClicked, int index) {
        updateContents(language, getItemAssetDirectory(index));
        //bookContentsSpinner.setSelection(index);
    }

    protected abstract String getItemAssetDirectory(int index);

    private void updateContents(String defaultLanguage, String book) {
        MediaContents contents = getMediaContents(defaultLanguage.toLowerCase(), book);
        updateViewPagerContents(contents);
        updateSpinners(defaultLanguage, contents);
    }

    private void setOnSpinnerChangeListener() {
        bookSubContentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setOnUpdateBtnClickListener() {
        updateContents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = bookContentsSpinner.getSelectedItemPosition();
                updateContents((String) languageSpinner.getSelectedItem(), getItemAssetDirectory(index));
                bookContentsSpinner.setSelection(index);
            }
        });
    }

    private void updateSpinners(String defaultLanguage, MediaContents contents) {
        updateLanguageSpinner(defaultLanguage);
        updateBookSpinner(getBookContents(defaultLanguage));
        updateBookSubContents(contents);
    }

    private void updateBookSubContents(MediaContents contents) {
        if (bookSubContentsSpinner != null) {
            List<String> titleList = contents.getTitleList();
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_text, titleList);
            bookSubContentsSpinner.setAdapter(spinnerAdapter);
        }
    }

    private void updateLanguageSpinner(String defaultLanguage) {
        if (languageSpinner != null) {
            String[] languages = getResources().getStringArray(R.array.languages);
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_text, languages);
            languageSpinner.setAdapter(spinnerAdapter);
           /* String defaultValue = MainActivityFragment.languageSelected(getActivity());*/
            for (int i = 0; i < languages.length; i++) {
                if (languageSpinner.getItemAtPosition(i).equals(defaultLanguage)) {
                    languageSpinner.setSelection(i);
                }
            }
        }
    }

    private void updateBookSpinner(List<String> bookContents) {
        if (bookContentsSpinner != null) {
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_text, bookContents);
            bookContentsSpinner.setAdapter(spinnerAdapter);

        }
    }

    private void updateViewPagerContents(MediaContents contents) {
        if (mSectionsPagerAdapter != null) {
            mSectionsPagerAdapter = null;
            mViewPager.setAdapter(null);
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), contents);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeBookSubContentsSpinner(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    private void changeBookSubContentsSpinner(int position) {
        if (bookSubContentsSpinner != null)
            bookSubContentsSpinner.setSelection(position);
    }

    private void getViews(View rootView) {
        controlsView = rootView.findViewById(R.id.fullscreen_content_controls);
        controlsView.setVisibility(View.INVISIBLE);
        mViewPager = (ViewPager) rootView.findViewById(R.id.fullscreen_content);
        languageSpinner = (Spinner) rootView.findViewById(R.id.languageSpinner);
        bookContentsSpinner = (Spinner) rootView.findViewById(R.id.bookContents);
        bookSubContentsSpinner = (Spinner) rootView.findViewById(R.id.bookSubContents);
        previewControl = (ImageView) rootView.findViewById(R.id.previewControl);
        hideControl = (ImageView) rootView.findViewById(R.id.hideControls);
        sun = (ImageView) rootView.findViewById(R.id.light);
        moon = (ImageView) rootView.findViewById(R.id.dark);
        increaseFontsize = (TextView) rootView.findViewById(R.id.increaseFontSize);
        decreaseFontsize = (TextView) rootView.findViewById(R.id.decreaseFontSize);
        updateContents = (ImageView) rootView.findViewById(R.id.updateContents);


    }

    protected void initController() {
        SharedPreferences myAppSharedPreferences = MediaDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(CONTROLLER_BACKGROUND_COLOR, R.drawable.gradient);
        controlsView.setBackgroundResource(color);
    }

    private void setViewsOnClickListener() {
        previewControl.setOnClickListener(new ViewHideControllerOnClick(View.VISIBLE));
        hideControl.setOnClickListener(new ViewHideControllerOnClick(View.GONE));
        increaseFontsize.setOnClickListener(new TextWorkerInterface(true, true));
        decreaseFontsize.setOnClickListener(new TextWorkerInterface(false, true));
        sun.setOnClickListener(new TextWorkerInterface(false, false));
        moon.setOnClickListener(new TextWorkerInterface(true, false));
    }

    private void changeTextColorAndBackground(boolean value) {

        for (Fragment fragment : mSectionsPagerAdapter.getFragmentStack()) {
            TextView textView = ((PlaceholderFragment) fragment).getTextView();
            View rootView = ((PlaceholderFragment) fragment).getRootView();
            textView.setTextColor(value ? Color.WHITE : Color.BLACK);
            rootView.setBackgroundColor(value ? Color.BLACK : Color.WHITE);
            controlsView.setBackgroundResource(value ? R.drawable.gradient_light : R.drawable.gradient);
        }

        if (getActivity() != null) {
            SharedPreferences.Editor editor = getEditor(getActivity());
            editor.putInt(PlaceholderFragment.TEXT_VIEW_COLOR, value ? Color.WHITE : Color.BLACK);
            editor.putInt(PlaceholderFragment.ROOT_VIEW_BACKGROUND_COLOR, value ? Color.BLACK : Color.WHITE);
            editor.putInt(CONTROLLER_BACKGROUND_COLOR, value ? R.drawable.gradient_light : R.drawable.gradient);
            editor.commit();
        }

    }

    private void changeTextFontSize(boolean isIncrease) {
        float px = getPx(getActivity(), 1);
        Fragment fragment = mSectionsPagerAdapter.getFragmentStack().get(mViewPager.getCurrentItem());
        TextView textView = ((PlaceholderFragment) fragment).getTextView();
        if (textView != null) {
            float currentSize = textView.getTextSize();
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, isIncrease ? (currentSize + px) : (currentSize - px));
            if (getActivity() != null) {
                SharedPreferences.Editor editor = getEditor(getActivity());
                editor.putFloat(PlaceholderFragment.TEXT_VIEW_SIZE, textView.getTextSize());
                editor.commit();
            }
        }
    }

    public class ViewHideControllerOnClick implements View.OnClickListener {

        private int visibility;

        public ViewHideControllerOnClick(int visibility) {
            this.visibility = visibility;
        }

        @Override
        public void onClick(View v) {
            controlsView.setVisibility(visibility);
            if (visibility == View.VISIBLE) {
                previewControl.setVisibility(View.INVISIBLE);
            } else {
                previewControl.setVisibility(View.VISIBLE);
            }
        }
    }

    public class TextWorkerInterface implements View.OnClickListener {

        boolean isIncrease;
        private boolean isSizeOrColor;

        public TextWorkerInterface(boolean isPlus, boolean isSizeOrColor) {
            this.isIncrease = isPlus;
            this.isSizeOrColor = isSizeOrColor;
        }

        @Override
        public void onClick(View v) {
            if (isSizeOrColor) {
                changeTextFontSize(isIncrease);
            } else {
                changeTextColorAndBackground(isIncrease);
            }
        }
    }


}
