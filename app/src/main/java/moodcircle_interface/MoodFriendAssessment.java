package moodcircle_interface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;

import SurveySender.SendFriendAssessmentToDatabase;
import eecs395_495.mhealth_moodcircle.MainActivity;
import eecs395_495.mhealth_moodcircle.R;


/**
 * Created by laurenru on 2/25/17.
 */

public class MoodFriendAssessment extends AppCompatActivity {
    CheckBox yes;
    CheckBox no;
    SeekBar confidence;
    Button submit;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_assessment);
        yes= (CheckBox) findViewById(R.id.checkBoxyes);
        no= (CheckBox) findViewById(R.id.checkBoxno);
        confidence= (SeekBar) findViewById(R.id.binary_confidence_mood_slider);
        submit= (Button) findViewById(R.id.friend_submit_button);
        sharedPreferences=getSharedPreferences("friend",MODE_ENABLE_WRITE_AHEAD_LOGGING);
    }
    /** Called when the user clicks the SUBMIT button on the FRIEND ASSESSMENT screen */
    public void saveFriendResponse(View view) {
        // save positive SeekBar response
        // save negative SeekBar response
        // save confidence SeekBar response
        // return to main screen
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(yes.isChecked())
            editor.putInt("interaction",1);
        if(no.isChecked())
            editor.putInt("interaction",0);

        editor.putInt("confidence",confidence.getProgress());
        editor.commit();
        new SendFriendAssessmentToDatabase(this).execute();
        Intent intent=new Intent(MoodFriendAssessment.this, MainActivity.class);
        MoodFriendAssessment.this.startActivity(intent);

    }
}


