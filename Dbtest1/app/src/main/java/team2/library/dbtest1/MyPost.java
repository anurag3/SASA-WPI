package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 4/2/2016.
 */
public class MyPost extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_posts);

        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

        listView = (ListView) this.findViewById(R.id.my_post_list);
        listView.setOnItemClickListener(new ItemClickListener());

        String[] value= new String[1];
        value[0]= LoginActivity.user_id;
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.mypostlist, value);
        String[] from = new String[]{"user_first_name","user_last_name","post_title","post_desc"};
        int[] to = new int[]{R.id.user_first_name, R.id.user_last_name,R.id.post_title, R.id.post_desc};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.buy_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String post_id = cursor.getString(0);
            String user_first_name = cursor.getString(1);
            String user_last_name = cursor.getString(2);
            String user_phone = cursor.getString(3);
            String post_title = cursor.getString(4);
            String post_desc = cursor.getString(5);

            System.out.println( "Post ID = " + post_id);
            Intent intent = new Intent(getApplicationContext(), PostSelectedPageTest.class);
            intent.putExtra("user_first_name", user_first_name);
            intent.putExtra("user_last_name", user_last_name);
            intent.putExtra("user_phone", user_phone);
            intent.putExtra("post_title", post_title);
            intent.putExtra("post_desc", post_desc);
            intent.putExtra("post_id",post_id);
            startActivity(intent);
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }
}
