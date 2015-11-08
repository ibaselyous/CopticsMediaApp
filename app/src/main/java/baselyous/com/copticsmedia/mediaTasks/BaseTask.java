package baselyous.com.copticsmedia.mediaTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.MainActivityFragment;
import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.MediaInterface;

/**
 * Created by Ihab Baselyous on 09.10.2015.
 * base for all tasks
 */
public abstract class BaseTask extends Fragment implements MediaInterface {

    public static final String APP_SHARED_PREFERENCES_NAME = "com.baselyous.copticMedia.shared_preferences";

    private static final int TASK_CONTENT_SELECTOR = 0;


    public static SharedPreferences getMyAppSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getMyAppSharedPreferences(context).edit();
    }


    public void selectTaskContent() {
        String baseLanguage = MainActivityFragment.languageSelected(getActivity());

        Intent intent = new Intent(getActivity(), TaskContentSelector.class);
        intent.putStringArrayListExtra("content", (ArrayList<String>) getBookContents(baseLanguage.toLowerCase()));
        intent.putIntegerArrayListExtra("icons", (ArrayList<Integer>) getBookContentIconList());
        intent.putExtra("task", getTaskName());
        startActivityForResult(intent, TASK_CONTENT_SELECTOR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TASK_CONTENT_SELECTOR: {
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        String itemClickedString = data.getExtras().getString("itemClickedString");
                        int itemIndex = data.getExtras().getInt("itemClicked");
                        String defaultLanguage = MainActivityFragment.languageSelected(getActivity());
                        loadBookItemSelected(defaultLanguage.toLowerCase(), itemClickedString, itemIndex);
                    }
                    break;
                }
            }
            break;
        }
    }

    protected abstract void loadBookItemSelected(String language, String itemIndex, int index);

    public abstract List<String> getBookContents(String selectedLanguage);

    public abstract List<Integer> getBookContentIconList();


}
