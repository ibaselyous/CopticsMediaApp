package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 *
 */
public class AgpeyaPlaceHolder extends PlaceholderFragment {


    @Override
    public void initViews(View rootView) {
        setRootView(rootView);
        updateChildViews(rootView);
        initializeTextView();
        initRootView(rootView);
    }

    private void initRootView(View rootView) {
        SharedPreferences myAppSharedPreferences = TaskBaseDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(ROOT_VIEW_BACKGROUND_COLOR, Color.WHITE);
        rootView.setBackgroundColor(color);
    }

    private void initializeTextView() {
        SharedPreferences myAppSharedPreferences = TaskBaseDetailFragment.getMyAppSharedPreferences(getActivity());
        int color = myAppSharedPreferences.getInt(TEXT_VIEW_COLOR, Color.BLACK);
        float size = myAppSharedPreferences.getFloat(TEXT_VIEW_SIZE, 24.0f);
        for (TextView textView : getTextViewList()) {
            textView.setTextColor(color);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size + AgpeyaPrayDetailFragment.getPx(getActivity(), 0));
        }
    }

    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.agpeya_fragment_contents_holder;
    }


    @Override
    public void updateChildViews(View rootView) {
        final TextView textView = (TextView) rootView.findViewById(R.id.section_label);

        new AsyncTask<Void, String ,String>(){

            @Override
            protected String doInBackground(Void... params) {
                return getTextContents();
            }
           @Override
            protected void onPostExecute(String s) {
                textView.setText(s);
            }
        }.execute(null, null, null);

        textView.setMovementMethod(new ScrollingMovementMethod());
        addToTextViewList(textView);

    }

    private String getTextContents() {
        int pageNr = getArguments().getInt(ARG_SECTION_NUMBER);
        String praySelected = getArguments().getString(PRAY_SELECTED);
        String language = getArguments().getString(LANGUAGE_SELECTED);

        return ResourceManagement.getTextContent(getString(R.string.agpya), pageNr, praySelected, language, getActivity());
    }

}
