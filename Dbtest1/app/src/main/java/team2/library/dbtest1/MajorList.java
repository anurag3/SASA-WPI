package team2.library.dbtest1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 04/12/2015.
 */
public class MajorList extends AppCompatActivity implements RecyclerViewClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView title;
    private TextView sellerName;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //Toast.makeText(this,"Click on an item to add to Wishlist" , Toast.LENGTH_SHORT).show();
        title =(TextView) this.findViewById(R.id.title);
        desc=(TextView) this.findViewById(R.id.desc);

        String sql1= "SELECT post_title,post_desc FROM POST";
        Cursor cursor1 = DBOperator.getInstance().execQuery(sql1);
        StringArray stringArray1 = new StringArray();
        String ars[][]= stringArray1.toStr(cursor1);
        title.setText(ars[0][0]);
        desc.setText(ars[0][1]);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String value[]=new String[1];
        value[0]=ShowlistActivity.testId;


        String sql = SQLCommand.QUERY_display_items;

        System.out.println(sql);
        Cursor cursor = DBOperator.getInstance().execQuery(sql,value);
        StringArray stringArray = new StringArray();
        String ars1[][]= stringArray.toStr(cursor);


        // mAdapter = new MyAdapter(ars);
        mAdapter = new MajorMyAdapter(getApplicationContext(), this,ars1);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        //System.out.println(position);
        /*
        Intent intent = new Intent(this, Student_detailsMajor.class);
        this.startActivity(intent);
    */

        Toast.makeText(MajorList.this,"Selected "+MajorMyAdapter.post_id , Toast.LENGTH_SHORT).show();


    }


}
