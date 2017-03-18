package SurveySender;

import android.content.Context;
import android.content.SharedPreferences;
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
import java.util.Date;

/**
 * Created by 51375 on 2017/3/3.
 */

public class SendFriendAssessmentToDatabase extends AsyncTask<Void,Void,Object> {
    private static final String TAG = "SMSSender";
    private static boolean isReplicating = false;
    private boolean isCanceled = false;

    private Context context;
    private SharedPreferences surveyResult;

    public SendFriendAssessmentToDatabase(Context context) {
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
            surveyResult=context.getSharedPreferences("friend",Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);


            String data = "";

            int ID=7;

            int interaction=surveyResult.getInt("interaction",0);
            int confidence=surveyResult.getInt("confidence",0);
            Log.v("interaction", String.valueOf(interaction));
            Log.v("confidence", String.valueOf(confidence));
            Date date=new Date();

            //angry int, sad int, pemotion varchar(80), nemotion varchar(80), prate double, nrate double);




            data = "";
            try {
                data += URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ID), "UTF-8");
                data += "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(date), "UTF-8");
                data += "&" + URLEncoder.encode("interaction", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(interaction), "UTF-8");
                data += "&" + URLEncoder.encode("confidence", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(confidence), "UTF-8");
                Log.v(TAG, data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            OutputStreamWriter writer = null;
            BufferedReader reader;
            try {
                URL url = new URL("http://murphy.wot.eecs.northwestern.edu/~ywn3512/FriendGateway.py");
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

