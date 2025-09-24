package cs301.birthdaycake;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener,
        View.OnTouchListener {

    private CakeView view1;
    private CakeModel model1;

    public CakeController(CakeView c) {
        view1 = c;
        model1 = view1.getCakeModel();
        view1.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        model1.candlesLit = false;
        view1.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        model1.candlesOrNot = isChecked;
        view1.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        model1.candlesAmount = progress;
        view1.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            model1.touchX = event.getX();
            model1.touchY = event.getY();
            view1.invalidate();
            return true;
        }
        return false;
    }
}
