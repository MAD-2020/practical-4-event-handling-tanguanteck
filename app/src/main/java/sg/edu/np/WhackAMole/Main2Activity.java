package sg.edu.np.WhackAMole;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 8.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The functions readTimer() and placeMoleTimer() are to inform the user X seconds before starting and loading new mole.
        - Feel free to modify the function to suit your program.
    */
            final String TAG = "Whack-a-mole 2.0";
            int advScore;
            Button buttontopLeft;
            Button buttontopMiddle;
            Button buttontopRight;
            Button buttonmidLeft;
            Button buttonmidMiddle;
            Button buttonmidRight;
            Button buttonbotLeft;
            Button buttonbotMiddle;
            Button buttonbotRight;
            TextView ViewScore;

            CountDownTimer sCountdown;
            CountDownTimer zCountdown;
    private void readyTimer(){
        /*  HINT:
            The "Get Ready" Timer.
            Log.v(TAG, "Ready CountDown!" + millisUntilFinished/ 1000);
            Toast message -"Get Ready In X seconds"
            Log.v(TAG, "Ready CountDown Complete!");
            Toast message - "GO!"
            belongs here.
            This timer countdown from 10 seconds to 0 seconds and stops after "GO!" is shown.
         */
        sCountdown = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                Toast.makeText(Main2Activity.this, "Get Ready in " + l/1000 + " seconds.", Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown!" + l/ 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(Main2Activity.this,"Go",Toast.LENGTH_SHORT).show();
                Log.v(TAG, "Ready CountDown Complete!");
                sCountdown.cancel();

                placeMoleTimer();
            }
        };
        sCountdown.start();


    }
    private void placeMoleTimer(){
        /* HINT:
           Creates new mole location each second.
           Log.v(TAG, "New Mole Location!");
           setNewMole();
           belongs here.
           This is an infinite countdown timer.
         */
        zCountdown = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {
                setNewMole();
                Log.v(TAG, "New Mole Location!");
            }

            @Override
            public void onFinish() {
                zCountdown.start();

            }
        };
        zCountdown.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Hint:
            This starts the countdown timers one at a time and prepares the user.
            This also prepares the existing score brought over.
            It also prepares the button listeners to each button.
            You may wish to use the for loop to populate all 9 buttons with listeners.
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        buttontopLeft = (Button) findViewById(R.id.buttontop_left);
        buttontopMiddle = (Button) findViewById(R.id.buttontop_middle);
        buttontopRight = (Button) findViewById(R.id.buttontop_right);
        buttonmidLeft = (Button) findViewById(R.id.buttonmiddle_left);
        buttonmidMiddle = (Button) findViewById(R.id.buttonmiddle_left);
        buttonmidRight = (Button) findViewById(R.id.buttonmiddle_left);
        buttonbotLeft = (Button) findViewById(R.id.buttonbot_left);
        buttonbotMiddle = (Button) findViewById(R.id.buttonbot_middle);
        buttonbotRight = (Button) findViewById(R.id.buttonbot_right);
        ViewScore = (TextView) findViewById(R.id.textView3);

        buttontopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttontopLeft);
            }
        });
        buttontopMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttontopMiddle);
            }
        });
        buttontopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttontopRight);
            }
        });
        buttonmidLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonmidLeft);
            }
        });
        buttonmidMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonmidLeft);
            }
        });
        buttonmidRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonmidRight);
            }
        });
        buttonbotLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonbotLeft);
            }
        });
        buttonbotMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonbotMiddle);
            }
        });
        buttonbotRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doCheck(buttonbotRight);
            }
        });


        Log.v(TAG, "Current User Score: " + String.valueOf(advScore));


        //for(final int id : BUTTON_IDS){
            /*  HINT:
            This creates a for loop to populate all 9 buttons with listeners.
            You may use if you wish to remove or change to suit your codes.
            */
        //}
    }
    @Override
    protected void onStart(){
        super.onStart();
        Intent receivingEnd = getIntent();
        int message = receivingEnd.getIntExtra("Scores", 0);
        advScore = message;
        ViewScore.setText(String.valueOf(message));
        readyTimer();

    }
    private void doCheck(Button checkButton)
    {
        /* Hint:
            Checks for hit or miss
            Log.v(TAG, "Hit, score added!");
            Log.v(TAG, "Missed, point deducted!");
            belongs here.
        */
        if (checkButton.getText() == "*"){
            advScore++;
            Log.v(TAG, "Hit Score added!");
        }
        else{
            advScore--;
            Log.v(TAG, "Missed, score deducted!");
        }
        ViewScore.setText(Integer.toString(advScore));
        setNewMole();
    }

    public void setNewMole()
    {
        /* Hint:
            Clears the previous mole location and gets a new random location of the next mole location.
            Sets the new location of the mole.
         */
        Random ran = new Random();
        int randomLocation = ran.nextInt(9);
        buttontopLeft.setText("O");
        buttontopMiddle.setText("O");
        buttontopRight.setText("O");
        buttonmidLeft.setText("O");
        buttonmidMiddle.setText("O");
        buttonmidRight.setText("O");
        buttonbotLeft.setText("O");
        buttonbotMiddle.setText("O");
        buttonbotRight.setText("O");
        if (randomLocation == 0){
            buttontopLeft.setText("*");
        }
        else if (randomLocation == 1){
            buttontopMiddle.setText("*");
        }
        else if (randomLocation == 2){
            buttontopRight.setText("*");
        }
        else if (randomLocation == 3){
            buttonmidLeft.setText("*");
        }
        else if (randomLocation == 4){
            buttonmidMiddle.setText("*");
        }
        else if (randomLocation == 5){
            buttonmidRight.setText("*");
        }
        else if (randomLocation == 6){
            buttonbotLeft.setText("*");
        }
        else if (randomLocation == 7){
            buttonbotMiddle.setText("*");
        }
        else if (randomLocation == 8){
            buttonbotRight.setText("*");
        }
    }
}

