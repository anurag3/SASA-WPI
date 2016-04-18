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
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.mppiechart.PieChartActivity;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 3/26/2016.
 */
public class EventRegistation extends AppCompatActivity implements View.OnClickListener{

    private TextView event_title1,event_desc1;
    private RadioGroup radioGroup;
    private RadioButton radioButton,radioButtonYes,radioButtonNo;
    private Button update_button,report_button;
    private String event_id,event_title,event_desc;


    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_registration);
        event_title1 = (TextView) this.findViewById(R.id.event_title);
        event_desc1 = (TextView) this.findViewById(R.id.event_desc);

        Intent intent = this.getIntent();
        event_id = intent.getStringExtra("event_id");
        event_title = intent.getStringExtra("event_title");
        event_desc = intent.getStringExtra("event_desc");

        event_title1.setText(event_title);
        event_desc1.setText(event_desc);

        radioGroup = (RadioGroup) this.findViewById(R.id.attendanceradiogroup);
        radioButtonYes = (RadioButton) this.findViewById(R.id.radioButtonYes);
        radioButtonNo = (RadioButton) this.findViewById(R.id.radioButtonNo);

        update_button = (Button) this.findViewById(R.id.update_event_btn);
        report_button = (Button) this.findViewById(R.id.event_attendance_btn);

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
            update_button.setVisibility(View.VISIBLE);
            report_button.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.submitbtn)
        {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            if (radioButton == radioButtonYes)
            {


                System.out.println("Event_id = " + event_id);
                String value[] = new String[2];
                value[0] = event_id;
                value[1] = LoginActivity.user_id;
                String count = SQLCommand.rsvpcheck;
                Cursor cursor = DBOperator.getInstance().execQuery(count,value);
                StringArray stringArray = new StringArray();
                String ars[][] = stringArray.toStr(cursor);
                int count1 = Integer.parseInt(ars[0][0]);
                System.out.println("RSVP Check = "+count1);
                if(count1==0)
                {
                    String count2 = SQLCommand.getedid;
                    Cursor cursor2 = DBOperator.getInstance().execQuery(count2);
                    StringArray stringArray2 = new StringArray();
                    String ars2[][] = stringArray2.toStr(cursor2);
                    int count3 = Integer.parseInt(ars2[0][0]);
                    count3 = count3 + 1;

                    String value1[] = new String[3];
                    value1[0] = Integer.toString(count3);
                    value1[1] = event_id;
                    value1[2] = LoginActivity.user_id;

                    String sql = SQLCommand.eventregisteration;
                    DBOperator.getInstance().execSQL(sql, value1);

                    Toast.makeText(getApplicationContext(), "You have been registered for the event", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(this, MainActivity.class);
                    this.startActivity(intent1);
                    finish();
                } else if (count1!=0)
                {
                    Toast.makeText(getApplicationContext(), "You have already registered for the event", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    this.startActivity(intent);
                    finish();
                }
            } else if (radioButton == radioButtonNo) {
                Toast.makeText(getApplicationContext(), "You have not registered for the event", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, MainActivity.class);
                this.startActivity(intent1);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Please select your registration status for the event", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.update_event_btn)
        {
            /*Intent intent = this.getIntent();
            String event_id = intent.getStringExtra("event_id");
            String event_title = intent.getStringExtra("event_title");

            String event_desc = intent.getStringExtra("event_desc");
            String event_title = getString(R.id.event_title);
            String event_desc = getString(R.id.event_desc);*/
            Intent intent = new Intent(this, UpdateEventDetails.class);
            intent.putExtra("event_id",event_id);
            intent.putExtra("event_title",event_title);
            intent.putExtra("event_desc",event_desc);
            this.startActivity(intent);
            finish();
        }
        if (id == R.id.event_attendance_btn)
        {
            String count = SQLCommand.getusercount;
            Cursor cursor = DBOperator.getInstance().execQuery(count);
            StringArray stringArray = new StringArray();
            String ars[][] = stringArray.toStr(cursor);
            int usercount = Integer.parseInt(ars[0][0]);
            System.out.println("user count = "+usercount);

            String value[] = new String[1];
            value[0] = event_id;
            String count1 = SQLCommand.getrsvpcount;
            Cursor cursor1 = DBOperator.getInstance().execQuery(count1,value);
            StringArray stringArray1 = new StringArray();
            String ars1[][] = stringArray1.toStr(cursor1);
            int rsvpcount = Integer.parseInt(ars1[0][0]);
            System.out.println("rsvp count = "+rsvpcount);


            Intent intent = new Intent(this, PieChartActivity.class);
            intent.putExtra("event_id",event_id);
            intent.putExtra("event_title",event_title);
            intent.putExtra("usercount",usercount);
            intent.putExtra("rsvpcount",rsvpcount);
            this.startActivity(intent);
        }
    }
}
