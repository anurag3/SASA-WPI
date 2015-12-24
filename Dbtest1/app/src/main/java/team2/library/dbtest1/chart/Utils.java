package team2.library.dbtest1.chart;

/**
 * Created by Anurag on 12/7/2015.
 */
import android.graphics.Color;
public class Utils {
    public static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;
        return Color.HSVToColor(hsv);
    }
}

