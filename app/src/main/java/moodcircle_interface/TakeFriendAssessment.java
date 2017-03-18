package moodcircle_interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import eecs395_495.mhealth_moodcircle.R;


public class TakeFriendAssessment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the TAKE button on the MAIN screen */
    public void takeFriendAssessment (View view) {
        // move the user to the MoodFriendAssessment

    }
}
