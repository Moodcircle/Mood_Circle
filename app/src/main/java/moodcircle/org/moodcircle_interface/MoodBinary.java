package moodcircle.org.moodcircle_interface;

        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.view.View;
        import android.support.design.widget.NavigationView;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.Menu;
        import android.view.MenuItem;

/** This is the activity for the binary (negative and positive) aspect of the mood assessment. */

public class MoodBinary extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_binary);
    }

    /** Called when the user clicks the NEXT button on the BINARY screen */
    public void saveBinaryResponse(View view) {
        // save positive SeekBar response
        // save negative SeekBar response
        // initiate the next part of the assessment
    }
}