package edu.mobile.digital.coordinatorlayout.model.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

public final class ImageAnimationUtils {

    /**
     * Animates image resource with scale animation
     *
     * @param imageView View to change the image
     * @param from      From resource
     * @param to        To resource
     * @param duration  Duration > 0
     */
    public static void changeImageViewResource(@NonNull final ImageView imageView,
                                               @DrawableRes final int from,
                                               @DrawableRes final int to,
                                               final long duration) {
        imageView.setImageResource(from);
        imageView.animate().setDuration(duration / 2).scaleY(0f).scaleX(0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setImageResource(to);
                imageView.animate().setDuration(duration / 2).scaleY(1f).scaleX(1f);
            }
        });
    }

    /**
     * Animates show/hide image view with scale animation
     *
     * @param imageView View to change the image
     * @param show      True for show and false for hide
     * @param duration  Duration > 0
     */
    public static void scaleImage(@NonNull final ImageView imageView, final boolean show, final long duration) {
        if (!imageView.isAttachedToWindow()) return;
        imageView.setScaleX(show ? 0f : 1f);
        imageView.setScaleY(show ? 0f : 1f);
        imageView.setVisibility(View.VISIBLE);
        imageView.animate().setDuration(duration).scaleY(show ? 1f : 0f).scaleX(show ? 1f : 0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    private ImageAnimationUtils() {
        // No instances!
    }
}
