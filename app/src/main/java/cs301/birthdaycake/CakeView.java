package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* Paints */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint balloonPaint = new Paint();
    Paint redPaint = new Paint();
    Paint greenPaint = new Paint();

    /* Cake dimensions */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 65.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cakeModel; // Instance variable: CakeModel

    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cakeModel = new CakeModel();

        setWillNotDraw(false); // essential for onDraw

        // Setup paints
        cakePaint.setColor(Color.BLACK);
        cakePaint.setStyle(Paint.Style.FILL);

        frostingPaint.setColor(0xFFFFFACD); // pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);

        candlePaint.setColor(0xFF32CD32); // lime green
        candlePaint.setStyle(Paint.Style.FILL);

        outerFlamePaint.setColor(0xFFFFD700); // gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);

        innerFlamePaint.setColor(0xFFFFA500); // orange
        innerFlamePaint.setStyle(Paint.Style.FILL);

        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        balloonPaint.setARGB(255, 66, 135, 245); // light blue
        balloonPaint.setStyle(Paint.Style.FILL);

        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.FILL);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);
    }

    public CakeModel getCakeModel() {
        return this.cakeModel;
    }

    public void drawBalloon(Canvas canvas, float x, float y) {
        canvas.drawCircle(x, y, 100, balloonPaint);
        canvas.drawCircle(x - 40, y - 40, 10, frostingPaint);
        canvas.drawRect(x + 50, y + 50, x + 55, y + 255, wickPaint);
    }

    public void drawCandle(Canvas canvas, float left, float bottom) {
        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        float flameCenterX = left + candleWidth / 2;
        float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
        flameCenterY += outerFlameRadius / 3;

        if (cakeModel.candlesLit) {
            canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);
            canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
        }

        float wickLeft = left + candleWidth / 2 - wickWidth / 2;
        float wickTop = bottom - wickHeight - candleHeight;
        canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
    }

    public void drawCheckerboard(Canvas canvas, float x, float y) {
        canvas.drawRect(x, y, x + 100, y + 100, greenPaint); // Bottom Right
        canvas.drawRect(x, y, x - 100, y + 100, redPaint);   // Bottom Left
        canvas.drawRect(x, y, x - 100, y - 100, greenPaint); // Top Left
        canvas.drawRect(x, y, x + 100, y - 100, redPaint);   // Top Right
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Draw balloon at last touched location
        if (cakeModel.balloonX >= 0) {
            drawBalloon(canvas, cakeModel.balloonX, cakeModel.balloonY);
        }

        // Cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        // Candles
        if (cakeModel.candlesOrNot) {
            float spacing = cakeWidth / (cakeModel.candlesAmount + 1);
            for (int c = 0; c < cakeModel.candlesAmount; c++) {
                float x = cakeLeft + (c + 1) * spacing;
                drawCandle(canvas, x - candleWidth / 2, cakeTop);
            }
        }

        // Checkerboard on touch
        if (cakeModel.checkerX >= 0) {
            drawCheckerboard(canvas, cakeModel.checkerX, cakeModel.checkerY);
        }

        // Draw touch coordinates in RED, bottom-right corner (simplified)
        if (cakeModel.touchX >= 0 && cakeModel.touchY >= 0) {
            Paint textPaint = new Paint();
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(60);
            String coords = "Touch: (" + cakeModel.touchX + ", " + cakeModel.touchY + ")";
            canvas.drawText(coords, canvas.getWidth() - 600, canvas.getHeight() - 50, textPaint);
        }
    }
}
