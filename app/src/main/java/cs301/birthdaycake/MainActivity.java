package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeView);
        CakeController cakeController = new CakeController(cakeView);

        Button myButton = findViewById(R.id.button1);
        myButton.setOnClickListener(cakeController);

        Switch mySwitch = findViewById(R.id.Switch2);
        mySwitch.setOnCheckedChangeListener(cakeController);

        SeekBar mySeekBar = findViewById(R.id.seekBar);
        mySeekBar.setOnSeekBarChangeListener(cakeController);

        // Add On touch event
        cakeView.setOnTouchListener(cakeController);

    }
    public void goodbye(View button)
    {
        Log.i("button", "Goodbye");
    }
}
