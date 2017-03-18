package moodcircle_interface;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import eecs395_495.mhealth_moodcircle.R;

/**
 * This is the activity for the mood words aspect of the mood assessment.
 */

public class MoodWords extends AppCompatActivity {
    //positive buttons
    Button Happy;
    Button Grateful;
    Button Content;
    Button Enthusiastic;
    Button Inspired;
    Button Proud;
    EditText PositiveEmotion;
    //negative buttons
    Button Guilty;
    Button Anxious;
    Button Bored;
    Button Fearful;
    Button Angry;
    Button Sad;
    EditText NegativeEmotion;

    //submit button
    Button submit;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_words);

        //all emotion buttons
        Happy = (Button) findViewById(R.id.words_button_happy);
        Grateful = (Button) findViewById(R.id.words_button_grateful);
        Content = (Button) findViewById(R.id.words_button_content);
        Enthusiastic = (Button) findViewById(R.id.words_button_enthusiastic);
        Inspired = (Button) findViewById(R.id.words_button_inspired);
        Proud = (Button) findViewById(R.id.words_button_proud);
        Guilty = (Button) findViewById(R.id.words_button_guilty);
        Anxious = (Button) findViewById(R.id.words_button_anxious);
        Bored = (Button) findViewById(R.id.words_button_bored);
        Fearful = (Button) findViewById(R.id.words_button_fearful);
        Angry = (Button) findViewById(R.id.words_button_angry);
        Sad = (Button) findViewById(R.id.words_button_sad);

        //EditText
        PositiveEmotion = (EditText) findViewById(R.id.words_other_positive);
        NegativeEmotion = (EditText) findViewById(R.id.words_other_negative);
        sharedPreferences = getSharedPreferences("survey", MODE_ENABLE_WRITE_AHEAD_LOGGING);
        //submit button
        submit = (Button) findViewById(R.id.words_button_submit);

        SharedPreferences.Editor initialEditor = sharedPreferences.edit();
//        Log.v("initial", "put value");
        initialEditor.putInt("happy", 0);
//        Log.v("happy", String.valueOf(sharedPreferences.getInt("happy", 0)));
        initialEditor.putInt("grateful", 0);
        initialEditor.putInt("content", 0);
        initialEditor.putInt("enthusiastic", 0);
        initialEditor.putInt("inspired", 0);
        initialEditor.putInt("proud", 0);
        initialEditor.putInt("guilty", 0);
        initialEditor.putInt("anxious", 0);
        initialEditor.putInt("bored", 0);
        initialEditor.putInt("fearful", 0);
        initialEditor.putInt("angry", 0);
        initialEditor.putInt("sad", 0);
        initialEditor.commit();
    }

    protected void onResume() {
        super.onResume();
        SharedPreferences.Editor initialEditor = sharedPreferences.edit();
        initialEditor.putInt("happy", 0);
        initialEditor.putInt("grateful", 0);
        initialEditor.putInt("content", 0);
        initialEditor.putInt("enthusiastic", 0);
        initialEditor.putInt("inspired", 0);
        initialEditor.putInt("proud", 0);
        initialEditor.putInt("guilty", 0);
        initialEditor.putInt("anxious", 0);
        initialEditor.putInt("bored", 0);
        initialEditor.putInt("fearful", 0);
        initialEditor.putInt("angry", 0);
        initialEditor.putInt("sad", 0);
    }

    /**
     * Called when the user clicks the SUBMIT button on the WORDS screen
     */
    public void saveWordsResponse(View view) {
        // save selected words (indicated via words selected during toggleWordsResponse)
        // save "other" words (e.g., other positive and other negative) if a user has input a unique word
        // initiate the next part of the assessment
        String pemotion;
        String nemotion;

        pemotion = PositiveEmotion.getText().toString();
        nemotion = NegativeEmotion.getText().toString();

        SharedPreferences.Editor motionEditor = sharedPreferences.edit();
        motionEditor.putString("pemotion", pemotion);
        motionEditor.putString("nemotion", nemotion);
        motionEditor.commit();
        Intent moodRatings = new Intent(MoodWords.this, MoodRatings.class);
        MoodWords.this.startActivity(moodRatings);
    }

    /**
     * Called when the user clicks the SUBMIT button on the WORDS screen
     */
    public void toggleWordsResponseHappy(View view) {
        // if the word is unselected, select it.
        // if the word is selected, unselect it.
//        if (Happy.getSolidColor() != Color.BLUE)
//        else
//            Happy.setBackgroundColor(Color.GRAY);
        Log.v("happy", String.valueOf(sharedPreferences.getInt("happy", 0)));
        Log.v("content", String.valueOf(sharedPreferences.getInt("content", 0)));
        Happy.setBackgroundColor(Color.BLUE);
        ShowDialog("happy");
//        SharedPreferences.Editor happy = sharedPreferences.edit();
//        Log.v("happy", String.valueOf(sharedPreferences.getInt("happy", 0)));
//        happy.putInt("happy", 1);
//        happy.commit();
    }

    public void toggleWordsResponseGrateful(View view) {
        Grateful.setBackgroundColor(Color.BLUE);
        SharedPreferences.Editor grateful = sharedPreferences.edit();
//        grateful.putInt("grateful", 1);
//        grateful.commit();
        ShowDialog("grateful");
    }

    public void toggleWordsResponseContent(View view) {
        Content.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor content = sharedPreferences.edit();
//        content.putInt("content", 1);
//        content.commit();
        ShowDialog("content");
    }

    public void toggleWordsResponseEnthusiastic(View view) {
        Enthusiastic.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor enthusiastic = sharedPreferences.edit();
//        enthusiastic.putInt("enthusiastic", 1);
//        enthusiastic.commit();
        ShowDialog("enthusiastic");
    }

    public void toggleWordsResponseInspired(View view) {
        Inspired.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor inspired = sharedPreferences.edit();
//        inspired.putInt("inspired", 1);
//        inspired.commit();
        ShowDialog("inspired");
    }

    public void toggleWordsResponseProud(View view) {
        Proud.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor proud = sharedPreferences.edit();
//        proud.putInt("proud", 1);
//        proud.commit();
        ShowDialog("proud");
    }

    public void toggleWordsResponseGuilty(View view) {
        Guilty.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor guilty = sharedPreferences.edit();
//        guilty.putInt("guilty", 1);
//        guilty.commit();
        ShowDialog("guilty");
    }

    public void toggleWordsResponseAnxious(View view) {
        Anxious.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor anxious = sharedPreferences.edit();
//        anxious.putInt("anxious", 1);
//        anxious.commit();
        ShowDialog("anxious");
    }

    public void toggleWordsResponseBored(View view) {
        Bored.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor bored = sharedPreferences.edit();
//        bored.putInt("bored", 1);
//        bored.commit();
        ShowDialog("bored");
    }

    public void toggleWordsResponseFearful(View view) {
        Fearful.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor fearful = sharedPreferences.edit();
//        fearful.putInt("fearful", 1);
//        fearful.commit();
        ShowDialog("fearful");
    }

    public void toggleWordsResponseAngry(View view) {
        Angry.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor angry = sharedPreferences.edit();
//        angry.putInt("angry", 1);
//        angry.commit();
        ShowDialog("angry");
    }

    public void toggleWordsResponseSad(View view) {
        Sad.setBackgroundColor(Color.BLUE);
//        SharedPreferences.Editor sad = sharedPreferences.edit();
//        sad.putInt("sad", 1);
//        sad.commit();
        ShowDialog("sad");
    }
//    Inspired = (Button) findViewById(R.id.words_button_inspired);
//    Proud = (Button) findViewById(R.id.words_button_proud);
//    Guilty= (Button) findViewById(R.id.words_button_guilty);
//    Anxious= (Button) findViewById(R.id.words_button_anxious);
//    Bored= (Button) findViewById(R.id.words_button_bored);
//    Fearful= (Button) findViewById(R.id.words_button_fearful);
//    Angry= (Button) findViewById(R.id.words_button_angry);
//    Sad= (Button) findViewById(R.id.words_button_sad);


    public void ShowDialog(final String mood) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        seek.setMax(5);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Please Indicating Your Rating of "+mood);
        popDialog.setView(seek);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Do something here with new value
                SharedPreferences.Editor moodEditor=sharedPreferences.edit();
                moodEditor.putInt(mood,progress);
                moodEditor.commit();
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
        });


        // Button OK
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(mood, String.valueOf(sharedPreferences.getInt(mood,0)));
                        dialog.dismiss();
                    }

                });


        popDialog.create();
        popDialog.show();

    }


}



