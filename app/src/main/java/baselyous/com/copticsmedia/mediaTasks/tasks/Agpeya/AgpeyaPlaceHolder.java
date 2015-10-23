package baselyous.com.copticsmedia.mediaTasks.tasks.agpeya;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.mediaTasks.tasks.PlaceholderFragment;

/**
 * Created by Ihab Baselyous on 19.10.2015.
 */
public class AgpeyaPlaceHolder extends PlaceholderFragment {


    @Override
    protected int getPlaceHolderLayoutResID() {
        return R.layout.agpeya_fragment_contents_holder;
    }


    @Override
    public void updateChildViews(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getArguments().getString(CONTENTS_DATA_ARRAY));
        textView.setMovementMethod(new ScrollingMovementMethod());
        addToTextViewList(textView);

    }

}
