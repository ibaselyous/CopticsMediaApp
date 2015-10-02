package baselyous.com.copticsmedia;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import baselyous.com.copticsmedia.mediaTasks.MediaListActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getViews(view);
        return view ;
    }

    private void getViews(View view) {
        TextView letsPray= (TextView) view.findViewById(R.id.fragment_main_start_btn);
        letsPray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPraying();
            }
        });
    }


    public void startPraying() {
        Intent intent = new Intent(getActivity(), MediaListActivity.class);

       getActivity().startActivity(intent);
    }
}
