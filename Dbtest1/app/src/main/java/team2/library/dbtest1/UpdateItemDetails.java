package team2.library.dbtest1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;
/**
 * Created by viraj on 3/26/2016.
 */
public class UpdateItemDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText update_item_name;
    private EditText update_item_quantity;
    private EditText update_item_price;
    private EditText update_item_description;
    private Spinner update_item_category;
    private String temp_item_id;

    protected void onCreate(Bundle savedInstanceState)
    {
        //System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item_details);
        update_item_name = (EditText) this.findViewById(R.id.txt_update_item_name);
        update_item_quantity = (EditText) this.findViewById(R.id.txt_update_item_quantity);
        update_item_price = (EditText) this.findViewById(R.id.txt_update_item_price);
        update_item_description = (EditText) this.findViewById(R.id.txt_update_item_description);
        update_item_category = (Spinner) this.findViewById(R.id.spn_update_item_category);


        Intent intent = this.getIntent();
        String item_id = intent.getStringExtra("item_id");
        System.out.println("Item ID_Update: "+item_id);
        temp_item_id = item_id;
        String[] value= new String[1];
        value[0] = item_id;
        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails_updatePage, value);
        StringArray stringArray = new StringArray();
        String itemDetails[][] = stringArray.toStr(cursor1);
        int index = 0;
        for(int i=0; i<update_item_category.getCount(); i++)
        {
            if(update_item_category.getItemAtPosition(i).toString().equals(itemDetails[0][4]))
                index = i;
        }

        update_item_name.setText(itemDetails[0][0]);
        update_item_quantity.setText(itemDetails[0][1]);
        update_item_price.setText(itemDetails[0][2]);
        update_item_description.setText(itemDetails[0][3]);
        update_item_category.setSelection(index);
    }

    public void onClick(View view)
    {
        System.out.println("inside update item page");
        int id=view.getId();
        if (id==R.id.btn_home_update)
        {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        if(id==R.id.btn_update_post)
        {
            System.out.println("update selected");
            String[] value= new String[6];
            value[0] = update_item_name.getText().toString();
            value[1] = update_item_quantity.getText().toString();
            value[2] = update_item_price.getText().toString();
            value[3] = update_item_description.getText().toString();

            String[] tempCat = new String[1];
            tempCat[0]= update_item_category.getAdapter().toString();
            Cursor cursor1 = DBOperator.getInstance().execQuery("select cat_id from category where cat_name=?", tempCat);
            StringArray stringArray1 = new StringArray();

            String[][] category = stringArray1.toStr(cursor1);

            if(category[0][0]==null)
                System.out.println("category[][] is null");

            System.out.println("Category ID: "+category[0][0]);
            int c = Integer.parseInt(category[0][0]);
            value[4] = Integer.toString(c);
            value[5] = temp_item_id;
            Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.updateItemDetails, value);
            StringArray stringArray2 = new StringArray();
            String itemDetails[][] = stringArray2.toStr(cursor2);
        }

    }
}
