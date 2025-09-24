package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener{

    private CakeView view1;
    private CakeModel model1;
    public CakeController(CakeView c)
    {
        this.view1 = c;
        this.model1 = view1.getCakeModel();
    }

    public void onClick(View view)
    {
        Log.d("cake", "click!");

        model1.candlesLit = false;

        view1.invalidate();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
    {

        model1.candlesOrNot = isChecked;
        view1.invalidate();

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
    {
        model1.candlesAmount = progress;
        view1.invalidate();
    }

    public void onStartTrackingTouch(SeekBar seekBar)
    {
        // Nothing right now..
    }

    public void onStopTrackingTouch(SeekBar seekbar)
    {
        // Nothing right now..
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // find the coordinate of the tap
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        model1.balloonX = x;
        model1.balloonY = y;
        view1.invalidate();
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
        {
            model1.checkerX = motionEvent.getX();
            model1.checkerY = motionEvent.getY();
            view1.invalidate();
        }
        return true;
    }
}
