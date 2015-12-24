package team2.library.dbtest1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team2.library.dbtest1.util.DBOperator;


/**
 * Created by Anurag on 11/21/2015.
 */
public class buy extends AppCompatActivity {


    private ListView listView;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buypage);

        //copy database file
        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        listView = (ListView) this.findViewById(R.id.buy_list);
        //listView.setOnItemClickListener(new ItemClickListener());

        // get the sql string delivered from the mainpage
        Intent intent = this.getIntent();
        String sql = intent.getStringExtra("sql");
        // execute the sql
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        // bind the data to list
        for (cursor.moveToFirst(); cursor.isAfterLast() == false; cursor.moveToNext()) {
            System.out.println(cursor.getString(0));
        }

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.listitem_ibuy, cursor,
                new String[] { "post_first_name", "st_title", "post_desc"}, new int[]
                {R.id.st_first_name,R.id.post_title, R.id.post_desc },
                SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

    }

}


