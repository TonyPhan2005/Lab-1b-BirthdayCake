package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView; // Using SurfaceView from the first two versions

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    // Combined all unique paint objects
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    // Paints added in the first version but not explicitly set up (balloonPaint, redPaint, greenPaint)
    // or added in the third version (textPaint)
    Paint balloonPaint = new Paint();
    Paint redPaint = new Paint();
    Paint greenPaint = new Paint();
    Paint textPaint = new Paint();


    /* These constants define the dimensions of the cake. */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    // Keeping the original 65.0f but noting the lab change in the comment
    public static final float candleWidth = 65.0f; //changed to 60 for lab
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;


    private CakeModel cakeModel; // Instance variable: Type CakeModel

    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cakeModel = new CakeModel();

        // This is essential for SurfaceView if drawing outside of a dedicated thread
        setWillNotDraw(false);

        //Setup our palette - combining all paint initializations
        cakePaint.setColor(Color.BLACK);
        cakePaint.setStyle(Paint.Style.FILL);

        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);

        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);

        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);

        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);

        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);

        // Setup for textPaint from the third version
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.RIGHT);

        setBackgroundColor(Color.WHITE);  //better than black default

    }

    public CakeModel getCakeModel() // Getter Method: Cake Model
    {
        return this.cakeModel;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    // Method signature made private in the third version, kept public for consistency with the first two.
    public void drawCandle(Canvas canvas, float left, float bottom) {

        canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

        //draw the outer flame
        float flameCenterX = left + candleWidth/2;
        // Common calculation for flameCenterY
        float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius/3 + outerFlameRadius/3;


        // Checkpoint 2: Only draw flames if lit
        if (cakeModel.candlesLit)
        {
            canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);
            canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
        }


        //draw the wick
        float wickLeft = left + candleWidth/2 - wickWidth/2;
        float wickTop = bottom - wickHeight - candleHeight;
        canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

    }

    /**
     * onDraw is like "paint" in a regular Java program.
     * This method will draw a birthday cake
     */
    @Override
    // Note: SurfaceView uses just 'onDraw(Canvas)', while View uses 'protected void onDraw(Canvas)'
    public void onDraw(Canvas canvas)
    {
        // Call super.onDraw(canvas) if inheriting from View, which is not strictly necessary for SurfaceView
        // super.onDraw(canvas);

        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        // Draw cake layers
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

        // Draw candles (Checkpoint 3-4 logic)
        if (cakeModel.candlesOrNot)
        {
            float spacing = cakeWidth / (cakeModel.candlesAmount + 1);

            for (int c = 0; c < cakeModel.candlesAmount; c++)
            {
                float x = cakeLeft + (c+1) * spacing;
                drawCandle(canvas, x - candleWidth/2, cakeTop);
            }
        }

        // Draw touch coordinates (from the third version)
        if (cakeModel.touchX >= 0 && cakeModel.touchY >= 0) {
            canvas.drawText(
                    "Touch: (" + (int) cakeModel.touchX + ", " + (int) cakeModel.touchY + ")",
                    getWidth() - 20,
                    getHeight() - 20 - textPaint.getTextSize(),
                    textPaint
            );
        }


    }//onDraw

}//class CakeView