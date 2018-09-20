/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.mobile.digital.coordinatorlayout.model.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.mobile.digital.coordinatorlayout.model.utils.IntProperty;


/**
 * This transition tracks changes during scene changes to the
 * {@link View#setBackground(android.graphics.drawable.Drawable) background}
 * property of its target views (when the background is a
 * {@link ColorDrawable}, as well as the
 * {@link TextView#setTextColor(android.content.res.ColorStateList)
 * color} of the text for target TextViews. If the color changes between
 * scenes, the color change is animated.
 */

public class RecolorTransition extends Transition {

    private static final String PROPNAME_BACKGROUND = "android:recolor:background";
    private static final String PROPNAME_TEXT_COLOR = "android:recolor:textColor";

    private static final Property<TextView, Integer> TEXTVIEW_TEXT_COLOR;
    private static final Property<ColorDrawable, Integer> COLORDRAWABLE_COLOR;

    static {
        TEXTVIEW_TEXT_COLOR = new IntProperty<TextView>() {

            @Override
            public void setValue(TextView object, int value) {
                object.setTextColor(value);
            }

            @Override
            public Integer get(TextView object) {
                return 0;
            }

        }.optimize();
        COLORDRAWABLE_COLOR = new IntProperty<ColorDrawable>() {
            @Override
            public void setValue(ColorDrawable object, int value) {
                object.setColor(value);
            }

            @Override
            public Integer get(ColorDrawable object) {
                return object.getColor();
            }
        }.optimize();
    }

    public RecolorTransition() {
    }

    public RecolorTransition(Context context, AttributeSet attrs) {
        super();
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_BACKGROUND, transitionValues.view.getBackground());
        if (transitionValues.view instanceof TextView) {
            transitionValues.values.put(PROPNAME_TEXT_COLOR,
                    ((TextView) transitionValues.view).getCurrentTextColor());
        }
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
								   TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        final View view = endValues.view;
        Drawable startBackground = (Drawable) startValues.values.get(PROPNAME_BACKGROUND);
        Drawable endBackground = (Drawable) endValues.values.get(PROPNAME_BACKGROUND);
        ObjectAnimator bgAnimator = null;
        if (startBackground instanceof ColorDrawable && endBackground instanceof ColorDrawable) {
            ColorDrawable startColor = (ColorDrawable) startBackground;
            ColorDrawable endColor = (ColorDrawable) endBackground;
            if (startColor.getColor() != endColor.getColor()) {
                final int finalColor = endColor.getColor();
                endColor.setColor(startColor.getColor());
                bgAnimator = ObjectAnimator.ofInt(endColor, COLORDRAWABLE_COLOR, startColor.getColor(), finalColor);
                bgAnimator.setEvaluator(new ArgbEvaluator());
            }
        }
        ObjectAnimator textColorAnimator = null;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            int start = (Integer) startValues.values.get(PROPNAME_TEXT_COLOR);
            int end = (Integer) endValues.values.get(PROPNAME_TEXT_COLOR);
            if (start != end) {
                textView.setTextColor(end);
                textColorAnimator = ObjectAnimator.ofInt(textView, TEXTVIEW_TEXT_COLOR, start, end);
                textColorAnimator.setEvaluator(new ArgbEvaluator());
            }
        }
        return mergeAnimators(bgAnimator, textColorAnimator);
    }

    private static Animator mergeAnimators(Animator animator1, Animator animator2) {
        if (animator1 == null) {
            return animator2;
        } else if (animator2 == null) {
            return animator1;
        } else {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animator1, animator2);
            return animatorSet;
        }
    }
}
