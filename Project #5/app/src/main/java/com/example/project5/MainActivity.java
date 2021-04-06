package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Main Activity for the museum admission calculator.
 * Displays possible museums that can be selected.
 * @author Nilay Naik, Evan Maggio
 */
public class MainActivity extends AppCompatActivity {

    private Button museum1Button, museum2Button, museum3Button, museum4Button;

    /**
     * Sets the view and buttons on the activity.
     * @param savedInstanceState bundle sent to activity on creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        museum1Button = (Button) findViewById(R.id.goToMuseum1Button);
        museum2Button = (Button) findViewById(R.id.goToMuseum2Button);
        museum3Button = (Button) findViewById(R.id.goToMuseum3Button);
        museum4Button = (Button) findViewById(R.id.goToMuseum4Button);

    }

    /**
     * Starts the admissions calculator activity.
     * Changes info sent to the activity depending on which museum button was pressed.
     * @param v View for getting button ID.
     */
    public void onClick(View v){
        Intent intent = new Intent(this, MuseumAdmissions.class);
        switch (v.getId()){
            case R.id.goToMuseum1Button:
                intent.putExtra(Intent.EXTRA_TEXT, "museum1");
                break;
            case R.id.goToMuseum2Button:
                intent.putExtra(Intent.EXTRA_TEXT, "museum2");
                break;
            case R.id.goToMuseum3Button:
                intent.putExtra(Intent.EXTRA_TEXT, "museum3");
                break;
            case R.id.goToMuseum4Button:
                intent.putExtra(Intent.EXTRA_TEXT, "museum4");
                break;
        }
        startActivity(intent);
    }


}