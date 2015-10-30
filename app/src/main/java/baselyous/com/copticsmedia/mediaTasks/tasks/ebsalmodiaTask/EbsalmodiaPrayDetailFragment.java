package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * A fragment representing a single EbsalmodiaPray detail screen.
 * This fragment is either contained in a {@link EbsalmodiaPrayListActivity}
 * in two-pane mode (on tablets) or a {@link EbsalmodiaPrayDetailActivity}
 * on handsets.
 */
public class EbsalmodiaPrayDetailFragment extends TaskBaseDetailFragment implements ControllerFragmentBase.ViewControllerInterface {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    //public static final String ARG_ITEM_ID = "item_id";
    private EbsalmodiaController controller ;
    public static final String[] directoryList = new String[]{"tenseno"};
    /**
     * The dummy content this fragment is presenting.
     */


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EbsalmodiaPrayDetailFragment() {
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
        return 2;
    }

    @Override
    protected int getPrayNrOfPages(String praySelected, String language) {
        return ResourceManagement.getPrayNrOfPages(getActivity(), getString(R.string.ebsalmodia).toLowerCase(), praySelected, language);
    }

    @Override
    protected String getSelectedLanguage() {
        return controller.getSelectedLanguage();
    }

    @Override
    protected void applyChange(int position) {

    }

    @Override
    public List<String> getBookContents(String selectedLanguage) {
        return null;
    }

    @Override
    public void viewPreviewControlBtn() {

    }

    @Override
    public List<String> getBookSubContents(String languageSelected, int praySelectedIndex) {
        return null;
    }

    @Override
    public void updateContents(String language, int bookIndex) {

    }

    @Override
    public void updatePage(int position) {

    }

    @Override
    public void changeColor(boolean value) {

    }

    @Override
    public void changeSize(boolean isIncrease) {

    }

    public void setController(EbsalmodiaController controller) {
        this.controller = controller;
    }

    public EbsalmodiaController getController() {
        return controller;
    }
}
