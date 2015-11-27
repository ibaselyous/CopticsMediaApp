package baselyous.com.copticsmedia.mediaTasks.tasks;

/**
 * Created by Ihab Baselyous on 03.10.2015.
 * a place holder class for the view pager
 */

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.MediaDetailFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    public static final String TEXT_VIEW_COLOR = "com.baselyous.copticMedia.shared.preferences.text.view.color";
    public static final String TEXT_VIEW_SIZE = "com.baselyous.copticMedia.shared.preferences.text.view.size";
    public static final String ROOT_VIEW_BACKGROUND_COLOR = "com.baselyous.copticMedia.shared.preferences.root.view.background.color";
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String CONTENTS_DATA_ARRAY = "data_array";
    private TextView textView;
    private View rootView;

    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, MediaContents data) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(CONTENTS_DATA_ARRAY, data.getMediaList().get(sectionNumber).getContent());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contents_preview, container, false);
        setRootView(rootView);
        textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getArguments().getString(CONTENTS_DATA_ARRAY));
        textView.setMovementMethod(new ScrollingMovementMethod());
        initializeTextView();
        initRootView();
        return rootView;
    }

    private void initRootView() {
        SharedPreferences myAppSharedPreferences = MediaDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(ROOT_VIEW_BACKGROUND_COLOR, Color.WHITE);
        rootView.setBackgroundColor(color);

    }

    private void initializeTextView() {
        SharedPreferences myAppSharedPreferences = MediaDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(TEXT_VIEW_COLOR, Color.BLACK);
        float size = myAppSharedPreferences.getFloat(TEXT_VIEW_SIZE, 24.0f);
        textView.setTextColor(color);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + MediaDetailFragment.getPx(getActivity(), 0));
    }


    public TextView getTextView() {
        return textView;
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }
}
