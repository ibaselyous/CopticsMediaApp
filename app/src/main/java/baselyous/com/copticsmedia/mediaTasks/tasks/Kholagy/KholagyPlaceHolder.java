package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 * a place holder for Kholagy Contents
 */
public class KholagyPlaceHolder extends PlaceholderFragment {


    private ImageView copticImage;
    private ImageView arabicCopticImage;
    private ImageView arabicImage;

    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.kholagy_fragment_contents_holder;
    }

    @Override
    public void updateChildViews(View rootView) {
        copticImage = (ImageView) rootView.findViewById(R.id.kholagy_coptic_image);
        arabicCopticImage = (ImageView) rootView.findViewById(R.id.kholagy_arabic_coptic_image);

        arabicImage = (ImageView) rootView.findViewById(R.id.kholagy_arabic_image);

        ImageView arabicMinimize = (ImageView) rootView.findViewById(R.id.kholagy_arabic_minimize);
        ImageView copticMinimize = (ImageView) rootView.findViewById(R.id.kholagy_coptic_minimize);

        ImageView arabicCopticMinimize = (ImageView) rootView.findViewById(R.id.kholagy_arabic_coptic_minimize);

        arabicCopticMinimize.setOnClickListener(new MinimizeOnClickListener(arabicCopticImage, arabicCopticMinimize));
        copticMinimize.setOnClickListener(new MinimizeOnClickListener(copticImage, copticMinimize));
        arabicMinimize.setOnClickListener(new MinimizeOnClickListener(arabicImage, arabicMinimize));


        setImageResource ( arabicImage, CONTENTS_LANGUAGE_ARRAY );
        setImageResource ( arabicCopticImage, CONTENTS_COMPINATION_ARRAY);
        setImageResource(copticImage, CONTENTS_COPTIC_ARRAY);




    }

    private void setImageResource(ImageView arabicImage, String contentsLanguageArray) {
        Bundle extras = getArguments();
        byte[] arabicArray = extras.getByteArray(contentsLanguageArray);
        Bitmap arabicBitmap = BitmapFactory.decodeByteArray(arabicArray, 0, arabicArray.length);
        arabicImage.setImageBitmap(arabicBitmap);
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
}
