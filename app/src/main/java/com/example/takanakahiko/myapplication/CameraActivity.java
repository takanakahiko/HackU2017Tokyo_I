package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {

    private final static int RESULT_CAMERA = 1001;
    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView mimageView;
    private ListView mlistView;
    private LinearLayout loading;
    private TextView retTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mimageView = (ImageView)findViewById(R.id.image_view);
        mlistView = (ListView) findViewById(R.id.element_list);
        loading = (LinearLayout) findViewById(R.id.loading);
        retTextView = (TextView) findViewById(R.id.retTextView);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, RESULT_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(bitmap);
            uploadImage(bitmap);
        }
    }

    public void uploadImage(Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Image picker gave us a null image.");
            return;
        }
        try {
            FetchImageAITask task = new FetchImageAITask(getApplicationContext(), bitmap, mlistView, loading, retTextView);
            task.execute();
            Toast.makeText(this,"poststart", Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Log.d(TAG, "Image picking failed because " + e.getMessage());
        }
    }
}
