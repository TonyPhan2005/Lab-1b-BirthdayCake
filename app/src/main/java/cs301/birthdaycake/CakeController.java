package cs301.birthdaycake;

import android.util.Log;
import android.view.View;

public class CakeController implements View.OnClickListener  {

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

}
