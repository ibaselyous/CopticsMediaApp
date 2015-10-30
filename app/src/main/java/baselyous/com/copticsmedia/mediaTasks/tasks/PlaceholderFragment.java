package baselyous.com.copticsmedia.mediaTasks.tasks;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * a place holder class for the view pager
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaPlaceHolder;
import baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask.AgpeyaPrayDetailFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.EbsalmodiaPlaceHolder;
import baselyous.com.copticsmedia.mediaTasks.tasks.kholagy.KholagyPlaceHolder;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class PlaceholderFragment extends Fragment {
    public static final String TEXT_VIEW_COLOR = "com.baselyous.copticMedia.shared.preferences.text.view.color";
    public static final String TEXT_VIEW_SIZE = "com.baselyous.copticMedia.shared.preferences.text.view.size";
    public static final String ROOT_VIEW_BACKGROUND_COLOR = "com.baselyous.copticMedia.shared.preferences.root.view.background.color";
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String CONTENTS_DATA_ARRAY = "data_array";
    public static final String CONTENTS_LANGUAGE_ARRAY = "LANGUAGE_ARRAY";
    public static final String CONTENTS_COPTIC_ARRAY = "COPTIC_LANGUAGE_ARRAY";
    public static final String CONTENTS_COMPINATION_ARRAY = "CONTENT_COMPINATION_ARRAY";
    public static final String PRAY_SELECTED = "pray_selected";
    public static final String LANGUAGE_SELECTED = "LANGUAGE_SELECTED";

    private View rootView;
    private List<TextView> textViewList = new ArrayList<>();

    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    public List<TextView> getTextViewList() {
        return textViewList;
    }

    public void addToTextViewList(TextView textView) {
        textViewList.add(textView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(getPlaceHolderLayoutResID(), container, false);
        initViews(rootView);
        return rootView;
    }

    protected abstract int getPlaceHolderLayoutResID();

    private void initViews(View rootView) {
        setRootView(rootView);
        updateChildViews(rootView);
        initializeTextView();
        initRootView();
    }

    private void initRootView() {
        SharedPreferences myAppSharedPreferences = TaskBaseDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(ROOT_VIEW_BACKGROUND_COLOR, Color.WHITE);
        rootView.setBackgroundColor(color);
    }

    private void initializeTextView() {
        SharedPreferences myAppSharedPreferences = TaskBaseDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(TEXT_VIEW_COLOR, Color.BLACK);
        float size = myAppSharedPreferences.getFloat(TEXT_VIEW_SIZE, 24.0f);
        for (TextView textView : textViewList) {
            textView.setTextColor(color);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + AgpeyaPrayDetailFragment.getPx(getActivity(), 0));
        }
    }

    public abstract void updateChildViews(View rootView);

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public static PlaceholderFragment newInstance(int position, int taskIndex, String praySelected, String selectedLanguage) {
        PlaceholderFragment fragment = null;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        args.putString(PRAY_SELECTED, praySelected);
        args.putString(LANGUAGE_SELECTED, selectedLanguage);
        switch (taskIndex) {
            case 0: {
                fragment = new AgpeyaPlaceHolder();
            }
            break;
            case 1: {
                fragment = new KholagyPlaceHolder();
            }
            break;
            case 2: {
                fragment = new EbsalmodiaPlaceHolder();
            }
            break;
        }


        if (fragment != null) {
            fragment.setArguments(args);
        }

        return fragment;
    }
}
