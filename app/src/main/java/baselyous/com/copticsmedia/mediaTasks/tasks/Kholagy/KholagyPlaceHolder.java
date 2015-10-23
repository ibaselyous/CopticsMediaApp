package baselyous.com.copticsmedia.mediaTasks.tasks.kholagy;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 * a place holder for Kholagy Contents
 */
public class KholagyPlaceHolder extends PlaceholderFragment {

 //   private TextView arabicTextView;
    private TextView copticTextView;
    private TextView arabicCopticTextView;

    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.kholagy_fragment_contents_holder;
    }

    @Override
    public void updateChildViews(View rootView) {
//        arabicTextView = (TextView) rootView.findViewById(R.id.kholagy_arabic_text);
        copticTextView = (TextView) rootView.findViewById(R.id.kholagy_coptic_text);
        arabicCopticTextView = (TextView) rootView.findViewById(R.id.kholagy_arabic_coptic_text);

        ImageView arabicImage = (ImageView) rootView.findViewById(R.id.kholagy_arabic_image);

        ImageView arabicMinimize = (ImageView) rootView.findViewById(R.id.kholagy_arabic_minimize);
        ImageView copticMinimize = (ImageView) rootView.findViewById(R.id.kholagy_coptic_minimize);
        ImageView arabicCopticMinimize = (ImageView) rootView.findViewById(R.id.kholagy_arabic_coptic_minimize);

        arabicCopticMinimize.setOnClickListener(new MinimizeOnClickListener(arabicImage, arabicCopticMinimize));
        copticMinimize.setOnClickListener(new MinimizeOnClickListener(copticTextView, copticMinimize));
  //      arabicMinimize.setOnClickListener(new MinimizeOnClickListener(arabicTextView, arabicMinimize));




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
