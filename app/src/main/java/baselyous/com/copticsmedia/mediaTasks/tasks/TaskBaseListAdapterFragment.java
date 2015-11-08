package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.MainActivityFragment;
import baselyous.com.copticsmedia.adapters.AppBaseAdapter.taskListAdapter.TaskListAdapter;
import baselyous.com.copticsmedia.recources.ResourceManagement;

/**
 * Created by Ihab Baselyous on 28.10.2015.
 * a base function for defining each book pray contents
 */
public abstract class TaskBaseListAdapterFragment extends TaskBaseListFragment {

    protected abstract List<Integer> getIconList();

    protected abstract String getTaskName();

    public void setListContents() {
        TaskListAdapter adapter = new TaskListAdapter(getTaskList(), getActivity());
        setListAdapter(adapter);

    }

    private List<TaskList> getTaskList() {
        List<TaskList> taskList = new ArrayList<>();
        List<Integer> iconList = getIconList();
        List<String> contentList = getBookContents();

        for (int i = 0; i < contentList.size(); i++) {
            taskList.add(new TaskList(contentList.get(i), iconList.get(i), ""));
        }
        return taskList;
    }


    public List<String> getBookContents() {
        String baseLanguage = MainActivityFragment.languageSelected(getActivity());
        Log.i("reached get book", " " + getTaskName());
        return ResourceManagement.readTaskContentsFile(getActivity(), getTaskName(), baseLanguage, "", "salawat");
    }

}
