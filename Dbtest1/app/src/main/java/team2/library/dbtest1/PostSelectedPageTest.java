package team2.library.dbtest1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

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
        listView.setOnItemClickListener(new ItemClickListener());
        post_title1 =(TextView) this.findViewById(R.id.post_title1);
        post_desc1 =(TextView) this.findViewById(R.id.post_desc1);



        String value [] = new String[1];
        value [0] = ShowBuyListActivity.postId;

        //Get Post Details using the PostId from ShowBuyList
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.getpostdetails, value);
        StringArray stringArray = new StringArray();
        String ars[][]= stringArray.toStr(cursor);
        String post_title = ars [0][1];
        String post_desc = ars [0][2];
        post_title1.setText(post_title);
        post_desc1.setText(post_desc);




        //Get Item Details and Display them
        Cursor cursor2 = DBOperator.getInstance().execQuery(SQLCommand.getitemdetails, value);
        String[] from = new String[]{"item_name","item_desc","item_price"};
        int[] to = new int[]{R.id.item_name, R.id.item_desc,R.id.item_price};
        // bind the data to list
        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                getApplicationContext(), R.layout.major_list, cursor2,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String item_id = cursor.getString(0);


            /*int count1,count2;
            String [] value = new String[1];
            value[0] = item_id;

            String getwdid= SQLCommand.getwdid;
            Cursor cursor3 = DBOperator.getInstance().execQuery(getwdid);
            StringArray stringArray3 = new StringArray();
            String ars[][]= stringArray3.toStr(cursor3);
            count1=Integer.parseInt(ars[0][0]);
            count1=count1+1;

            System.out.println(count1);*/
            String value [] = new String[1];
            value [0] = ShowBuyListActivity.postId;
            //Update or Delete Button Condition
            Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getuserid,value);
            StringArray stringArray1 = new StringArray();
            String ars1[][]= stringArray1.toStr(cursor1);
            String post_user_id = ars1 [0][0];
            System.out.println("Queried User ID = "+post_user_id);
            System.out.println("Logged In User ID = "+LoginActivity.user_id);
            if (post_user_id.equals(LoginActivity.user_id))
            {
                UpdatePost(item_id);
            }
            else
            {
                AddtoWishlistDialog(item_id);
            }
           /* String getwishid= SQLCommand.getwishid;
            String userid[] = new String[1];
            userid[0] = LoginActivity.user_id;
            Cursor cursor2 = DBOperator.getInstance().execQuery(getwishid,userid);
            StringArray stringArray2 = new StringArray();
            String ars2[][]= stringArray2.toStr(cursor2);
            count2=Integer.parseInt(ars2[0][0]);*/


        }
    }

    private void UpdatePost(final String item_id) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog dialog;
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v2 = inflater.inflate(R.layout.update_item,null);

        alert.setView(v2);
        alert.setPositiveButton("Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

        alert.setNegativeButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value[] = new String[1];
                        value[0] = item_id;

                        Cursor cursor1 = DBOperator.getInstance().execQuery(SQLCommand.getitemid2, value);
                        DBOperator.getInstance();
                        //Toast.makeText(PostSelectedPageTest.this, item_id+"has been deleted", Toast.LENGTH_SHORT).show();
                    }

                });
        dialog = alert.create();
        dialog.show();
    }

    private void AddtoWishlistDialog(final String id) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog dialog;
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v1 = inflater.inflate(R.layout.add_to_wishlist,null);

        alert.setView(v1);
        alert.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String getwishId = SQLCommand.getwishid;
                        String userid[] = new String[1];
                        userid[0] = LoginActivity.user_id;
                        Cursor cursor = DBOperator.getInstance().execQuery(getwishId, userid);
                        StringArray stringArray = new StringArray();
                        String ars[][] = stringArray.toStr(cursor);
                        String wishId = Integer.toString(Integer.parseInt(ars[0][0]));

                        int count1;
                        String getwdid = SQLCommand.getwdid;
                        Cursor cursor1 = DBOperator.getInstance().execQuery(getwdid, null);
                        StringArray stringArray1 = new StringArray();
                        String ars1[][] = stringArray1.toStr(cursor1);
                        count1 = Integer.parseInt(ars1[0][0]);
                        count1 = count1 + 1;

                        String sql = SQLCommand.addtowishlist;
                        String[] value = new String[3];
                        value[0] = Integer.toString(count1);
                        value[1] = wishId;
                        value[2] = id;
                        DBOperator.getInstance().execSQL(sql, value);



                        Toast.makeText(PostSelectedPageTest.this, id + " has been added to Your Wishlist", Toast.LENGTH_SHORT).show();
                    }
                });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        dialog = alert.create();
        dialog.show();

    }




}
