package team2.library.dbtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.view.TableView;
import team2.library.dbtest1.util.DBOperator;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Button;
import android.view.View.OnClickListener;


public class AnuragDesaiActivity extends AppCompatActivity implements OnClickListener
{
    Button checkoutBtn, queryBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuragdesai_layout);
        //copy database file


        checkoutBtn = (Button) this.findViewById(R.id.goCheckOut_btn);
        checkoutBtn.setOnClickListener(this);
        queryBtn = (Button) this.findViewById(R.id.goDoQuery_btn);
        queryBtn.setOnClickListener(this);

        try {
            DBOperator.copyDB(getBaseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.goCheckOut_btn) {
           /* Intent intent = new Intent(this, CheckoutActivity.class);
            this.startActivity(intent);*/
        } else if (id == R.id.goDoQuery_btn) {
            Intent intent = new Intent(this, QueryActivity.class);
            this.startActivity(intent);
        }
    }
}
