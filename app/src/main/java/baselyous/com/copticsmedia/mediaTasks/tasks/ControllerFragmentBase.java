package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by Ihab Baselyous on 29.10.2015.
 *
 */
public class ControllerFragmentBase extends Fragment {

    private ViewControllerInterface controllerInterface ;


    public ViewControllerInterface getControllerInterface() {
        return controllerInterface;
    }

    public void setControllerInterface(ViewControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }


    public interface ViewControllerInterface {

        List<String> getBookContents(String selectedLanguage);
        void viewPreviewControlBtn();
        List<String> getBookSubContents(String languageSelected, int praySelectedIndex);

        void updateContents(String language, int bookIndex);

        void updatePage(int position);

        void changeColor(boolean value);

        void changeSize(boolean isIncrease);
    }
}
