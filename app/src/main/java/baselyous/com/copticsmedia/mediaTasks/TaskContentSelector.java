package baselyous.com.copticsmedia.mediaTasks;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.adapters.AppBaseAdapter.taskListAdapter.TaskListAdapter;
import baselyous.com.copticsmedia.mediaTasks.tasks.TaskList;

/**
 * Created by Ihab Baselyous on 09.10.2015.
 * select a pray from the prayList
 */
public class TaskContentSelector extends ListActivity {


    private ArrayList<String> contentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        if (intent != null && intent.hasExtra("content")) {
            contentList = intent.getStringArrayListExtra("content");
            if (intent.hasExtra("task")) {
                String task = intent.getExtras().getString("task");
                if (task != null && task.equals("agpeya")) {
                    TaskListAdapter adapter = new TaskListAdapter(getTaskList(intent.getIntegerArrayListExtra("icons")), getApplicationContext(), true);
                    setListAdapter(adapter);
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, contentList);
                    setListAdapter(adapter);
                }
            }
        }
    }

    private List<TaskList> getTaskList(ArrayList<Integer> iconList) {
        List<TaskList> taskList = new ArrayList<>();
        for (int i = 0; i < contentList.size(); i++) {
            taskList.add(new TaskList(contentList.get(i), iconList.get(i), ""));
        }
        return taskList;
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("itemClickedString", contentList.get(position));
        intent.putExtra("itemClicked", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
