package baas202.baspondman_pset4;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class ShowTaskList extends ActionBarActivity {

    private DBManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_TODO};

    final int[] to = new int[] { R.id.id, R.id.title,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainscreen_activity);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.read();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.list_row_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView titleTextView = (TextView) view.findViewById(R.id.title);

                String id = idTextView.getText().toString();
                String task = titleTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), UpdateTasks.class);
                modify_intent.putExtra("task", task);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });


    }

    public void deleteDB(View view) {
        {
            dbManager = new DBManager(this);
            dbManager.open();
            dbManager.read();
            dbManager.deleteAll();
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddNewTask.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }
}