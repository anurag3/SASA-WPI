package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 12/8/2015.
 */

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    private EditText profile_firstname;
    private EditText profile_lastname;
    private EditText profile_email;
    private EditText profile_phone;
    private EditText profile_pass;
    private Button update;

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        profile_firstname=(EditText) this.findViewById(R.id.profile_firstname);
        profile_lastname=(EditText) this.findViewById(R.id.profile_lastname);
        profile_email=(EditText) this.findViewById(R.id.profile_email);
        profile_phone=(EditText) this.findViewById(R.id.profile_phone);
        profile_pass=(EditText) this.findViewById(R.id.profile_pass);

        String [] value= new String[1];
        value[0]=LoginActivity.user_id;
        String sql= SQLCommand.showprofile;
        Cursor cursor = DBOperator.getInstance().execQuery(sql,value);

        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        //String first_name=cursor.getString(0);
        String first_name=ars[0][0];
        String last_name=ars[0][1];
        String pass=ars[0][2];
        String email=ars[0][3];
        String phone=ars[0][4];
        profile_firstname.setText(first_name);
        profile_lastname.setText(last_name);
        profile_pass.setText(pass);
        profile_email.setText(email);
        profile_phone.setText(phone);

        update= (Button) this.findViewById(R.id.update_btn);
        update.setOnClickListener(this);

}

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.update_btn)
        {
            String value[]=new String[6];
            value[0]= profile_firstname.getText().toString();
            value[1]= profile_lastname.getText().toString();
            value[2]= profile_email.getText().toString();
            value[3]= profile_pass.getText().toString();
            value[4]= profile_phone.getText().toString();
            value[5]= LoginActivity.user_id;

            String sql=SQLCommand.updateprofile;
            System.out.println(sql);
            DBOperator.getInstance().execSQL(sql, value);
            Toast.makeText(getApplicationContext(),"Profile Updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }
}
