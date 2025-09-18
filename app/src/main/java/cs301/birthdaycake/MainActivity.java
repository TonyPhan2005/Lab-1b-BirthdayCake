package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // Checkpoint 1
        CakeView cakeView = findViewById(R.id.cakeView);
        CakeController cakeController = new CakeController(cakeView);

        // Checkpoint 2
        Button myButton = findViewById(R.id.button1);
        myButton.setOnClickListener(cakeController);

        // Checkpoint 3
        Switch mySwitch = findViewById(R.id.Switch2);
        mySwitch.setOnCheckedChangeListener(cakeController);
    }
    public void goodbye(View button)
    {
        Log.i("button", "Goodbye");
    }
}
