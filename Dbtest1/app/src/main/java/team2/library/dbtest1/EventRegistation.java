package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 3/26/2016.
 */
public class EventRegistation extends AppCompatActivity implements View.OnClickListener{

    private TextView event_title1,event_desc1;
    private RadioGroup radioGroup;
    private RadioButton radioButton,radioButtonYes,radioButtonNo;
    private Button button;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_registration);
        event_title1 = (TextView) this.findViewById(R.id.event_title);
        event_desc1 = (TextView) this.findViewById(R.id.event_desc);

        Intent intent = this.getIntent();
        String event_title = intent.getStringExtra("event_title");
        String event_desc = intent.getStringExtra("event_desc");

        event_title1.setText(event_title);
        event_desc1.setText(event_desc);

        radioGroup = (RadioGroup) this.findViewById(R.id.attendanceradiogroup);
        radioButtonYes = (RadioButton) this.findViewById(R.id.radioButtonYes);
        radioButtonNo = (RadioButton) this.findViewById(R.id.radioButtonNo);

        button = (Button) this.findViewById(R.id.update_event_btn);

        String [] value = new String[1];
        value[0] = LoginActivity.user_id;
        int count;

        String admincheck= SQLCommand.adminflag;
        Cursor cursor = DBOperator.getInstance().execQuery(admincheck,value);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        count=Integer.parseInt(ars[0][0]);
        System.out.println("Admin Check = "+count);

        if(count==1)
        {
            button.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton=(RadioButton)findViewById(selectedId);
        if (radioButton == radioButtonYes)
        {
            
            System.out.println("COOOOOOLLLLLL");
        }
        else if(radioButton == radioButtonNo)
        {
            System.out.println("Nooooooooooo");
        }
        else
        {
            System.out.println("None Selected");
        }
    }
}
