package edu.mobile.digital.coordinatorlayout.model.animation;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateDecelerateInterpolator;

public final class TextAnimationUtils {

    @NonNull
    public static ValueAnimator valueChangeAnimator(final float fromValue,
													final float toValue,
													@NonNull final ValueAnimator.AnimatorUpdateListener updateListener) {
        return valueChangeAnimator(fromValue, toValue, 200L, updateListener);
    }

    @NonNull
    public static ValueAnimator valueChangeAnimator(final float fromValue,
													final float toValue,
													final long duration,
													@NonNull final ValueAnimator.AnimatorUpdateListener updateListener) {
        final ValueAnimator progressTextAnimator = ValueAnimator.ofFloat(fromValue, toValue);
        progressTextAnimator.setDuration(duration);
        progressTextAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressTextAnimator.addUpdateListener(updateListener);
        return progressTextAnimator;
    }

    private TextAnimationUtils() {
        // No instances!
    }
}
