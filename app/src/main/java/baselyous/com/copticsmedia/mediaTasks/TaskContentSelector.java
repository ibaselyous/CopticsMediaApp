package baselyous.com.copticsmedia.mediaTasks;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ihab Baselyous on 09.10.2015.
 * select a pray from the prayList
 */
public class TaskContentSelector extends FragmentActivity implements AdapterView.OnItemClickListener {


    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);
        list = (ListView) findViewById(android.R.id.list);
        list.setOnItemClickListener(this);
        list.setBackgroundColor(Color.BLACK);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        if (intent != null && intent.hasExtra("content")) {
            ArrayList<String> contentList = intent.getStringArrayListExtra("content");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, contentList);
            list.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("itemClicked", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
