package com.example.takanakahiko.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by hayatokimura on 2017/04/09.
 */


public class FetchImageAITask extends AsyncTask<Integer, Integer, String> {

    private String api_key;
    private ListView mListView;
    private HttpsURLConnection mConnection;
    private Bitmap mBitmap;
    private Context context;

    /**
     * コンストラクタ
     */
    public FetchImageAITask(Context context, Bitmap bitmap, ListView listView) {
        super();
        this.context = context;
        this.mListView = listView;
        this.mBitmap = bitmap;
        this.api_key = context.getString(R.string.cloudvision);
        try {

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * バックグランドで行う処理
     */
    @Override
    protected String doInBackground(Integer... value) {

        String response = "faild";
        String end_point = "https://vision.googleapis.com/v1/images:annotate?key="+api_key;

        //Bitmapをbase64に変換
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String strBase64 =  Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        String sendData = String.format("{\"requests\":[{\"image\":{\"content\":\"%s\"},\"features\":[{\"type\":\"LABEL_DETECTION\",\"maxResults\":3}]}]}",strBase64);

        try {
            byte[] sendJson = sendData.getBytes("UTF-8");
            URL url = new URL(end_point);
            mConnection = (HttpsURLConnection) url.openConnection();
            mConnection.setRequestMethod("POST");
            mConnection.setRequestProperty("Content-Type", "application/json");
            mConnection.setRequestProperty("Accept", "application/json");
            mConnection.setDoInput(true);
            mConnection.setDoOutput(true);
            mConnection.getOutputStream().write(sendJson);
            mConnection.getOutputStream().flush();
            mConnection.getOutputStream().close();
            mConnection.connect();
            InputStream in = mConnection.getInputStream();
            byte bodyByte[] = new byte[1024];
            in.read(bodyByte);
            in.close();
            response = new String(bodyByte,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            JSONObject json = new JSONObject(result);
            JSONArray responses = json.getJSONArray("responses");
            JSONArray labelAnnotations = responses.getJSONObject(0).getJSONArray("labelAnnotations");
            for (int i = 0; i < labelAnnotations.length(); i++) {
                JSONObject data = labelAnnotations.getJSONObject(i);
                String description = data.getString("description");
                String score = data.getString("score");
                list.add(description+":"+score);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(arrayAdapter);
    }
}
