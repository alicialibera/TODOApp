package au.edu.jcu.cp3406.todo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AddItemActivity extends AppCompatActivity {
    private SharedPreferences dateSource;
    EditText editText;
    private String newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        dateSource = getSharedPreferences("todo items", Context.MODE_PRIVATE);

        editText = findViewById(R.id.editText);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        newItem = String.valueOf(editText.getText());
        switch (item.getItemId()) {
            case android.R.id.home:
                if (newItem.equals("")) {
                    Toast.makeText(this, "No item was given", Toast.LENGTH_SHORT).show();
                } else {
                    Set<String> items = dateSource.getStringSet("items", new HashSet<String>());
                    assert items != null;
//                    items.clear();
                    items.add(newItem);

                    dateSource.edit().clear().putStringSet("items", items).apply();

                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
