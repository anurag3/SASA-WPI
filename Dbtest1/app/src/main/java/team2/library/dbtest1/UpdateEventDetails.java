package team2.library.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 3/27/2016.
 */
public class UpdateEventDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText event_title1;
    private EditText event_desc1;
    private String event_id,event_title,event_desc;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_event_details);

        event_title1 = (EditText) this.findViewById(R.id.event_title);
        event_desc1 = (EditText) this.findViewById(R.id.event_desc);


        Intent intent = this.getIntent();
        event_id =  intent.getStringExtra("event_id");
        event_title = intent.getStringExtra("event_title");
        event_desc = intent.getStringExtra("event_desc");

        event_title1.setText(event_title);
        event_desc1.setText(event_desc);
    }


    @Override
    public void onClick(View v) {
        String value[] = new String[3];
        value[0] = event_title1.getText().toString().trim();
        value[1] = event_desc1.getText().toString().trim();
        value[2] = event_id;
        System.out.println("event id = "+event_id);
        System.out.println("event_title1 = "+event_title1.getText().toString().trim());
        System.out.println("event_desc1 = "+event_desc1.getText().toString().trim());
        System.out.println(value);
        String updateEventDetails = SQLCommand.updateEventDetails;
        DBOperator.getInstance().execQuery(updateEventDetails,value);
        Toast.makeText(getApplicationContext(), "Event Details Successfully Updated", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}