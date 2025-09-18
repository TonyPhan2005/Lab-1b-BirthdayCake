package cs301.birthdaycake;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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

}
