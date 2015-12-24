package team2.library.dbtest1;

/**
 * Created by Chandan on 12/6/2015.
 */
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import team2.library.dbtest1.constant.SQLCommand;
import team2.library.dbtest1.util.DBOperator;


public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Cursor cursor = DBOperator.getInstance().execQuery(SQLCommand.QUERY_1, null);
        String[] from = new String[]{"st_first_name","st_last_name","post_title","post_desc"};
        int[] to = new int[]{R.id.st_first_name, R.id.st_last_name,R.id.post_title, R.id.post_desc};
        // bind the data to list
        /*final SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this.getContext(), R.layout.listitem_ibuy, cursor,
                from, to, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);
        listView.setAdapter(adapter);*/
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}
