package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener,
        SeekBar.OnSeekBarChangeListener,
        View.OnTouchListener { // Includes all interfaces from all versions

    private CakeView view1;
    private CakeModel model1;

    public CakeController(CakeView c) {
        this.view1 = c;
        this.model1 = view1.getCakeModel();
        // Set the touch listener in the constructor from the first version
        view1.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        // Includes logging from the second and third versions
        Log.d("cake", "click!");

        // Common logic for all versions
        model1.candlesLit = false;

        view1.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        // Logic from all versions
        model1.candlesOrNot = isChecked;
        view1.invalidate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // Logic from all versions
        model1.candlesAmount = progress;
        view1.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Nothing right now.. (Kept the comment from the later versions)
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { // Note: changed 'seekbar' to 'seekBar' for consistency
        // Nothing right now.. (Kept the comment from the later versions)
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // This is a merge of the two different onTouch implementations:

        // From the third version:
        // Updates balloon coordinates on every touch event
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        model1.balloonX = x;
        model1.balloonY = y;

        // Common logic for ACTION_DOWN from all versions:
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            // From the first version (touchX, touchY for a generic tap handler):
            model1.touchX = motionEvent.getX();
            model1.touchY = motionEvent.getY();

            // From the third version (checkerX, checkerY for a different tap handler):
            model1.checkerX = motionEvent.getX();
            model1.checkerY = motionEvent.getY();

            view1.invalidate();
            // Return true here from the first version
            return true;
        }

        view1.invalidate(); // Call invalidate outside of ACTION_DOWN to update balloon
        return true; // Return true from the third version to consume the event
    }
}