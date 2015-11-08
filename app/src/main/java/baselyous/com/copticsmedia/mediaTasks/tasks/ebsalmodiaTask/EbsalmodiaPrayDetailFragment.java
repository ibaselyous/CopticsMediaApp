package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.content.Context;
import android.view.View;

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

    private EbsalmodiaController controller;
    public static final String[] directoryList = new String[]{"tenseno"};

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
        return ResourceManagement.getEbsalmodiaPrayNrOfPages(getActivity(), praySelected, language.toLowerCase());
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
        return ResourceManagement.readTaskContentsFile(getActivity(),getString(R.string.ebsalmodia).toLowerCase(), selectedLanguage, "", "salawat");
    }

    @Override
    public void viewPreviewControlBtn() {
        getPreviewControl().setVisibility(View.VISIBLE);
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
