package team2.library.dbtest1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 3/12/2016.
 */
public class PostSelectedPageTest extends AppCompatActivity {

    private ListView listView;
    private TextView post_title1,post_desc1;
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_click_page);

        listView = (ListView) this.findViewById(R.id.post_details);
        post_title1 =(TextView) this.findViewById(R.id.post_title1);
        post_desc1 =(TextView) this.findViewById(R.id.post_desc1);

        String value [] = new String[1];
        value [0] = ShowBuyListActivity.postId;
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getpostdetails1, value);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String post_title = ars [0][1];
        String post_desc = ars [0][2];
        post_title1.setText(post_title);
        post_desc1.setText(post_desc);



        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails, value);
        String[] from = new String[]{"item_name","item_desc","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_desc,R.id.item_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.major_list, cursor1,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

    }
}
