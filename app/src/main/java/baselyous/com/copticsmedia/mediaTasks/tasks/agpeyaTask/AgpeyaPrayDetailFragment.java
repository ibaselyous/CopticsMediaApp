package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * A fragment representing a single AgpeyaPray detail screen.
 * This fragment is either contained in a {@link AgpeyaPrayListActivity}
 * in two-pane mode (on tablets) or a {@link AgpeyaPrayDetailActivity}
 * on handsets.
 */
public class AgpeyaPrayDetailFragment extends TaskBaseDetailFragment implements ControllerFragmentBase.ViewControllerInterface {

    private AgpeyaController controller;
    public static final String[] directoryList = new String[]{"paker", "third", "sixth", "nineth", "groob", "nom", "setar", "midnight_1", "midnight_2", "midnight_3"};
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgpeyaPrayDetailFragment() {

    }

    @Override
    public void setControllerVisibility(int visibility) {
        controller.setControllerVisibility(visibility);
    }

    @Override
    protected String getFirstPrayInBook() {
        return directoryList[0];
    }

    @Override
    protected int getTaskIndex() {
        return 0;
    }

    @Override
    protected int getPrayNrOfPages(String praySelected, String language) {
        return ResourceManagement.getPrayNrOfPages (getActivity(),getString(R.string.agpya).toLowerCase(),  praySelected, language);
    }

    @Override
    protected void applyChange(int position) {
        controller.setSubContentPosition(position);
    }

    @Override
    public void updateContents(String languageSelected, int praySelectedIndex) {
        updateViewPager(directoryList[praySelectedIndex], languageSelected);
    }

    @Override
    public void updatePage(int position) {
        setViewPagerPosition(position);
    }

    @Override
    public void changeColor(boolean value) {
        for (PlaceholderFragment fragment : getCurrentPlaceholderFragments()) {

            View rootView = fragment.getRootView();
            rootView.setBackgroundColor(value ? Color.BLACK : Color.WHITE);
            List<TextView> textViewList = fragment.getTextViewList();
            for (TextView textView : textViewList)
                textView.setTextColor(value ? Color.WHITE : Color.BLACK);
        }

        if (getActivity() != null) {
            SharedPreferences.Editor editor = getMyAppSharedPreferences(getActivity()).edit();
            editor.putInt(PlaceholderFragment.TEXT_VIEW_COLOR, value ? Color.WHITE : Color.BLACK);
            editor.putInt(PlaceholderFragment.ROOT_VIEW_BACKGROUND_COLOR, value ? Color.BLACK : Color.WHITE);
            editor.putInt(AgpeyaController.CONTROLLER_BACKGROUND_COLOR, value ? R.drawable.gradient_light : R.drawable.gradient);
            editor.apply();
        }
    }

    @Override
    public void changeSize(boolean isIncrease) {
        float px = getPx(getActivity(), 1);
        for (PlaceholderFragment fragment : getCurrentPlaceholderFragments()) {
            List<TextView> textViewList = fragment.getTextViewList();
            for (TextView textView : textViewList){
                float currentSize = textView.getTextSize();
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, isIncrease ? (currentSize + px) : (currentSize - px));
                if (getActivity() != null) {
                    SharedPreferences.Editor editor = getMyAppSharedPreferences(getActivity()).edit();
                    editor.putFloat(PlaceholderFragment.TEXT_VIEW_SIZE, textView.getTextSize());
                    editor.apply();
                }
            }
        }
    }

    public static float getPx(Context activity, int value) {
        Resources r = activity.getResources();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, r.getDisplayMetrics());
    }

    @Override
    public List<String> getBookContents(String selectedLanguage) {
        return ResourceManagement.readTaskContentsFile(getActivity(),getString(R.string.agpya).toLowerCase(), selectedLanguage, "", "salawat");
    }

    @Override
    public void viewPreviewControlBtn() {
        getPreviewControl().setVisibility(View.VISIBLE);
    }

    @Override
    public List<String> getBookSubContents(String languageSelected, int praySelectedIndex) {
        return ResourceManagement.getPraySelectedPageTitleList(getActivity(), getString(R.string.agpya).toLowerCase(), languageSelected, directoryList[praySelectedIndex]);
    }

    @Override
    public String getSelectedLanguage() {
      return controller.getSelectedLanguage();
    }

    public void setController(AgpeyaController controller) {
        this.controller = controller;
    }
}
