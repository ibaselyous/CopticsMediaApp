package baselyous.com.copticsmedia.preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import baselyous.com.copticsmedia.R;

/**
 * Created by Ihab Baselyous on 09.10.2015.
 * fragment to define base language
 */
@SuppressWarnings("deprecation")
public class LanguagePreference extends PreferenceActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.language_preference);

    }
}
