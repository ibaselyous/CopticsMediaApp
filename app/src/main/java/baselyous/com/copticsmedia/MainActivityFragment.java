package baselyous.com.copticsmedia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import baselyous.com.copticsmedia.mediaTasks.MediaListActivity;
import baselyous.com.copticsmedia.preferences.LanguagePreference;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    public static String languageSelected(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("language_preference_list", "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getViews(view);
        return view;
    }

    private void getViews(View view) {
        TextView letsPray = (TextView) view.findViewById(R.id.fragment_main_start_btn);
        letsPray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPraying();
            }
        });
    }

    public void startPraying() {


        String value = languageSelected(getActivity());

        if (value.isEmpty()) {
            Intent intent = new Intent(getActivity(), LanguagePreference.class);
            getActivity().startActivity(intent);

        } else {
            Intent intent = new Intent(getActivity(), MediaListActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
