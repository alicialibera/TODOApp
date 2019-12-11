package au.edu.jcu.cp3406.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView todoList;
    ArrayAdapter<String> adapter;
    private SharedPreferences dateSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = findViewById(R.id.listview);

        dateSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item);
        todoList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(this, AddItemActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        //saves items
        super.onStart();
        adapter.clear();
        Set<String> newItems = dateSource.getStringSet("items", new HashSet<String>());
        assert newItems != null;
        adapter.addAll(newItems);

        //removes item
        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView itemview = (TextView) view;
                adapter.remove(itemview.getText().toString());
            }
        });
    }

}
