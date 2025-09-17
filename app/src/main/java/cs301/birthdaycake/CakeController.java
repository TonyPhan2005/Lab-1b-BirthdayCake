package cs301.birthdaycake;

public class CakeController {

    private CakeView view1;
    private CakeModel model1;
    public CakeController(CakeView c)
    {
        this.view1 = c;
        this.model1 = view1.getCakeModel();
    }

}
