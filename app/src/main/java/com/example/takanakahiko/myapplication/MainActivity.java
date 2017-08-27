package com.example.takanakahiko.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int RESULT_CAMERA = 1001;
    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView imageView;
    private TextView mImageDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageDetails = (TextView) findViewById(R.id.image_details);
        imageView = (ImageView)findViewById(R.id.image_view);
        Button cameraButton = (Button)findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RESULT_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CAMERA) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            uploadImage(bitmap);
        }
    }

    public void uploadImage(Bitmap bitmap) {
        if (bitmap == null) {
            Log.d(TAG, "Image picker gave us a null image.");
            return;
        }
        try {
            Log.d(TAG, "--------------------------------------");
            FetchImageAITask task = new FetchImageAITask(getBaseContext(), mImageDetails,bitmap);
            task.execute();
            Toast.makeText(this,"poststart", Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Log.d(TAG, "Image picking failed because " + e.getMessage());
        }
    }

}