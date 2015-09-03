package org.peacepassion.layout;

import android.graphics.drawable.Drawable;

/**
 * Created by peacepassion on 15/8/23.
 */
public class DataHolder {

    Drawable back;
    Drawable front;
    String title;
    int titleTargetColor;

    public DataHolder(Drawable back, Drawable front, String title, int titleTargetColor) {
        this.back = back;
        this.front = front;
        this.title = title;
        this.titleTargetColor = titleTargetColor;
    }
}
