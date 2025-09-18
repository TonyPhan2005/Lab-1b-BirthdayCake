package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    }
    public void goodbye(View button)
    {
        Log.i("button", "Goodbye");
    }
}
