package team2.library.dbtest1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 12/8/2015.
 */
public class Wishlist extends AppCompatActivity {

    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        listView = (ListView) this.findViewById(R.id.wish_listView);
        //String sql= "SELECT item_name, item_price FROM ITEM WHERE st_id="+login.st_id;
        String sql= "SELECT wish_id FROM WISHLIST WHERE st_id="+login.st_id;
        Cursor cursor = DBOperator.getInstance().execQuery(sql);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String w_id = ars[0][0];

        String sql1= "SELECT ITEM.item_id AS _id,item_name, item_price FROM ITEM,WISHLIST_DETAILS,WISHLIST WHERE WISHLIST_DETAILS.item_id=ITEM.item_id AND WISHLIST_DETAILS.wish_id=WISHLIST.wish_id AND WISHLIST.wish_id="+w_id;
        Cursor cursor1 = DBOperator.getInstance().execQuery(sql1);
        String[] from = new String[]{"item_name","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_price};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.wishlist_layout, cursor1,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

    }
}
