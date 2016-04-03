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
 * Created by Anurag on 11/20/2015.
 */
public class MainActivity extends AppCompatActivity {


    private ListView listView;
    //SQLiteDatabse mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_test);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

        listView = (ListView) this.findViewById(R.id.event_listView);
        listView.setOnItemClickListener(new ItemClickListener());

        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.showeventlist, null);
        String[] from = new String[]{"event_title","event_desc"};
        int[] to = new int[]{R.id.event_title, R.id.event_desc};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.event_listview, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        }

    private class ItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String event_id = cursor.getString(0);
            String event_title = cursor.getString(1);
            String event_desc = cursor.getString(2);

            Intent intent = new Intent(getApplicationContext(), EventRegistation.class);
            intent.putExtra("event_id",event_id);
            intent.putExtra("event_title",event_title);
            intent.putExtra("event_desc", event_desc);
            startActivity(intent);
        }
    }
    public void onClick(View view)
    {
        //String sql="";
        int id=view.getId();
        if (id==R.id.buy_button)
        {

            //sql = SQLCommand.QUERY_1;
            Intent intent = new Intent(getApplicationContext(), ShowBuyListActivity.class);
            //intent.putExtra("sql", sql);
            startActivity(intent);

        }
        if(id==R.id.sell_button)
        {
            Intent intent = new Intent(getApplicationContext(), SellPage.class);
            this.startActivity(intent);
        }
        if(id==R.id.hot_text)
        {
            Intent intent = new Intent(this, HotDeals.class);
            this.startActivity(intent);
        }
        if(id==R.id.new_text)
        {
            Intent intent = new Intent(this, NewDeals.class);
            this.startActivity(intent);
        }
        if(view.getId()==R.id.wish_button){
            Intent intent = new Intent(this, WishList.class);
            this.startActivity(intent);
        }
        if(view.getId()==R.id.my_post){
            Intent intent = new Intent(this, MyPost.class);
            this.startActivity(intent);
        }
        if(view.getId()==R.id.prof_button){
            Intent it = new Intent(this,ProfilePage.class);
            startActivity(it);
        }
        if(view.getId()==R.id.logout_button){
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        }

    }


    public void onBackPressed() {

    }

  /*  public void onBackPressed() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }*/




}
