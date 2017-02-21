package moodcircle.org.moodcircle_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/** This is the screen for the "Mood Diary." Meaning, users can access all assessments from here. */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the SUBMIT button on the WORDS screen */
    public void takeAssessment (View view) {
        // save the start time of when the individual is taking the assessment
        // this should set the text-time for the assessments moving forward (i.e., morning, afternoon,
        // evening, and night)
        // move the user to the MoodBinary of the assessment

    }
}
