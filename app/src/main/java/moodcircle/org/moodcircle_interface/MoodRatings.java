package moodcircle.org.moodcircle_interface;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/** This needs to generate interface (UI) content based off of items inhereted from MoodWords.
 * Meaning, MoodWords should save the selected words and pass them along to this screen.
 * This screen should take each of those words and turn them into a rating (SeekBar), similar to
 * the ones on MoodBinary.
 */

public class MoodRatings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_ratings);
    }

    /** Called when the user clicks the FINISH button on the RATINGS screen */
    public void finishAssessment(View view) {
        // save each position of every SeekBar
        // push all responses (from this screen and previous screens) to the server
        // push all passive data on phone to server
        // save time assessment was completed
        // change status text of associated assessment to "Complete" (@string/main_label_status_complete)
    }

}
