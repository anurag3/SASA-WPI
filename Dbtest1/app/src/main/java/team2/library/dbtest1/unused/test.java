package team2.library.dbtest1.unused;

/**
 * Created by Anurag on 12/5/2015.
 */
/*public class test {
}
*/



        import android.app.Activity;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TableLayout;
        import android.widget.TableRow;
        import android.widget.TextView;

        import team2.library.dbtest1.R;
        import team2.library.dbtest1.constant.SQLCommand;

public class test extends Activity {
    String fname,lname,email;
    SQLiteDatabase db;
    TableRow tableRow;
    TextView textView,textView1,textView2,textView3,textView4,textView5;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tablelist);
        db=openOrCreateDatabase("abc",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Student(fname VARCHAR,lname VARCHAR,email VARCHAR);");
    }
   /* public void data(View view)
    {
        EditText edittext1=(EditText )findViewById(R.id.firstname);
        EditText edittext2=(EditText )findViewById(R.id.lastname);
        EditText edittext3=(EditText )findViewById(R.id.email);
        fname=edittext1.getText().toString();
        lname=edittext2.getText().toString();
        email=edittext3.getText().toString();
        db.execSQL("INSERT INTO  Student VALUES('"+fname+"','"+lname+"','"+email+"');");


    }*/
    public void showdata(View view)
    {
        Cursor c=db.rawQuery(SQLCommand.QUERY_1, null);
        int count= c.getCount();
        c.moveToFirst();
        TableLayout tableLayout = new TableLayout(getApplicationContext());
        tableLayout.setVerticalScrollBarEnabled(true);
        TableRow tableRow;
        TextView textView,textView1,textView2,textView3,textView4,textView5;
        tableRow = new TableRow(getApplicationContext());
        textView=new TextView(getApplicationContext());
        textView.setText("Firstname");
        textView.setTextColor(Color.RED);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setPadding(20, 20, 20, 20);
        tableRow.addView(textView);
        textView4=new TextView(getApplicationContext());
        textView4.setText("LastName");
        textView4.setTextColor(Color.RED);
        textView4.setTypeface(null, Typeface.BOLD);
        textView4.setPadding(20, 20, 20, 20);
        tableRow.addView(textView4);
        textView5=new TextView(getApplicationContext());
        textView5.setText("Email");
        textView5.setTextColor(Color.RED);
        textView5.setTypeface(null, Typeface.BOLD);
        textView5.setPadding(20, 20, 20, 20);
        tableRow.addView(textView5);
        tableLayout.addView(tableRow);
        for (Integer j = 0; j < count; j++)
        {
            tableRow = new TableRow(getApplicationContext());
            textView1 = new TextView(getApplicationContext());
            textView1.setText(c.getString(c.getColumnIndex("fname")));
            textView2 = new TextView(getApplicationContext());
            textView2.setText(c.getString(c.getColumnIndex("lname")));
            textView3 = new TextView(getApplicationContext());
            textView3.setText(c.getString(c.getColumnIndex("email")));
            textView1.setPadding(20, 20, 20, 20);
            textView2.setPadding(20, 20, 20, 20);
            textView3.setPadding(20, 20, 20, 20);
            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableLayout.addView(tableRow);
            c.moveToNext() ;
        }
        setContentView(tableLayout);
        db.close();
    }
    public void close(View view)
    {
        System.exit(0);
    }
}
