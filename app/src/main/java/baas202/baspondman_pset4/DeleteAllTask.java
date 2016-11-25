package baas202.baspondman_pset4;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeleteAllTask extends Activity implements View.OnClickListener {

    private DBManager dbManager;

    private Button deleteall_button, cancel_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Are you sure?");

        setContentView(R.layout.deteledb_activity);

        dbManager = new DBManager(this);
        dbManager.open();

        deleteall_button = (Button) findViewById(R.id.delete_all_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        deleteall_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_all_button:
                dbManager.deleteAll();
                return_main_screen();
                break;
            case R.id.cancel_button:
                return_main_screen();
                break;

        }
    }

    public void return_main_screen() {
        Intent return_main = new Intent(getApplicationContext(), ShowTaskList.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(return_main);


    }
}
