package team2.library.dbtest1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Anurag on 11/21/2015.
 */
public class SellPage extends AppCompatActivity {

    public static String title;
    public static String description;
    EditText et1,et3;

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_page);
        et1 = (EditText) this.findViewById(R.id.editText1);

        et3 = (EditText) this.findViewById(R.id.editText3);
    }
    public void onClick(View view)
    {
        int id=view.getId();
        if (id==R.id.home)
        {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        if (id==R.id.button2) {

        et1.setError(null);
        //et2.setError(null);
        et3.setError(null);
        title = et1.getText().toString().trim();
        description = et3.getText().toString().trim();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(title) ) {
            et1.setError(getString(R.string.error_title));
            focusView = et1;
            cancel = true;
        } else
        if (TextUtils.isEmpty(description) ) {
            et3.setError(getString(R.string.error_desc));
            focusView = et3;
            cancel = true;
        } else
        /*if (cancel) {
            // There was an error; don't attempt LoginActivity1 and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {*/
        {
            //Toast.makeText(getApplicationContext(),"Item Successfully Posted", Toast.LENGTH_LONG).show();
           /* for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext())
            {
                System.out.println(cursor.getString(0));
            }*/
            Intent intent = new Intent(this, AddNewItem.class);
            this.startActivity(intent);
        }
        }
    }
}
