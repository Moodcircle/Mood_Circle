package moodcircle_interface;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import SurveySender.SendSurveyToDatabase;
import eecs395_495.mhealth_moodcircle.MainActivity;
import eecs395_495.mhealth_moodcircle.R;

/**
 * This needs to generate interface (UI) content based off of items inhereted from MoodWords.
 * Meaning, MoodWords should save the selected words and pass them along to this screen.
 * This screen should take each of those words and turn them into a rating (SeekBar), similar to
 * the ones on MoodBinary.
 */

public class MoodRatings extends AppCompatActivity {
    Button finish;
    TextView ratings_positive;
    TextView ratings_negative;
    SeekBar positive_Seekbar;
    SeekBar negative_Seekbar;
    float positiveValue;
    float negativeValue;
    String positiveWord;
    String negativeWord;
    TextView binary_Message;
    String period;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_ratings);
        finish = (Button) findViewById(R.id.ratings_button_finish);
        ratings_negative = (TextView) findViewById(R.id.ratings_negative);
        ratings_positive = (TextView) findViewById(R.id.ratings_positive);
        binary_Message= (TextView) findViewById(R.id.binary_message);
        context=this;

        SharedPreferences sharedPreferences = getSharedPreferences("survey", 0);
        period=sharedPreferences.getString("period","");
        switch (period){
            case "morning":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how strongly have you felt each of the following this morning?");
                break;
            case "afternoon":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how strongly have you felt each of the following this afternoon?");
                break;
            case "evening":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how strongly have you felt each of the following this evening?");
                break;
            case "night":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how strongly have you felt each of the following tonight?");
                break;
            default:
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how strongly have you felt each of the following tonight?");
        }


        ratings_negative.setText("");
        ratings_positive.setText("");

        positiveWord=sharedPreferences.getString("pemotion", "");
        negativeWord=sharedPreferences.getString("nemotion", "");

        ratings_positive.setText(positiveWord);
        ratings_negative.setText(negativeWord);
        positive_Seekbar = (SeekBar) findViewById(R.id.ratings_positive_mood_slider);
        negative_Seekbar = (SeekBar) findViewById(R.id.ratings_negative_mood_slider);

        if (positiveWord.equals(""))
            negative_Seekbar.setVisibility(View.INVISIBLE);
        else
            negative_Seekbar.setVisibility(View.VISIBLE);

        if (negativeWord.equals(""))
            positive_Seekbar.setVisibility(View.INVISIBLE);
        else
            positive_Seekbar.setVisibility(View.VISIBLE);

        if(positiveWord.equals("")&&negativeWord.equals(""))
            binary_Message.setText("You have finished the test. Click FINISH to submit!");

    }

    /**
     * Called when the user clicks the FINISH button on the RATINGS screen
     */
    public void finishAssessment(View view) {
        // save each position of every SeekBar
        // push all responses (from this screen and previous screens) to the server
        // push all passive data on phone to server
        // save time assessment was completed
        // change status text of associated assessment to "Complete" (@string/main_label_status_complete)
        SharedPreferences finishShared = getSharedPreferences("survey", MODE_ENABLE_WRITE_AHEAD_LOGGING);
        positiveValue = positive_Seekbar.getProgress();
        negativeValue = negative_Seekbar.getProgress();
        SharedPreferences.Editor finishEditor = finishShared.edit();
        finishEditor.putFloat("prate", positiveValue);
        finishEditor.putFloat("nrate", negativeValue);
        finishEditor.commit();

        ShowDialog();
    }

    public void ShowDialog() {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("You have finished the test! Click OK to submit!");


        // Button OK
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new SendSurveyToDatabase(context).execute();
                        dialog.dismiss();
                        Intent returnMain = new Intent(MoodRatings.this, MainActivity.class);
                        MoodRatings.this.startActivity(returnMain);
                    }

                });
        popDialog.setNegativeButton("Back",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        dialog.dismiss();
                    }
        });
        popDialog.create();
        popDialog.show();

    }
}



