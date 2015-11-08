package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask.util.ImageFetcher;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 *
 */
public class EbsalmodiaPlaceHolder extends PlaceholderFragment {
    public static final int ONLY_LANGUAGE = 0;
    public static final int ONLY_COPTIC = 1;
    public static final int LANGUAGE_COPTIC = 2;


    public static final String ONLY_LANGUAGE_PREF = "com.baselyous.coptic.media.ebsalmodia.only.language.pref";
    public static final String ONLY_COPTIC_PREF = "com.baselyous.coptic.media.ebsalmodia.only.coptic.pref";
    public static final String ONLY_LANGUAGE_COPTIC_PREF = "com.baselyous.coptic.media.ebsalmodia.coptic.language.pref";


    @Override
    public void initViews(View rootView) {
        super.initViews(rootView);

    }

    private void initMinimizeMaximize(ImageView imageView, ImageView minimizeIcon, TextView titleView, String prefConst) {
        boolean viewVisibility = getPreferences().getBoolean(prefConst, false);
        if (viewVisibility) {
            updateViews(imageView, minimizeIcon, titleView, View.VISIBLE, R.drawable.minimize_mdpi, View.INVISIBLE);

        } else {
            updateViews(imageView, minimizeIcon, titleView, View.GONE, R.drawable.maximize, View.VISIBLE);
        }

    }

    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.tasbeha_fragment_content_holder;
    }

    @Override
    public void updateChildViews(View rootView) {
        int pageNr = getArguments().getInt(ARG_SECTION_NUMBER);
        String praySelected = getArguments().getString(PRAY_SELECTED);
        String language = getArguments().getString(LANGUAGE_SELECTED);
        language = language != null ? language.toLowerCase() : "arabic";


        ImageView copticImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_coptic_image);
        ImageView languageCopticImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_coptic_image);
        ImageView languageImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_image);

        ImageView arabicMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_minimize);
        ImageView copticMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_coptic_minimize);
        ImageView arabicCopticMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_coptic_minimize);

        TextView languageCopticTxt = (TextView) rootView.findViewById(R.id.ebsalmodia_language_coptic_current_language);
        TextView copticTxt = (TextView) rootView.findViewById(R.id.ebsalmodia_coptic_current_language);
        TextView langTxt = (TextView) rootView.findViewById(R.id.ebsalmodia_current_language);

        languageCopticTxt.setText(language + " Coptic");
        copticTxt.setText("Coptic");
        langTxt.setText(language);


        setImageResource(languageImage, pageNr, praySelected, language, ONLY_LANGUAGE);
        setImageResource(languageCopticImage, pageNr, praySelected, language, LANGUAGE_COPTIC);
        setImageResource(copticImage, pageNr, praySelected, language, ONLY_COPTIC);

        arabicCopticMinimize.setOnClickListener(new MinimizeOnClickListener(languageCopticImage, arabicCopticMinimize, languageCopticTxt,ONLY_LANGUAGE_COPTIC_PREF ));
        copticMinimize.setOnClickListener(new MinimizeOnClickListener(copticImage, copticMinimize, copticTxt, ONLY_COPTIC_PREF));
        arabicMinimize.setOnClickListener(new MinimizeOnClickListener(languageImage, arabicMinimize, langTxt, ONLY_LANGUAGE_PREF));
        initMinimizeMaximize(languageCopticImage, arabicCopticMinimize, languageCopticTxt, ONLY_LANGUAGE_COPTIC_PREF );
        initMinimizeMaximize(copticImage, copticMinimize, copticTxt, ONLY_COPTIC_PREF);
        initMinimizeMaximize(languageImage, arabicMinimize, langTxt, ONLY_LANGUAGE_PREF);
    }


    private void setImageResource(final ImageView imageView, final int index, final String praySelected, final String language, final int forWhichCombination) {
        String folderPath = ResourceManagement.getFilePath("ebsalmodia", language, praySelected);
        String indexString = index < 10 ? "_0" + (index + 1) : "_" + (index + 1);
        String filename = "";
        switch (forWhichCombination) {
            case EbsalmodiaPlaceHolder.ONLY_LANGUAGE: {
                filename = indexString + "_" + language + ".PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.ONLY_COPTIC: {
                filename = indexString + "_coptic.PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.LANGUAGE_COPTIC: {
                filename = indexString + "_" + language + "_coptic.PNG";
            }
            break;
        }
        if (EbsalmodiaPrayDetailActivity.class.isInstance(getActivity())) {
            ImageFetcher mImageFetcher = ((EbsalmodiaPrayDetailActivity) getActivity()).getImageFetcher();
            mImageFetcher.loadImage(folderPath + "/" + filename, imageView);
        }
    }

    public SharedPreferences getPreferences() {
        return getActivity().getPreferences(Context.MODE_PRIVATE);
    }


    public class MinimizeOnClickListener implements View.OnClickListener {

        private ImageView imageContent;
        private ImageView minimizeIcon;
        private TextView titleView;
        private String id;

        public MinimizeOnClickListener(ImageView imageContent, ImageView minimizeIcon, TextView titleView, String id) {
            this.imageContent = imageContent;
            this.minimizeIcon = minimizeIcon;
            this.titleView = titleView;
            this.id = id;
        }

        @Override
        public void onClick(View v) {
           // initMinimizeMaximize(imageContent, minimizeIcon, titleView);
            boolean viewVisibilityToSave;
            boolean viewVisibility = getPreferences().getBoolean(id , false);

            if (viewVisibility) {
                viewVisibilityToSave = false;
                updateViews(imageContent,minimizeIcon, titleView, View.GONE,R.drawable.maximize,View.VISIBLE  );
            } else{
                viewVisibilityToSave = true;
                updateViews(imageContent,minimizeIcon, titleView, View.VISIBLE,R.drawable.minimize_mdpi,View.INVISIBLE  );
            }

            getPreferences().edit().putBoolean(id, viewVisibilityToSave).apply();
        }
    }

    private void updateViews(ImageView imageContent,ImageView minimizeIcon, TextView titleView,  int imageContentVisibility, int minimizeResource, int titleVisibiliy) {
        imageContent.setVisibility(imageContentVisibility);
        minimizeIcon.setImageResource(minimizeResource);
        titleView.setVisibility(titleVisibiliy);
    }
}
