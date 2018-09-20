package edu.mobile.digital.coordinatorlayout.model.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;

public final class RevealAnimationUtils {

    /**
     * Animation to handle the method revealView
     *
     * @see RevealAnimationUtils#revealView
     */
    private Animator revealAnimator;

    public Animator revealView(@NonNull final View view, final boolean show) {
        return revealView(view, show, 400);
    }

    public Animator revealView(@NonNull final View view, final boolean show, final long duration) {
        final int finalRadius = Math.max(view.getWidth(), view.getHeight());
        final int centerX = (int) view.getX() + view.getWidth() / 2;
        final int centerY = view.getHeight() / 2;

        if (revealAnimator != null && revealAnimator.isRunning())
            revealAnimator.cancel();

        view.setVisibility(View.VISIBLE);
        revealAnimator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, show ? 0 : finalRadius, show ? finalRadius : 0);
        revealAnimator.setDuration(duration);
        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(@NonNull final Animator animation) {
                view.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
        return revealAnimator;
    }
}
