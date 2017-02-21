package moodcircle.org.moodcircle_interface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/** This is the activity for the mood words aspect of the mood assessment. */

public class MoodWords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_words);
    }

    /** Called when the user clicks the SUBMIT button on the WORDS screen */
    public void saveWordsResponse(View view) {
        // save selected words (indicated via words selected during toggleWordsResponse)
        // save "other" words (e.g., other positive and other negative) if a user has input a unique word
        // initiate the next part of the assessment
    }

    /** Called when the user clicks the SUBMIT button on the WORDS screen */
    public void toggleWordsResponse(View view) {
        // if the word is unselected, select it.
        // if the word is selected, unselect it.

    }
}
