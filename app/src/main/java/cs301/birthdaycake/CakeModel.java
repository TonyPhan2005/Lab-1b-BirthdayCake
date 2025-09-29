package cs301.birthdaycake;

public class CakeModel {

    // Common variables from all versions
    public boolean candlesLit = true;
    public int candlesAmount = 2;
    public boolean frostingOrNot = true;
    public boolean candlesOrNot = true;

    // Touch coordinates from the first version
    public float touchX = -1;
    public float touchY = -1;

    // Balloon and checker coordinates from the third version
    public float balloonX = -1;
    public float balloonY = -1; // Initializing to -1 for consistency
    public float checkerX = -1f;
    public float checkerY = -1f;

}