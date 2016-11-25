package baas202.baspondman_pset4;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNewTask extends Activity implements OnClickListener {

    private EditText input_text;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the content for the popup box
        setTitle("Add a new task");

        setContentView(R.layout.add_task_activity);

        input_text = (EditText) findViewById(R.id.input_add_task);

        Button add_button_todo = (Button) findViewById(R.id.add_button);

        dbManager = new DBManager(this);
        dbManager.open();
        add_button_todo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // inserts new task when Add button is clicked
            case R.id.add_button:

                final String name = input_text.getText().toString();

                dbManager.insert(name);

                Intent main = new Intent(AddNewTask.this, ShowTaskList.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}