package com.grucchi.tasks;

import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import org.json.JSONArray;
import java.util.*;
import android.widget.EditText;
import android.widget.Button;

import android.widget.ListView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences dataStore;
    private JSONArray jsonArr;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        final ArrayList<Task> savedList = getSavedTaskArray(dataStore);

        // アダプターにセット
        ListView listView = findViewById(R.id.list_view);
        final ListAdapter listAdapter = new ListAdapter(MainActivity.this);
        listAdapter.setTaskList(savedList);
        listView.setAdapter(listAdapter);

        // テキストからチェックボックス作成
        // 追加ボタン
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                // テキストエリア
                editText = findViewById(R.id.editText);
                String text = editText.getText().toString();
                editText.setText(""); // ボタンを押したらテキストエリアの中を空にする

                Task task = createTask(text);
                savedList.add(task);

                // リストから配列を作成
                JSONArray jsonArr = toJsonArray(savedList);

                dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
                Editor editor = dataStore.edit();

                // 文字列に変換して保存
                editor.putString("Tasks", jsonArr.toString());
                editor.apply();

                listAdapter.notifyDataSetChanged();
            }
        });
    }
    private Task createTask(String name) {
        Task task = new Task();
        task.setName(name);
        task.setStatus(1);
        return task;
    }

    private ArrayList<Task> getTaskList(String saved) {
        ArrayList<Task> savedList = new ArrayList<>();
        for (String s : Arrays.asList(saved.split(","))) {
            // 文字列だけ許可
            s = trim(s);
            if (s.equals("")) {
                continue;
            }
            Task t = createTask(s);
            savedList.add(t);
        }
        return savedList;
    }

    // dataStoreからArrayList作成
    private ArrayList<Task> getSavedTaskArray(SharedPreferences dataStore) {
        String saved = dataStore.getString("Tasks", "Nothing");
        ArrayList<Task> savedList = getTaskList(saved);
        return savedList;
    }

    // toJsonArray
    private JSONArray toJsonArray(ArrayList<Task> list) {
        jsonArr = new JSONArray();
        for (Task t : list) {
            jsonArr.put(t.getName());
        }
        return jsonArr;
    }

    private String trim(String s) {
        s = s.replaceAll("\\[", "");
        s = s.replaceAll("]", "");
        s = s.replaceAll("\"", "");
        s = s.replaceAll("\\\\", "");
        return s;
    }
}
