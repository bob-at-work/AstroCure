package com.astrocure.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DrawLine extends View {
    Paint paintLine = new Paint();
    Paint paintCircle = new Paint();
    int height;
    int width;

    public DrawLine(Context context) {
        super(context);
    }

    public DrawLine(Context context, int width, int height) {
        super(context);
        this.height = height;
        this.width = width;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        int endPoint = h - height + ((height / 2) / 2);
        Path pathLine = new Path();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(3);
        paintLine.setStyle(Paint.Style.STROKE);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.WHITE);
        pathLine.moveTo(width / 2, 0);
        pathLine.lineTo(width / 2, h - height + ((height / 2) / 2));
        pathLine.lineTo(width, (h - height) + ((height / 2) / 2));
        for (int i = 1; i <= endPoint; i += 40) {
//            canvas.drawCircle(width / 2, i, 8, paintCircle);
        }
//        canvas.drawCircle((width / 2) + 20, endPoint, 8, paintCircle);
//        canvas.drawCircle((width / 2) + 50, endPoint, 8, paintCircle);
        canvas.drawPath(pathLine, paintLine);
    }
}
