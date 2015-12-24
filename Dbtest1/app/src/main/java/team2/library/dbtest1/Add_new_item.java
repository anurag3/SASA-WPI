package team2.library.dbtest1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;

/**
 * Created by Anurag on 12/8/2015.
 */
public class Add_new_item extends AppCompatActivity implements View.OnClickListener {
    private EditText item_name;
    private EditText item_qoh;
    private EditText item_price;
    private EditText item_desc;
    private Button post;
    private Button back;
    private Spinner querySpinner;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);
        item_name = (EditText) this.findViewById(R.id.item_name);
        item_qoh = (EditText) this.findViewById(R.id.item_qoh);
        item_price = (EditText) this.findViewById(R.id.item_price);
        item_desc = (EditText) this.findViewById(R.id.item_desc);

        post = (Button) this.findViewById(R.id.post);
        post.setOnClickListener(this);

        back = (Button) this.findViewById(R.id.back);
        back.setOnClickListener(this);

        querySpinner = (Spinner) this.findViewById(R.id.spinner_item_cat);

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        String cat_id = null;
        if (id == R.id.post)
        {

            item_name.setError(null);
            item_price.setError(null);
            item_desc.setError(null);
            //et2.setError(null);
            item_qoh.setError(null);
            String name = item_name.getText().toString().trim();
            String price = item_price.getText().toString().trim();
            String desc = item_desc.getText().toString().trim();
            String qoh = item_qoh.getText().toString().trim();

            View focusView = null;
            boolean cancel = false;
            if (TextUtils.isEmpty(name)) {
                item_name.setError("Item Name is Empty");
                focusView = item_name;
                cancel = true;
            } else if (TextUtils.isEmpty(qoh)) {
                item_qoh.setError("Item Quantity is Empty");
                focusView = item_qoh;
                cancel = true;
            } else if (TextUtils.isEmpty(price)) {
                item_price.setError("Item Price is Empty");
                focusView = item_price;
                cancel = true;
            } else if (TextUtils.isEmpty(desc)) {
                item_desc.setError("Item Description is Empty");
                focusView = item_desc;
                cancel = true;
            } else  if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {


                //show query result
                int pos = querySpinner.getSelectedItemPosition();
                if (pos == 0) {
                    //show all books
                    cat_id = "300";
                } else if (pos == 1) {
                    //list the call numbers of books with the title ‘Database Management’
                    cat_id = "301";
                } else if (pos == 2) {
                    cat_id = "302";
                } else if (pos == 3) {
                    cat_id = "303";
                }

                int item_id;
                String count = "select count(item_id) from item";
                Cursor cursor = DBOperator.getInstance().execQuery(count);
                StringArray stringArray = new StringArray();
                String ars[][] = stringArray.toStr(cursor);
                item_id = Integer.parseInt(ars[0][0]);
                item_id = item_id + 1;

                int post_id = sell.count1;

                int item_hit = 1;
                String value[] = new String[8];
                value[0] = Integer.toString(item_id);
                value[1] = item_name.getText().toString();
                value[2] = Integer.toString(post_id);
                value[3] = item_qoh.getText().toString();
                value[4] = item_price.getText().toString();
                value[5] = item_desc.getText().toString();
                value[6] = cat_id;
                value[7] = Integer.toString(item_hit);

                String sql = SQLCommand.itemInsert;
                System.out.println(sql);
                DBOperator.getInstance().execSQL(sql, value);

                Toast.makeText(getApplicationContext(), "Item Successfully Posted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, Add_new_item.class);
                this.startActivity(intent);

            }

            if (id == R.id.back)
            {
                Intent intent = new Intent(this, mainpage.class);
                this.startActivity(intent);
            }
        }
    }
}
