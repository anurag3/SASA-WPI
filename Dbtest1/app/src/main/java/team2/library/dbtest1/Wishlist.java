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
public class WishList extends AppCompatActivity {

    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);

        listView = (ListView) this.findViewById(R.id.wish_listView);

        String [] value= new String[1];
        value[0]=LoginActivity.user_id;
        String sql= SQLCommand.getwishlist;
        Cursor cursor = DBOperator.getInstance().execQuery(sql,value);

        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String wish_id = ars[0][0];

        String [] value1= new String[1];
        value1[0]= wish_id;

        String sql1= SQLCommand.showwishlist;
        Cursor cursor1 = DBOperator.getInstance().execQuery(sql1,value1);
        String[] from = new String[]{"item_name","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_price};
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.wishlist_layout, cursor1,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);

    }
}
