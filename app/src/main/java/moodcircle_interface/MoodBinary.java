package moodcircle_interface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import eecs395_495.mhealth_moodcircle.R;

/**
 * This is the activity for the binary (negative and positive) aspect of the mood assessment.
 */

public class MoodBinary extends AppCompatActivity {

    SeekBar positiveSlider;
    SeekBar NegativeSlider;
    Button next;
    int positiveState;
    int NegativeState;
    TextView binary_Message;
    SharedPreferences sharedPreferences;
    String period;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_binary);
        positiveSlider = (SeekBar) findViewById(R.id.positive_mood_slider);
        NegativeSlider = (SeekBar) findViewById(R.id.binary_negative_mood_slider);
        next = (Button) findViewById(R.id.binary_button_next);
        binary_Message= (TextView) findViewById(R.id.binary_message);
        sharedPreferences=getSharedPreferences("survey",MODE_ENABLE_WRITE_AHEAD_LOGGING);
        period=sharedPreferences.getString("period","");
        switch (period){
            case "morning":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how positive and negative have you felt this morning?");
                break;
            case "afternoon":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how positive and negative have you felt this afternoon?");
                break;
            case "evening":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how positive and negative have you felt this evening?");
                break;
            case "night":
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how positive and negative have you felt tonight?");
                break;
            default:
                binary_Message.setText("On a scale of 0 (NOT AT ALL) to 5 (VERY MUCH), how positive and negative have you felt tonight?");
        }


//        binary_Message.setText();

    }

    /**
     * Called when the user clicks the NEXT button on the BINARY screen
     */
    public void saveBinaryResponse(View view) {
        // save positive SeekBar response
        // save negative SeekBar response
        // initiate the next part of the assessment
        positiveState = positiveSlider.getProgress();
        NegativeState = NegativeSlider.getProgress();
        SharedPreferences preferences=getSharedPreferences("survey",0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("positive",positiveState);
        editor.putInt("negative",NegativeState);
        editor.commit();
        Intent moodWords=new Intent(MoodBinary.this,MoodWords.class);
        MoodBinary.this.startActivity(moodWords);
    }
}