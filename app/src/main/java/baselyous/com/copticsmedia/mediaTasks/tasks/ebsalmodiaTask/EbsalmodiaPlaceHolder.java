package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 *
 */
public class EbsalmodiaPlaceHolder extends PlaceholderFragment {
    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.tasbeha_fragment_content_holder;
    }

    @Override
    public void updateChildViews(View rootView) {
        ImageView language = (ImageView) rootView.findViewById(R.id.ebsalmodia_language);
        ImageView coptic = (ImageView) rootView.findViewById(R.id.ebsalmodia_coptic);
        ImageView combined = (ImageView) rootView.findViewById(R.id.ebsalmodia_combined);
        ToggleButton langToggleButton = (ToggleButton) rootView.findViewById(R.id.languageToggleBtn);
        ToggleButton copticToggleButton = (ToggleButton) rootView.findViewById(R.id.copticToggleBtn);
        ToggleButton combinedToggleButton = (ToggleButton) rootView.findViewById(R.id.languageCombinedToggleBtn);
        langToggleButton.setOnCheckedChangeListener(new MinimizeOnClickListener(language,null));
        copticToggleButton.setOnCheckedChangeListener(new MinimizeOnClickListener(coptic,null));
        combinedToggleButton.setOnCheckedChangeListener(new MinimizeOnClickListener(combined,null));


      /*  setImageResource(language, CONTENTS_LANGUAGE_ARRAY);
        setImageResource(combined, CONTENTS_LANGUAGE_ARRAY);
        setImageResource(coptic, CONTENTS_LANGUAGE_ARRAY);*/



    }

    private void setImageResource(ImageView arabicImage, String contentsLanguageArray) {
        Bundle extras = getArguments();
        String folderInBook = extras.getString(contentsLanguageArray);
        int sectionNr = extras.getInt(ARG_SECTION_NUMBER);




        byte[] arabicArray = extras.getByteArray(contentsLanguageArray);
        Bitmap arabicBitmap = BitmapFactory.decodeByteArray(arabicArray, 0, arabicArray.length);
        arabicImage.setImageBitmap(arabicBitmap);
    }


    public class MinimizeOnClickListener implements CompoundButton.OnCheckedChangeListener {

        private View textView;
        private ImageView minimizeIcon;

        public MinimizeOnClickListener(View textView, ImageView minimizeIcon) {
            this.textView = textView;
            this.minimizeIcon = minimizeIcon;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        /*    int viewVisibilityToSave = View.VISIBLE;
            int viewVisibility = getPreferences().getInt(String.valueOf(textView.getId()), View.VISIBLE);*/

            if (isChecked) {
                textView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.GONE);
            }

            /*
            if (viewVisibility == View.VISIBLE) {
                minimizeIcon.setImageResource(R.drawable.maximize);
                viewVisibilityToSave = View.GONE;

            } else if (viewVisibility == View.GONE) {
                minimizeIcon.setImageResource(R.drawable.minimize_mdpi);
                viewVisibilityToSave = View.VISIBLE;
            }

            getPreferences().edit().putInt(String.valueOf(textView.getId()), viewVisibilityToSave).commit();*/
        }
    }

    public SharedPreferences getPreferences() {
        return getActivity().getPreferences(Context.MODE_PRIVATE);
    }
}
