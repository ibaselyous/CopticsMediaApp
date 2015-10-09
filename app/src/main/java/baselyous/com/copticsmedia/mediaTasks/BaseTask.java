package baselyous.com.copticsmedia.mediaTasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ihab Baselyous on 09.10.2015.
 * base for all tasks
 */
public abstract class BaseTask extends Fragment {

    public static final String APP_SHARED_PREFERENCES_NAME = "com.baselyous.copticMedia.shared_preferences";

    private static final int TASK_CONTENT_SELECTOR = 0;


    public static SharedPreferences getMyAppSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor(Context context) {
        return getMyAppSharedPreferences(context).edit();
    }


    public void selectTaskContent() {
        Intent intent = new Intent(getActivity(), TaskContentSelector.class);
        intent.putStringArrayListExtra("content", (ArrayList<String>) getBookContents("arabic"));
        startActivityForResult(intent, TASK_CONTENT_SELECTOR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TASK_CONTENT_SELECTOR: {
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        int itemIndex = data.getExtras().getInt("itemClicked");
                        loadBookItemSelected(itemIndex);
                    }
                    break;
                }
            }
            break;
        }
    }

    protected abstract void loadBookItemSelected(int itemIndex);


    public abstract List<String> getBookContents(String selectedLanguage);
}
