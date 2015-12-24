package team2.library.dbtest1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team2.library.dbtest1.util.DBOperator;


public class login extends AppCompatActivity {


    EditText et1,et2;
    public static String st_id;
    DBHelper helper = new DBHelper(this);
    protected void onCreate(Bundle savedInstanceState) {
        //System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }
        //Font on login page
        TextView tv=(TextView)findViewById(R.id.textView);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Vizel.otf");
        tv.setTypeface(face);

        et1 = (EditText) this.findViewById(R.id.userName);
        et2 = (EditText) this.findViewById(R.id.userPass);

    }

    public void onClick(View view)
    {
        DBOperator dbOperator= new DBOperator();
        if (view.getId()==R.id.login_button) {

            //attemptLogin();
            //Intent intent = new Intent(this, mainpage.class);
            //this.startActivity(intent);
            EditText uname = (EditText)findViewById(R.id.userName);
            String uname_Str = uname.getText().toString();
            EditText upass = (EditText)findViewById(R.id.userPass);
            String upass_Str = upass.getText().toString();
            View focusView = null;
            boolean cancel = false;

            String pword = dbOperator.searchPass(upass_Str);
            System.out.println("pword "+pword);
            System.out.println("upass_Str "+upass_Str);

            if (TextUtils.isEmpty(upass_Str) ) {
                et2.setError(getString(R.string.error_invalid_password));
                focusView = et2;
                cancel = true;
            }

            // Check for a valid email address.
            if (TextUtils.isEmpty(uname_Str)) {
                et1.setError(getString(R.string.error_field_required));
                focusView = et1;
                cancel = true;
            }

            else if(upass_Str.equals(pword)){
                st_id=DBOperator.c;
                Intent intent = new Intent(this, mainpage.class);
                this.startActivity(intent);

            }else et2.setError(getString(R.string.error_incorrect_password));
            /*else if(isPasswordInvalid(upass_Str)){
                et2.setError(getString(R.string.error_incorrect_password));
                focusView = et2;
                cancel=true;
            }*/
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

          /*  else if (uname_Str.isEmpty()){
                et2.setError(getString(R.string.error_invalid_password);
                //Toast temp1 = Toast.makeText(login.this,"Please enter Username & Password",Toast.LENGTH_SHORT);
                //temp1.show();
            }else if(upass_Str.isEmpty()){
                et2.setError(getString(R.string.error_invalid_password);
                focusView = et2;
                cancel = true;
            }


            else {
                Toast temp = Toast.makeText(login.this,"Username & Password don't match",Toast.LENGTH_SHORT);
                temp.show();
            }*/

        }
    }}

   /* public void onForgotClick(View v){
        if(v.getId()==R.id.forgot_pass){
            Intent it1 = new Intent(login.this,Forgot.class);
            startActivity(it1);
        }
    }


    public void onSignupClick(View v){
        if (v.getId()==R.id.btn_signup){
            Intent intent = new Intent(login.this, SignUp.class);
            startActivity(intent);
        }
    }*/













    private void attemptLogin() {
        et1.setError(null);
        et2.setError(null);
        String username = et1.getText().toString();
        String password = et2.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(password) ) {
            et2.setError(getString(R.string.error_invalid_password));
            focusView = et2;
            cancel = true;
        }else if(isPasswordInvalid(password)){
            et2.setError(getString(R.string.error_incorrect_password));
            focusView = et2;
            cancel=true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            et1.setError(getString(R.string.error_field_required));
            focusView = et1;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }else {
            nextpage();
        }
    }
    public boolean isPasswordInvalid(String password) {
        //TODO: Replace this with your own logic
        //return password.length() > 0;
            if (et1.getText().toString().trim().equals("sheetal")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("yuan")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("chandan")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("anurag")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("yifan")
                    && et2.getText().toString().trim().equals("123")) {
                return false;
            } else if (et1.getText().toString().trim().equals("A")
                    && et2.getText().toString().trim().equals("a")) {
                return false;
            }else {
                return true;
            }
    }



        public void nextpage()
    {

        Intent intent = new Intent(this, mainpage.class);
        this.startActivity(intent);
    }

}
