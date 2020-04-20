package com.example.textimage;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relativeLayout);
        CustomView myCustomView = new CustomView(this);
        relativeLayout.addView(myCustomView);

        /*Canvas mCanvas = new Canvas(result);
        Path path = new Path();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);     //done
    //    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    //    path.moveTo(0,0);
    //    path.lineTo( 1,1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));      //done
        mCanvas.drawBitmap(original, x, y, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        mCanvas.drawPath(path, paint1);
        imageView.setImageBitmap(result);*/

       /* path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
        PathInterpolator pathInterpolator = new PathInterpolator(path);

        ValueAnimator animation = ObjectAnimator.ofFloat(R.layout.activity_main, 1, 1, 1000f);
        animation.setInterpolator(pathInterpolator);
        animation.start();*/

    }

}
