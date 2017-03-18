package moodcircle_interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import eecs395_495.mhealth_moodcircle.R;

/**
 * Created by laurenru on 2/25/17.
 */

public class MoodFriendCircle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);

    }

    public void gotoMySurveyPage(View view){
        Intent surveyPage=new Intent(MoodFriendCircle.this,SurveyActivity.class);
        MoodFriendCircle.this.startActivity(surveyPage);
    }

    public void gotoFriendAssessment(View view){
        Intent FriendAssessment=new Intent(MoodFriendCircle.this,MoodFriendAssessment.class);
        MoodFriendCircle.this.startActivity(FriendAssessment);
    }


    /** Called when the user clicks the SUBMIT button on the FRIEND ASSESSMENT screen */
    public void saveFriendResponse(View view) {
        // save positive SeekBar response
        // save negative SeekBar response
        // save confidence SeekBar response
        // return to main screen
    }
}
