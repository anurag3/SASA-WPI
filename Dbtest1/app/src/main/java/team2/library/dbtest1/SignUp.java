package team2.library.dbtest1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chandan on 12/5/2015.
 */

public class SignUp extends Activity{
 DBHelper helper = new DBHelper(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        TextView tv=(TextView)findViewById(R.id.textView);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Vizel.otf");
        tv.setTypeface(face);

    }


public void onClickSignup(View v){

    if(v.getId()==R.id.btn_signup){

        EditText first_name = (EditText)findViewById(R.id.first_name);
        EditText last_name = (EditText)findViewById(R.id.last_name);
        EditText st_email = (EditText)findViewById(R.id.input_email);
        EditText pass = (EditText)findViewById(R.id.input_password1);
        EditText cpass = (EditText)findViewById(R.id.input_password2);

        String firstname = first_name.getText().toString();
        String lastname = last_name.getText().toString();
        String emailstr = st_email.getText().toString();
        String pstr = pass.getText().toString();
        String cpstr = cpass.getText().toString();
        if(!pstr.equals(cpstr))
        {
            Toast pass1 = Toast.makeText(SignUp.this,"Passwords don't match!",Toast.LENGTH_SHORT);
            pass1.show();
        }
        else{

            DBContact c = new DBContact();
            c.setfirstName(firstname);
            c.setlastName(lastname);
            c.setEmail(emailstr);
            c.setPass(pstr);
            helper.insertContact(c);
            Toast pass2 = Toast.makeText(SignUp.this,"User Created Successfully",Toast.LENGTH_SHORT);
            pass2.show();
            Intent it1 = new Intent(SignUp.this,login.class);
            startActivity(it1);
        }

    }


}


}