package com.grucchi.tasks;

import android.content.Context;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.graphics.Color;
import android.widget.CompoundButton;

import java.util.ArrayList;

/**
 * Created by imadashoudai on 2019/01/13.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Task> list;

    public ListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public ArrayList<Task> getTaskList() {
        return this.list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public long getItemId(int position) {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.task_list, parent, false);

        final CheckBox checkBox = ((CheckBox)convertView.findViewById(R.id.checkBox));
        final Task targetTask = list.get(position);
        checkBox.setText(String.valueOf(targetTask.getName()));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBox.setTextColor(Color.LTGRAY);
                // チェック後に削除
                removeTask(getTaskList(), targetTask);
            }
        });

        return convertView;
    }

    private void removeTask(ArrayList<Task> list, Task task) {
       list.remove(task);
    }
}
