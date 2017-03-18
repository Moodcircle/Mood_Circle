package moodcircle_interface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eecs395_495.mhealth_moodcircle.R;

/**
 * This is the screen for the "Mood Diary." Meaning, users can access all assessments from here.
 */

public class SurveyActivity extends AppCompatActivity {
    Button morning;
    Button afternoon;
    Button evening;
    Button night;
    SharedPreferences sharedPreferences;
    String morningLabel = "morning";
    String afternoonLabel = "afternoon";
    String eveningLabel = "evening";
    String nightLabel = "night";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surveyactivity);
        morning = (Button) findViewById(R.id.main_button_morning);
        afternoon = (Button) findViewById(R.id.main_button_afternoon);
        evening = (Button) findViewById(R.id.main_button_evening);
        night = (Button) findViewById(R.id.main_button_night);
        sharedPreferences = getSharedPreferences("survey", MODE_ENABLE_WRITE_AHEAD_LOGGING);
    }

    /**
     * Called when the user clicks the SUBMIT button on the WORDS screen
     */
    public void takeMorningAssessment(View view) {
        // save the start time of when the individual is taking the assessment
        // this should set the text-time for the assessments moving forward (i.e., morning, afternoon,
        // evening, and night)
        // move the user to the MoodBinary of the assessment
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("period", morningLabel);
        editor.commit();
        Intent intent = new Intent(SurveyActivity.this, MoodSentences.class);
        SurveyActivity.this.startActivity(intent);


    }

    public void takeAfternoonAssessment(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("period", afternoonLabel);
        editor.commit();
        Intent intent = new Intent(SurveyActivity.this, MoodSentences.class);
        SurveyActivity.this.startActivity(intent);
    }

    public void takeEveningAssessment(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("period", eveningLabel);
        editor.commit();
        Intent intent = new Intent(SurveyActivity.this, MoodSentences.class);
        SurveyActivity.this.startActivity(intent);
    }

    public void takeNightAssessment(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("period", nightLabel);
        editor.commit();
        Intent intent = new Intent(SurveyActivity.this, MoodSentences.class);
        SurveyActivity.this.startActivity(intent);
    }
}
