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
 * Created by 51375 on 2017/2/28.
 */

public class SendSurveyToDatabase extends AsyncTask<Void,Void,Object> {
    private static final String TAG = "SMSSender";
    private static boolean isReplicating = false;
    private boolean isCanceled = false;

    private Context context;
    private SharedPreferences surveyResult;

    public SendSurveyToDatabase(Context context) {
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
            surveyResult=context.getSharedPreferences("survey",Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);


            String data = "";

            int ID=7;

            double longitude=surveyResult.getFloat("longitude", (float) 0.0);
            double latitude=surveyResult.getFloat("latitude", (float) 0.0);
            Log.v("longitude", String.valueOf(longitude));
            int positive=surveyResult.getInt("positive",0);
            int negative=surveyResult.getInt("negative",0);
            int happy=surveyResult.getInt("happy",0);
            int grateful=surveyResult.getInt("grateful",0);
            int content=surveyResult.getInt("content",0);
            int enthusiastic=surveyResult.getInt("enthusiastic",0);
            int inspired=surveyResult.getInt("inspired",0);
            int proud=surveyResult.getInt("proud",0);
            int guilty=surveyResult.getInt("guilty",0);
            int anxious=surveyResult.getInt("anxious",0);
            int bored=surveyResult.getInt("bored",0);
            int fearful=surveyResult.getInt("fearful",0);
            int angry=surveyResult.getInt("angry",0);
            int sad=surveyResult.getInt("sad",0);
            String pemotion=surveyResult.getString("pemotion",null);
            String nemotion=surveyResult.getString("nemotion",null);
            float prate=surveyResult.getFloat("prate", (float) 0.0);
            float nrate=surveyResult.getFloat("nrate", (float) 0.0);
            String sentence=surveyResult.getString("sentence","");
            Log.v("sentence is ",sentence);
            Date date=new Date();

            //angry int, sad int, pemotion varchar(80), nemotion varchar(80), prate double, nrate double);




                data = "";
                try {
                    data += URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ID), "UTF-8");
                    data += "&" + URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(longitude), "UTF-8");
                    data += "&" + URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(date), "UTF-8");
                    data += "&" + URLEncoder.encode("latitude", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(latitude), "UTF-8");
                    data += "&" + URLEncoder.encode("positive", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(positive), "UTF-8");
                    data += "&" + URLEncoder.encode("negative", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(negative), "UTF-8");
                    data += "&" + URLEncoder.encode("happy", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(happy), "UTF-8");
                    data += "&" + URLEncoder.encode("grateful", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(grateful), "UTF-8");
                    data += "&" + URLEncoder.encode("enthusiastic", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(enthusiastic), "UTF-8");
                    data += "&" + URLEncoder.encode("inspired", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(inspired), "UTF-8");
                    data += "&" + URLEncoder.encode("proud", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(proud), "UTF-8");
                    data += "&" + URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(content), "UTF-8");
                    data += "&" + URLEncoder.encode("guilty", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(guilty), "UTF-8");
                    data += "&" + URLEncoder.encode("anxious", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(anxious), "UTF-8");
                    data += "&" + URLEncoder.encode("bored", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(bored), "UTF-8");
                    data += "&" + URLEncoder.encode("fearful", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(fearful), "UTF-8");
                    data += "&" + URLEncoder.encode("angry", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(angry), "UTF-8");
                    data += "&" + URLEncoder.encode("sad", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(sad), "UTF-8");
                    data += "&" + URLEncoder.encode("pemotion", "UTF-8") + "=" + URLEncoder.encode(pemotion, "UTF-8");
                    data += "&" + URLEncoder.encode("nemotion", "UTF-8") + "=" + URLEncoder.encode(nemotion, "UTF-8");
                    data += "&" + URLEncoder.encode("prate", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(prate), "UTF-8");
                    data += "&" + URLEncoder.encode("nrate", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(nrate), "UTF-8");
                    data += "&" + URLEncoder.encode("sentence", "UTF-8") + "=" + URLEncoder.encode(sentence, "UTF-8");
                    Log.v(TAG, data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                OutputStreamWriter writer = null;
                BufferedReader reader;
                try {
                    URL url = new URL("http://murphy.wot.eecs.northwestern.edu/~ywn3512/SQLGateway.py");
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
