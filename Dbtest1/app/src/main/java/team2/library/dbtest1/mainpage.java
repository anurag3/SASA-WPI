package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.dbtest.TodoListActivity;
import team2.library.dbtest1.util.DBOperator;
import team2.library.dbtest1.view.ChartGenerator;

/**
 * Created by Anurag on 11/20/2015.
 */
public class mainpage extends AppCompatActivity {


    //SQLiteDatabse mydatabase = openOrCreateDatabase("your database name",MODE_PRIVATE,null);
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

        }

    public void onClick(View view)
    {

        String sql="";
        int id=view.getId();
        if (id==R.id.buy_button)
        {

            //sql = SQLCommand.QUERY_1;
            Intent intent = new Intent(getApplicationContext(), ShowlistActivity.class);
            //intent.putExtra("sql", sql);
            startActivity(intent);

        }
        if(id==R.id.sell_button)
        {
            Intent intent = new Intent(getApplicationContext(), sell.class);
            this.startActivity(intent);
        }
        if(id==R.id.hot_text)
        {
            Intent intent = new Intent(this, HotBuy.class);
            this.startActivity(intent);
        }
        if(id==R.id.new_text)
        {
            Intent intent = new Intent(this, NewBuy.class);
            this.startActivity(intent);
        }
        if(view.getId()==R.id.prof_button){
            Intent it = new Intent(this,Profile_page.class);
            startActivity(it);
        }
        if(view.getId()==R.id.logout_button){
            Intent intent = new Intent(this, login.class);
            this.startActivity(intent);
        }
        if(view.getId()==R.id.wish_button){
            Intent intent = new Intent(this, Wishlist.class);
            this.startActivity(intent);
        }
    }
}
