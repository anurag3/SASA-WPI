package team2.library.dbtest1.chart;

/**
 * Created by Anurag on 12/7/2015.
 */
import android.animation.Animator;
import android.view.animation.Interpolator;

public interface HoloGraphAnimate {
    final int ANIMATE_NORMAL = 0;
    final int ANIMATE_INSERT = 1;
    final int ANIMATE_DELETE = 2;
    int getDuration();
    void setDuration(int duration);

    Interpolator getInterpolator();
    void setInterpolator(Interpolator interpolator);

    boolean isAnimating();
    boolean cancelAnimating();
    void animateToGoalValues();
    void setAnimationListener(Animator.AnimatorListener animationListener);
}

