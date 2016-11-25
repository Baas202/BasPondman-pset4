package baas202.baspondman_pset4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UpdateTasks extends Activity implements OnClickListener {

    private EditText update_input_text;
    private Button update_button, delete_button;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Edit a task");

        setContentView(R.layout.update_activity);

        dbManager = new DBManager(this);
        dbManager.open();

        update_input_text = (EditText) findViewById(R.id.input_update_task);

        update_button = (Button) findViewById(R.id.update_button);
        delete_button = (Button) findViewById(R.id.delete_button);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("task");

        _id = Long.parseLong(id);

        update_input_text.setText(name);

        update_button.setOnClickListener(this);
        delete_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_button:
                String task = update_input_text.getText().toString();

                dbManager.update(_id, task);
                this.returnHome();
                break;

            case R.id.delete_button:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), ShowTaskList.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
