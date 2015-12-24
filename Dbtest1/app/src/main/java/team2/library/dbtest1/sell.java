package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 11/21/2015.
 */
public class sell extends AppCompatActivity {

    EditText et1,et3;
    public static int count1;
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellpage);
        et1 = (EditText) this.findViewById(R.id.editText1);

        et3 = (EditText) this.findViewById(R.id.editText3);
    }
    public void onClick(View view)
    {
        int id=view.getId();
        if (id==R.id.back)
        {
            Intent intent = new Intent(this, mainpage.class);
            this.startActivity(intent);
        }

        if (id==R.id.button2) {
   /*         post();
        }
    }

    private void post()
    {*/
        et1.setError(null);
        //et2.setError(null);
        et3.setError(null);
        String title = et1.getText().toString().trim();
        //String category = et2.getText().toString().trim();
        String description = et3.getText().toString().trim();
            int post_hit_counter=1;
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(title) ) {
            et1.setError(getString(R.string.error_title));
            focusView = et1;
            cancel = true;
        } else
       /* if (TextUtils.isEmpty(category) ) {
            et2.setError(getString(R.string.error_category));
            focusView = et2;
            cancel = true;
        } else*/
        if (TextUtils.isEmpty(description) ) {
            et3.setError(getString(R.string.error_desc));
            focusView = et3;
            cancel = true;
        } else
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }else {
        {


            String count= SQLCommand.postCount;
            Cursor cursor = DBOperator.getInstance().execQuery(count);
            StringArray stringArray = new StringArray();
            String ars[][]= stringArray.toStr(cursor);
            count1=Integer.parseInt(ars[0][0]);
            count1=count1+1;
            String value[]=new String[5];
            value[0]=Integer.toString(count1);
            value[1]=title;
            value[2]=login.st_id;
            value[3]=description;
            value[4]=Integer.toString(post_hit_counter);

            String sql=SQLCommand.postInsert;
            System.out.println(sql);
            DBOperator.getInstance().execSQL(sql,value);
            //Toast.makeText(getApplicationContext(),"Item Successfully Posted", Toast.LENGTH_LONG).show();
           /* for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext())
            {
                System.out.println(cursor.getString(0));
            }*/
            Intent intent = new Intent(this, Add_new_item.class);
            this.startActivity(intent);
        }
        }
    }}
}
