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
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager = null;
    Sensor accelerometer;
    public static  int x,y;
    CustomView mCustomView = null;
    TextView xData, yData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xData = (TextView) findViewById(R.id.x);
        yData = (TextView) findViewById(R.id.y);



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



        ImageView imageView = findViewById(R.id.image);
        String text = "COSMOS";
        Bitmap bitmap = textAsBitmap(text, 1000, Color.BLACK);

        imageView.setImageBitmap(bitmap);

        Bitmap original = BitmapFactory.decodeResource(getResources(), R.drawable.cosmos);
        Bitmap mask = bitmap;
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Path path = new Path();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        path.moveTo(0,0);
        path.lineTo( 1,1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, x, y, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        mCanvas.drawPath(path, paint1);
        imageView.setImageBitmap(result);

       /* path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
        PathInterpolator pathInterpolator = new PathInterpolator(path);

        ValueAnimator animation = ObjectAnimator.ofFloat(R.layout.activity_main, 1, 1, 1000f);
        animation.setInterpolator(pathInterpolator);
        animation.start();*/







    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Reckoner_Bold.ttf");
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

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = (int) Math.pow(event.values[1], 2);
            y = (int) Math.pow(event.values[2], 2);

            String xVal = Integer.toString(x);
            String yVal = Integer.toString(y);
            xData.setText(xVal);
            yData.setText(yVal);
        }
        else{
            //do nothing
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);




    }

    @Override
    protected void onStop()
    {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }



}
