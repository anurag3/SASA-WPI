package team2.library.dbtest1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import team2.library.dbtest1.chart.PieGraph;
import team2.library.dbtest1.chart.PieSlice;

/**
 * Created by Anurag on 12/9/2015.
 */
public class Chart_test extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_test);


        PieGraph pg = (PieGraph)findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#99CC00"));
        slice.setValue(2);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(3);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#AA66CC"));
        slice.setValue(8);
        pg.addSlice(slice);
    }
}
