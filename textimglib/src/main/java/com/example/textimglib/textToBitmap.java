package com.example.textimglib;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

public class textToBitmap {

    private Context context;

    public void textToBitmap(Context context){
        this.context = context;

    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Reckoner_Bold.ttf");
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(typeface);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 2.5f); // round
        int height = (int) (baseline + paint.measureText(text) + 2.5f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }


}
