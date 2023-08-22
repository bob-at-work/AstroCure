package com.astrocure.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import java.util.List;

public class DrawLine extends View {
    Paint paintLine = new Paint();
    Paint paintCircle = new Paint();
    int height;
    int width;
    List<Integer> heights;

    public DrawLine(Context context, int height, int width, List<Integer> heights) {
        super(context);
        this.height = height;
        this.width = width;
        this.heights = heights;
    }

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

        int actualHeight = (h - heights.get(heights.size() - 1));
        Path pathLine = new Path();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(3);
        paintLine.setStyle(Paint.Style.STROKE);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.WHITE);
        pathLine.moveTo(w / 2, 0);
        pathLine.lineTo(w / 2, actualHeight + 20);

        for (int i = heights.size(); i > 0; i--) {
            canvas.drawCircle(w / 2, (h - heights.get(i - 1)) + 20, 8, paintCircle);
            h = h - heights.get(i - 1);
        }

        canvas.drawPath(pathLine, paintLine);
    }
}
