package moodcircle_interface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eecs395_495.mhealth_moodcircle.R;

public class MoodSentences extends AppCompatActivity {
    EditText sentence;
    Button sentenceNext;
    private TextView binary_Message;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_sentences);
        sentence= (EditText) findViewById(R.id.sentences_mood_text);
        sentenceNext= (Button) findViewById(R.id.binary_button_next);
        binary_Message= (TextView) findViewById(R.id.sentences_message);
        sharedPreferences=getSharedPreferences("survey",MODE_ENABLE_WRITE_AHEAD_LOGGING);
        switch (sharedPreferences.getString("period","")){
            case "morning":
                binary_Message.setText("How have you felt this morning? Why?");
                break;
            case "afternoon":
                binary_Message.setText("How have you felt this afternoon? Why?");
                break;
            case "evening":
                binary_Message.setText("How have you felt this evening? Why?");
                break;
            case "night":
                binary_Message.setText("How have you felt tonight? Why?");
                break;
            default:
                binary_Message.setText("How have you felt tonight? Why?");
        }

    }

    /** Called when the user clicks the NEXT button on the SENTENCES screen */
    public void saveSentencesResponse(View view) {
        // save the text the user has entered
        // it's OK if the user hasn't entered any text!
        SharedPreferences.Editor sentenceEditor=sharedPreferences.edit();
        sentenceEditor.putString("sentence", String.valueOf(sentence.getText()));
        sentenceEditor.commit();
        Intent intent=new Intent(MoodSentences.this,MoodBinary.class);
        MoodSentences.this.startActivity(intent);
    }
}
