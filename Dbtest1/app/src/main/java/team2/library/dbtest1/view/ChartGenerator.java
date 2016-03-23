package team2.library.dbtest1.view;

/**
 * Created by Anurag on 11/11/2015.
 */

import team2.library.dbtest1.util.Pair;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;


public class ChartGenerator {
        private static double yMaxNum = 0.0;
        /*
         * A Render defining char styles Including title, text color, background
         * colors, etc.
         */
        private static XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        private static XYSeriesRenderer renderer = new XYSeriesRenderer();

        static {
            mRenderer.setChartTitleTextSize(30);
            mRenderer.setLabelsTextSize(15);
            mRenderer.setLegendTextSize(15);
            mRenderer.setZoomEnabled(false, false);
            mRenderer.setYLabelsAlign(Align.RIGHT);
            mRenderer.setShowGridY(true);
            mRenderer.setXLabelsColor(Color.RED);
            mRenderer.setYLabelsColor(0, Color.RED);
            mRenderer.setXLabels(0);
            String[] months = new String[] { "DB Text", "Used IP6", "Home Furniture", "Office Furniture", "Study Table",
                    "Spyder", "Beamer" };
            for (int i = 0; i < months.length; i++) {
                mRenderer.addXTextLabel(i + 1, months[i]);
            }
            mRenderer.setShowGridX(true);
            mRenderer.setXTitle("Items");
            mRenderer.setYTitle("Number of Hits");
            mRenderer.setAxisTitleTextSize(20);
            mRenderer.setMargins(new int[] { 80, 40, 60, 40 });
            mRenderer.setPanEnabled(false, false);
            mRenderer.setZoomButtonsVisible(false);
            mRenderer.setBarSpacing(1f);
            renderer.setColor(Color.BLUE);
            renderer.setDisplayChartValues(true);
            renderer.setChartValuesTextAlign(Align.RIGHT);
            renderer.setChartValuesTextSize(20f);

            mRenderer.addSeriesRenderer(renderer);
        }

        private static XYMultipleSeriesDataset createDataSet(List<Pair> pairList) {
            XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
            CategorySeries series = new CategorySeries("Number of Hits / Items");
            for (Pair pair : pairList) {
                if (pair.getNumber() > yMaxNum) {
                    yMaxNum = pair.getNumber();
                }
                series.add(pair.getMonth() + "", pair.getNumber());
            }
            mRenderer.setYAxisMax(yMaxNum + 1.0);
            mDataset.addSeries(series.toXYSeries());
            return mDataset;
        }

        /**
         * Get a bar chart
         *
         * @param context
         * @param pairList
         * @return
         */
        public static Intent getBarChart(Context context, String title,
                                         List<Pair> pairList) {
            mRenderer.setChartTitle(title);
            return ChartFactory.getBarChartIntent(context, createDataSet(pairList),
                    mRenderer, BarChart.Type.DEFAULT);
        }

        /**
         * Get a line chart
         *
         * @param context
         * @param pairList
         * @return
         */
        public static Intent getLineChart(Context context, String title,
                                          List<Pair> pairList) {
            mRenderer.setChartTitle(title);
            return ChartFactory.getLineChartIntent(context,
                    createDataSet(pairList), mRenderer);
        }

    }
