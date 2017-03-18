package DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by 51375 on 2017/2/28.
 */

public class CalllogSender extends AsyncTask<Void, Void, Object> {

    private static final String TAG = "SMSSender";
    private static boolean isReplicating = false;
    private boolean isCanceled = false;

    private Context context;

    public CalllogSender(Context context) {
        this.context = context;
    }

    @Override
    /**
     * When execute() is called, this happens first
     */
    protected void onPreExecute() {
        isCanceled = isReplicating;
        isReplicating = true;
        Log.v(TAG, "pre");
    }

    @Override
    /**
     * When execute() is called, this happens second
     */
    protected Void doInBackground(Void... params) {

        // Don't do anything if the execution is canceled
        if (!isCanceled) {
            // Query the database and package the data
//            Cursor c = EnergyDBHelper.getFirst60Entries(context);
//            int timeCol = c.getColumnIndex(EnergyDBHelper.EnergyEntry.COLUMN_NAME_TIME);
//            int energyCol = c.getColumnIndex(EnergyDBHelper.EnergyEntry.COLUMN_NAME_ENERGY);
            // EnergyDBHelper.deleteNEntries(context, 0);

//            c.moveToFirst();
            int ID=7;
            Cursor cursor = SurveyDBHelper.getFirst60Entries(context, SurveyDBHelper.EnergyEntry.CALLLOG_MOODCIRCLE);
            cursor.moveToFirst();
//            cursor.
//            int energyIndex = cursor.getColumnIndex(SurveyDBHelper.EnergyEntry.COLUMN_NAME_ENERGY);
//            int dateIndex = cursor.getColumnIndex(SurveyDBHelper.EnergyEntry.COLUMN_NAME_TIME);
            int dateIndex = cursor.getColumnIndex(SurveyDBHelper.EnergyEntry.COLUMN_NAME_DATE);
            int phNumber = cursor.getColumnIndex(SurveyDBHelper.EnergyEntry.COLUMN_NAME_PHONENUMBER);
            int duration=cursor.getColumnIndex(SurveyDBHelper.EnergyEntry.COLUMN_NAME_DURATION);
            String data = "";

            while (!cursor.isAfterLast()) {
                data = "";
                try {
                    data += URLEncoder.encode("phonenumber", "UTF-8") + "=" + URLEncoder.encode(cursor.getString(phNumber), "UTF-8");
                    data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(cursor.getString(dateIndex), "UTF-8");
                    data += "&" + URLEncoder.encode("duration", "UTF-8") + "=" + URLEncoder.encode(cursor.getString(duration), "UTF-8");
                    data += "&" + URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ID), "UTF-8");
                    Log.v(TAG, data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                OutputStreamWriter writer = null;
                BufferedReader reader;
                try {
                    URL url = new URL("http://murphy.wot.eecs.northwestern.edu/~ywn3512/CALLgateway.py");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setConnectTimeout(3000);
                    urlConnection.setDoInput(true);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setUseCaches(false);
                    writer = new OutputStreamWriter(urlConnection.getOutputStream());
//                OutputStream os = urlConnection.getOutputStream();
//                writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
                    writer.write(data);
                    writer.flush();

                    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                        Log.v(TAG, sb.toString());
                    }
//  throw new UnsupportedOperationException("Not yet implemented");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null)
                            writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                SurveyDBHelper.deleteNEntries(context, 1, SurveyDBHelper.EnergyEntry.CALLLOG_MOODCIRCLE);
                cursor.moveToNext();
            }
        }
        return null;
    }

    @Override
    /**
     * When execute is called, this happens third
     */
    protected void onPostExecute(Object result) {
        isReplicating = false;
    }


}
