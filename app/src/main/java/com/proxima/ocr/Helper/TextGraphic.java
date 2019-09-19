package com.proxima.ocr.Helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class TextGraphic extends GraphicOverlay.Graphic  {
    private int RECT_COLOR = Color.BLUE;
    private static final float TEXT_SIZE=54.0F;
    private float STROKE_WIDTH = 4.0f;
    private Paint rectPaint,textPaint;
    GraphicOverlay graphicOverlay;
    private final FirebaseVisionText.Element text;


    public TextGraphic(GraphicOverlay graphicOverlay, FirebaseVisionText.Element text) {
        super(graphicOverlay);
        this.graphicOverlay = graphicOverlay;
        this.text = text;
        rectPaint = new Paint();
        rectPaint.setColor(RECT_COLOR);
       rectPaint.setStyle(Paint.Style.STROKE);
       rectPaint.setStrokeWidth(STROKE_WIDTH);
        textPaint = new Paint();
        textPaint.setColor(RECT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);
       postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        if (text == null)
        {
            throw new IllegalStateException("Attempt to draw a null text");
        }
        RectF rectF = new RectF(text.getBoundingBox());
        rectF.left = translateX(rectF.left);
        rectF.right = translateX(rectF.right);
        rectF.top = translateY(rectF.top);
        rectF.bottom = translateX(rectF.bottom);
        canvas.drawRect(rectF,rectPaint);
        canvas.drawText(text.getText(),rectF.left,rectF.bottom,textPaint);

    }
}
