package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonLeft;
    Button buttonMiddle;
    Button buttonRight;
    int Score = 0;

    TextView textScores;
    final String TAG = "Whack-a-mole 1.0";
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLeft = (Button) findViewById(R.id.button2);
        buttonMiddle = (Button) findViewById(R.id.button1);
        buttonRight = (Button) findViewById(R.id.button3);
        textScores = (TextView) findViewById(R.id.textView1);

        buttonLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Left clicked!");
                doCheck(buttonLeft);
            }

        });
        buttonMiddle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Middle clicked!");
                doCheck(buttonMiddle);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.v(TAG, "Button Right clicked!");
                doCheck(buttonRight);
            }
        });
        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
        if (checkButton.getText() == "*"){
            Score++;
            Log.v(TAG, "Hit Score added!");
        }
        else{
            Score--;
            Log.v(TAG, "Missed, score deducted!");
        }
        textScores.setText(Integer.toString(Score));
        setNewMole();
        if(Score % 10 == 0 && Score > 0){
            nextLevelQuery();
        }
    }

    private void nextLevelQuery(){
        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning! Insane Whack-A-Mole Incoming!");
        builder.setMessage("Would you like to advance to advanced mode?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nextLevel();
                Log.v(TAG, "User accepts!");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG, "User decline!");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        Log.v(TAG, "Advance option given to user!");
    }

    private void nextLevel(){
        /* Launch advanced page */
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("Scores", Score);
        startActivity(intent);
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        buttonLeft.setText("O");
        buttonMiddle.setText("O");
        buttonRight.setText("O");
        if (randomLocation == 0){
            buttonLeft.setText("*");
        }
        else if (randomLocation == 1){
            buttonMiddle.setText("*");
        }
        else if (randomLocation == 2){
            buttonRight.setText("*");
        }
    }
}