package baselyous.com.copticsmedia.mediaTasks.tasks;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import baselyous.com.copticsmedia.R;

/**
 * Created by Ihab Baselyous on 02.10.2015.
 * defines a list of tasks
 */
public class TaskList {
    private String taskName;
    private int icon;
    private String description;

    public TaskList(String taskName, int icon, String description) {
        this.taskName = taskName;
        this.icon = icon;
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<TaskList> getTaskList(Context context) {
        List<TaskList> taskList = new ArrayList<>();
        taskList.add(new TaskList(context.getString(R.string.agpya), R.drawable.gold_logo, context.getString(R.string.agpya_description)));
        taskList.add(new TaskList(context.getString(R.string.kholagy), R.drawable.gold_logo, context.getString(R.string.kholagy_description)));
        taskList.add(new TaskList(context.getString(R.string.ebsalmodia), R.drawable.gold_logo, context.getString(R.string.ebsalmodia_description)));

        return taskList;
    }
}
