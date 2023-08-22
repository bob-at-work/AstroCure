package com.astrocure.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.View;

import java.util.List;

public class DrawDotedLine extends View {
    Paint paintLine = new Paint();
    Paint paintCircle = new Paint();
    int height;
    int width;

    List<Integer> heights;

    public DrawDotedLine(Context context, int height, int width, List<Integer> heights) {
        super(context);
        this.height = height;
        this.width = width;
        this.heights = heights;
    }
    public DrawDotedLine(Context context) {
        super(context);
    }

    public DrawDotedLine(Context context, int width, int height) {
        super(context);
        this.height = height;
        this.width = width;
    }

    @Override
    public void onDraw(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        int actualHeight = (h - heights.get(heights.size() - 1))+55;

        Path pathLine = new Path();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(3);
        paintLine.setStyle(Paint.Style.STROKE);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.WHITE);
        pathLine.moveTo(w / 2, 0);
        pathLine.lineTo(w / 2,actualHeight);
        pathLine.lineTo(w,actualHeight);
//        pathLine.moveTo(w/2,h-heights.get(heights.size()-2)+20);
//        pathLine.lineTo(w,h-heights.get(heights.size()-2)+20);
//        for (int i = heights.size(); i > 0; i--) {
//                pathLine.moveTo(w/2,h-heights.get(i-1)+20);
//                pathLine.lineTo(w,h-heights.get(i-1)+20);

//            pathLine.moveTo(w/2,(h - heights.get(i - 2)) + 20);
//            pathLine.lineTo((h - heights.get(i - 2)) + 20,w);
//            canvas.drawCircle(w / 2, (h - heights.get(i - 1)) + 20, 8, paintCircle);
//            Log.i("TAG", "onDraw: "+h);
//            h = h - heights.get(i - 1);
//        }
        for (int i = 1; i <= actualHeight; i += 40) {
            canvas.drawCircle(w / 2, i, 8, paintCircle);
        }
        canvas.drawCircle((w / 2) + 30, actualHeight, 8, paintCircle);
        canvas.drawCircle((w / 2) + 60, actualHeight, 8, paintCircle);
        canvas.drawPath(pathLine, paintLine);
    }
}
