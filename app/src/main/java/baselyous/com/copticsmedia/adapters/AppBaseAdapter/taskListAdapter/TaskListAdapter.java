package baselyous.com.copticsmedia.adapters.AppBaseAdapter.taskListAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import baselyous.com.copticsmedia.R;
import baselyous.com.copticsmedia.adapters.AppBaseAdapter.ApplicationBaseAdapter;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskList;

/**
 * Created by Ihab Baselyous on 02.10.2015.
 */
@SuppressWarnings("unchecked")
public class TaskListAdapter extends ApplicationBaseAdapter {

    private final Context context;

    public TaskListAdapter(List<TaskList> list, Context context) {
        super(list, context);
        this.context = context;
    }

    @Override
    protected void inflateChildViews(View convertView, int position) {
        TextView title = (TextView) convertView.findViewById(R.id.task_row_title);
        TextView description = (TextView) convertView.findViewById(R.id.task_row_description);
        ImageView icon = (ImageView) convertView.findViewById(R.id.task_row_icon);
        if (getItem(position) != null) {
            title.setText(((TaskList) getItem(position)).getTaskName());
            description.setText(((TaskList) getItem(position)).getDescription());
            icon.setImageDrawable(context.getResources().getDrawable(((TaskList) getItem(position)).getIcon()));

        }

    }

    @Override
    protected int getViewResources() {
        return R.layout.task_list_layout;
    }
}
