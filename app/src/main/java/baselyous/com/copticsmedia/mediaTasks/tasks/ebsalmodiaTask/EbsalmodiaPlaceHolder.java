package baselyous.com.copticsmedia.mediaTasks.tasks.ebsalmodiaTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskBaseDetailFragment;
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
    private boolean isAttached;
    private ImageFetcher mImageFetcher;




    @Override
    public void initViews(View rootView) {
        super.initViews(rootView);
    }

    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.tasbeha_fragment_content_holder;
    }

    @Override
    public void updateChildViews(View rootView) {
        ImageView copticImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_coptic_image);
        ImageView languageCopticImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_coptic_image);
        ImageView languageImage = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_image);

        ImageView arabicMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_minimize);
        ImageView copticMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_coptic_minimize);

        ImageView arabicCopticMinimize = (ImageView) rootView.findViewById(R.id.ebsalmodia_language_coptic_minimize);

        arabicCopticMinimize.setOnClickListener(new MinimizeOnClickListener(languageCopticImage, arabicCopticMinimize));
        copticMinimize.setOnClickListener(new MinimizeOnClickListener(copticImage, copticMinimize));
        arabicMinimize.setOnClickListener(new MinimizeOnClickListener(languageImage, arabicMinimize));

        int pageNr = getArguments().getInt(ARG_SECTION_NUMBER);
        String praySelected = getArguments().getString(PRAY_SELECTED);
        String language = getArguments().getString(LANGUAGE_SELECTED);
        language = language != null ? language.toLowerCase() : "arabic";
        setImageResource(languageImage, pageNr, praySelected, language, ONLY_LANGUAGE);
        setImageResource(languageCopticImage, pageNr, praySelected, language, LANGUAGE_COPTIC);
        setImageResource(copticImage, pageNr, praySelected, language, ONLY_COPTIC);

    }








    private void setImageResource(final ImageView imageView,final int index,final String praySelected,final String language,final int forWhichCombination) {
        /*new AsyncTask<Void, byte[] ,byte[]>(){


            @Override
            protected byte[] doInBackground(Void... params) {
                return getDrawableByteArrayFromAssets(pageNr, praySelected, language, forWhichCombination);
            }

            @Override
            protected void onPostExecute(byte[] contentsLanguageArray) {
                Bitmap arabicBitmap = BitmapFactory.decodeByteArray(contentsLanguageArray, 0, contentsLanguageArray.length);
                imageView.setImageBitmap(arabicBitmap);
            }
        }.execute(null, null, null);*/
        String folderPath = ResourceManagement.getFilePath("ebsalmodia", language, praySelected);
        String indexString = index < 10 ? "_0"+ (index + 1):"_" + (index + 1);
        String filename = "" ;
        switch (forWhichCombination) {
            case EbsalmodiaPlaceHolder.ONLY_LANGUAGE: {
                filename = indexString + "_" + language + ".PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.ONLY_COPTIC: {
                filename = indexString + "_coptic.PNG";
            }
            break;
            case EbsalmodiaPlaceHolder.LANGUAGE_COPTIC:{
                filename = indexString + "_" + language + "_coptic.PNG";
            }
            break;
        }
        Log.i("Reched ebslmodia", "true" + getActivity().getClass().getName());
        if (EbsalmodiaPrayDetailActivity.class.isInstance(getActivity())) {
            Log.i("Reched ebslmodia", "true");
            mImageFetcher = ((EbsalmodiaPrayDetailActivity) getActivity()).getImageFetcher();
            mImageFetcher.loadImage(folderPath +"/" + filename, imageView);
        }

    }

    private byte[] getDrawableByteArrayFromAssets(int pageNr, String praySelected, String language, int forWhichCombination) {
        if(isAttached)
            return ResourceManagement.getDrawableImageResource(TaskBaseDetailFragment.currentContext , getString(R.string.ebsalmodia).toLowerCase(), praySelected, pageNr, language, forWhichCombination);
        else
            return new byte[0];

    }

    public SharedPreferences getPreferences() {
        return getActivity().getPreferences(Context.MODE_PRIVATE);
    }


    public class MinimizeOnClickListener implements View.OnClickListener {

        private View textView;
        private ImageView minimizeIcon;

        public MinimizeOnClickListener(View textView, ImageView minimizeIcon) {
            this.textView = textView;
            this.minimizeIcon = minimizeIcon;
        }

        @Override
        public void onClick(View v) {
            int viewVisibilityToSave = View.VISIBLE;
            int viewVisibility = getPreferences().getInt(String.valueOf(textView.getId()), View.VISIBLE);

            if (viewVisibility == View.VISIBLE) {
                textView.setVisibility(View.GONE);
                minimizeIcon.setImageResource(R.drawable.maximize);
                viewVisibilityToSave = View.GONE;

            } else if (viewVisibility == View.GONE) {
                textView.setVisibility(View.VISIBLE);
                minimizeIcon.setImageResource(R.drawable.minimize_mdpi);
                viewVisibilityToSave = View.VISIBLE;
            }

            getPreferences().edit().putInt(String.valueOf(textView.getId()), viewVisibilityToSave).commit();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttached = true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isAttached = false;
    }



    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private int data = 0;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage collected
            imageViewReference = new WeakReference<>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            return decodeSampledBitmapFromResource(getResources(), data, 100, 100);
        }

        private Bitmap decodeSampledBitmapFromResource(Resources resources, int data, int i, int i1) {
            return null;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public void loadBitmap(int resId, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId);
    }
}
