package com.example.textimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.SENSOR_SERVICE;

public class CustomView extends View implements SensorEventListener {

    SensorManager sensorManager = null;
    Sensor accelerometer;
    public static  int x,y;
    CustomView mCustomView = null;
    TextView xData, yData;
    ImageView imageView;
    Bitmap bitmap, original, mask, result;
    String text = "COSMOS";
    Paint paint;

    public CustomView(Context context) {
        super(context);

        xData = (TextView) findViewById(R.id.x);
        yData = (TextView) findViewById(R.id.y);

        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        imageView = findViewById(R.id.image);
        bitmap = textAsBitmap(text, 1000, Color.BLACK);
        imageView.setImageBitmap(bitmap);

        original = BitmapFactory.decodeResource(getResources(), R.drawable.cosmos);
        mask = bitmap;
        result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));


        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Reckoner_Bold.ttf");
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
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas = new Canvas(result);
        canvas.drawBitmap(original, x, y, null);
        canvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
      //canvas.drawPath(path, paint1);
        imageView.setImageBitmap(result);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        sensorManager.unregisterListener(this);

    }


}


