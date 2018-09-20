package edu.mobile.digital.coordinatorlayout.model.utils;

import android.annotation.SuppressLint;
import android.util.Property;

public abstract class IntProperty<T> extends Property<T, Integer> {

    public IntProperty() {
        super(Integer.class, null);
    }

    public abstract void setValue(T object, int value);

    @Override
    public final void set(T object, Integer value) {
        setValue(object, value);
    }

    /**
     * Just default realisation. Some of properties can have no getter. Override for real getter
     */
    @Override
    public Integer get(T object) {
        return null;
    }

    @SuppressLint("NewApi")
    public Property<T, Integer> optimize() {
        return new android.util.IntProperty<T>(null) {
            @Override
            public void setValue(T object, int value) {
                IntProperty.this.setValue(object, value);
            }

            @Override
            public Integer get(T object) {
                return IntProperty.this.get(object);
            }
        };
    }

} 