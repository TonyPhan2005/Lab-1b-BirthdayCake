package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CakeView extends View {

    private CakeModel cakeModel;

    private Paint cakePaint, frostingPaint, candlePaint, outerFlamePaint, innerFlamePaint, wickPaint, textPaint;

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

    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cakeModel = new CakeModel();

        // Paints
        cakePaint = new Paint();
        cakePaint.setColor(Color.BLACK);
        cakePaint.setStyle(Paint.Style.FILL);

        frostingPaint = new Paint();
        frostingPaint.setColor(0xFFFFFACD);
        frostingPaint.setStyle(Paint.Style.FILL);

        candlePaint = new Paint();
        candlePaint.setColor(0xFF32CD32);
        candlePaint.setStyle(Paint.Style.FILL);

        outerFlamePaint = new Paint();
        outerFlamePaint.setColor(0xFFFFD700);
        outerFlamePaint.setStyle(Paint.Style.FILL);

        innerFlamePaint = new Paint();
        innerFlamePaint.setColor(0xFFFFA500);
        innerFlamePaint.setStyle(Paint.Style.FILL);

        wickPaint = new Paint();
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.RIGHT);

        setBackgroundColor(Color.WHITE);
    }

    public CakeModel getCakeModel() {
        return cakeModel;
    }

    private void drawCandle(Canvas canvas, float left, float bottom) {
        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        float flameX = left + candleWidth / 2;
        float flameY = bottom - wickHeight - candleHeight - outerFlameRadius / 3 + outerFlameRadius / 3;

        if (cakeModel.candlesLit) {
            canvas.drawCircle(flameX, flameY, outerFlameRadius, outerFlamePaint);
            canvas.drawCircle(flameX, flameY, innerFlameRadius, innerFlamePaint);
        }

        float wickLeft = left + candleWidth / 2 - wickWidth / 2;
        float wickTop = bottom - wickHeight - candleHeight;
        canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        // Frosting and cake layers
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

        // Draw candles
        if (cakeModel.candlesOrNot) {
            float spacing = cakeWidth / (cakeModel.candlesAmount + 1);
            for (int i = 0; i < cakeModel.candlesAmount; i++) {
                float x = cakeLeft + (i + 1) * spacing;
                drawCandle(canvas, x - candleWidth / 2, cakeTop);
            }
        }

        // Draw touch coordinates
        if (cakeModel.touchX >= 0 && cakeModel.touchY >= 0) {
            canvas.drawText(
                    "Touch: (" + (int) cakeModel.touchX + ", " + (int) cakeModel.touchY + ")",
                    getWidth() - 20,
                    getHeight() - 20 - textPaint.getTextSize(),
                    textPaint
            );
        }
    }
}
