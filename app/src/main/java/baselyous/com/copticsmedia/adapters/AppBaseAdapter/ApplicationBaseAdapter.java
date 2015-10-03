package baselyous.com.copticsmedia.adapters.AppBaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Ihab Baselyous on 02.10.2015.
 * root class for the adapters
 */
public abstract class ApplicationBaseAdapter<E> extends BaseAdapter {

    private final List<E> list;
    private final LayoutInflater inflater;

    public ApplicationBaseAdapter(List<E> list, Context context) {
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (list != null && !list.isEmpty())
            return list.size();
        else
            return 0;
    }

    @Override
    public E getItem(int position) {
        if (list != null && !list.isEmpty()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(getViewResources(), null);
        }
        inflateChildViews(convertView, position);
        return convertView;
    }

    protected abstract void inflateChildViews(View convertView, int position);

    protected abstract int getViewResources();
}
