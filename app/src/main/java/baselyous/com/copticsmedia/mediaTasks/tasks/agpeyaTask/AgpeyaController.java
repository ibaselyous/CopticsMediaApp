package baselyous.com.copticsmedia.mediaTasks.tasks.agpeyaTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.ControllerFragmentBase;

/**
 * Created by Ihab Baselyous on 28.10.2015.
 * a layout controller for the Agpeya prays
 */
public class AgpeyaController extends ControllerFragmentBase {
    public static final String CONTROLLER_BACKGROUND_COLOR = "com.baselyous.copticMedia.shared.preferences.controller.view.background.color";
    private Spinner languageSpinner;
    private Spinner bookContentsSpinner;
    private Spinner bookSubContentsSpinner;
    private ImageView sun;
    private ImageView moon;
    private ImageView updateContents;
    private TextView increaseFontSize;
    private TextView decreaseFontSize;
    private RelativeLayout controlsView;

    public AgpeyaController() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_contols, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViews(view);
        loadAgpeyaPrays();
        setOnClickListeners();
    }


    private void setOnClickListeners() {

        applyChangesBtnClickListener();
        bookSubContentsChangeListener();
        changeColorClickListeners();
        changeSizeClickListeners();
    }

    private void changeSizeClickListeners() {
        increaseFontSize.setOnClickListener(new TextWorkerInterface(true, true));
        decreaseFontSize.setOnClickListener(new TextWorkerInterface(false, true));
    }

    private void changeColorClickListeners() {
        sun.setOnClickListener(new TextWorkerInterface(false, false));
        moon.setOnClickListener(new TextWorkerInterface(true, false));
    }

    private void bookSubContentsChangeListener() {
        bookSubContentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getControllerInterface().updatePage(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void applyChangesBtnClickListener() {
        updateContents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = bookContentsSpinner.getSelectedItemPosition();
                getControllerInterface().updateContents(((String) languageSpinner.getSelectedItem()).toLowerCase(), index);
                bookContentsSpinner.setSelection(index);
                loadPraySelectedContents();
            }
        });
    }

    private void loadAgpeyaPrays() {
        if (bookContentsSpinner != null) {
            bookContentsSpinner.post(new Runnable() {
                @Override
                public void run() {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_text, getControllerInterface().getBookContents((String) languageSpinner.getSelectedItem()));
                    bookContentsSpinner.setAdapter(spinnerAdapter);
                    loadPraySelectedContents();
                }
            });
        }
    }

    private void loadPraySelectedContents() {
        if (bookSubContentsSpinner != null) {
            bookSubContentsSpinner.post(new Runnable() {
                @Override
                public void run() {
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_custom_text, getControllerInterface().getBookSubContents(((String) languageSpinner.getSelectedItem()).toLowerCase(), bookContentsSpinner.getSelectedItemPosition()));
                    bookSubContentsSpinner.setAdapter(spinnerAdapter);
                }
            });
        }
    }

    private void getViews(View rootView) {
        controlsView = (RelativeLayout) rootView.findViewById(R.id.fullscreen_content_controls);
        controlsView.setVisibility(View.INVISIBLE);
        languageSpinner = (Spinner) rootView.findViewById(R.id.languageSpinner);
        bookContentsSpinner = (Spinner) rootView.findViewById(R.id.bookContents);
        bookSubContentsSpinner = (Spinner) rootView.findViewById(R.id.bookSubContents);
        sun = (ImageView) rootView.findViewById(R.id.light);
        moon = (ImageView) rootView.findViewById(R.id.dark);
        increaseFontSize = (TextView) rootView.findViewById(R.id.increaseFontSize);
        decreaseFontSize = (TextView) rootView.findViewById(R.id.decreaseFontSize);
        updateContents = (ImageView) rootView.findViewById(R.id.updateContents);
        ImageView hideControl = (ImageView) rootView.findViewById(R.id.hideControls);
        hideControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setControllerVisibility(View.GONE);
                viewPreviewControlBtn();
            }
        });
    }

    private void viewPreviewControlBtn() {
        getControllerInterface().viewPreviewControlBtn();
    }

    public void setControllerVisibility(int controllerVisibility) {
        controlsView.setVisibility(controllerVisibility);
    }

    public String getSelectedLanguage() {
        return (String) languageSpinner.getSelectedItem();
    }

    public void setSubContentPosition(int position) {
        if (bookSubContentsSpinner != null)
            bookSubContentsSpinner.setSelection(position);
    }

    public class TextWorkerInterface implements View.OnClickListener {

        boolean isIncrease;
        private boolean isSizeOrColor;

        public TextWorkerInterface(boolean isPlus, boolean isSizeOrColor) {
            this.isIncrease = isPlus;
            this.isSizeOrColor = isSizeOrColor;
        }

        @Override
        public void onClick(View v) {
            if (isSizeOrColor) {
                changeTextFontSize(isIncrease);
            } else {
                changeTextColorAndBackground(isIncrease);
            }
        }
    }

    private void changeTextColorAndBackground(boolean value) {
        getControllerInterface().changeColor(value);
        controlsView.setBackgroundResource(value ? R.drawable.gradient_light : R.drawable.gradient);
    }

    private void changeTextFontSize(boolean isIncrease) {
        getControllerInterface().changeSize(isIncrease);

    }

}
