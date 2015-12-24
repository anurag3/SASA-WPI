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
 * Created by Chandan on 12/6/2015.
 */
public class Forgot extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);
        TextView tv=(TextView)findViewById(R.id.textView);
        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Vizel.otf");
        tv.setTypeface(face);
    }

    public void onForgotClick(View v){

        if(v.getId()==R.id.sub_button){
            Toast pass2 = Toast.makeText(Forgot.this,"Password sent to registerd Email",Toast.LENGTH_SHORT);
            pass2.show();
            Intent it1 = new Intent(Forgot.this,login.class);
            startActivity(it1);
        }
    }
}
