package team2.library.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by Anurag on 3/27/2016.
 */
public class UpdateEventDetails extends AppCompatActivity {

    private EditText event_title1;
    private EditText event_desc1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_event_details);

        event_title1 = (EditText) this.findViewById(R.id.event_title);
        event_desc1 = (EditText) this.findViewById(R.id.event_desc);

        Intent intent = this.getIntent();
        String event_id = intent.getStringExtra("event_id");

        event_title1.setText(intent.getStringExtra("event_title"));
        event_desc1.setText(intent.getStringExtra("event_desc"));
    }
}