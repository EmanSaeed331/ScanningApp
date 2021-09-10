package com.example.lenovo.scannapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lenovo.scannapp.R;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.File;
import java.io.IOException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 99;
    private Button scanButton;
    private ImageButton cameraButton;
    private ImageButton mediaButton;
    private ImageView scannedImageView;
    ImageButton CreateFolderbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_main);
        CreateFolderbtn = (ImageButton) findViewById(R.id.CreateButton);
        final Button  cancelbtn = (Button) findViewById(R.id.cancelbtn);

        CreateFolderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mview = getLayoutInflater().inflate(R.layout.dialog_design, null);


               final EditText FolderName = (EditText)mview.findViewById(R.id.EditText);
                Button SaveBtn = (Button)mview.findViewById(R.id.CreateButton);
                 final File root3 = new File(Environment.getExternalStorageDirectory() , "SCANAPP");

              if(!root3.exists())
                {
                    root3.mkdir();

                }



                SaveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            File root2 = new File(root3 ,FolderName.getText().toString() );
                            if(!root2.exists())
                            {
                                root2.mkdir();
                            }


                    }


                }

                );


                mBuilder.setView(mview);

                  AlertDialog dialog = mBuilder.create();
                dialog.show();
              //


            }



        });





        init();
    }


    private void init() {
        scanButton = (Button) findViewById(R.id.scanButton);
        //scanButton.setOnClickListener(new ScanButtonClickListener());
        cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));
         mediaButton = (ImageButton) findViewById(R.id.mediaButton);
         mediaButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_MEDIA));
        scannedImageView = (ImageView) findViewById(R.id.scannedImage);
    }

    private class ScanButtonClickListener implements View.OnClickListener {

        private int preference;

        public ScanButtonClickListener(int preference) {
            this.preference = preference;
        }

        public ScanButtonClickListener() {
        }

        @Override
        public void onClick(View v) {
            startScan(preference);
        }
    }

    public void startScan(int preference) {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;
            try {


                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                getContentResolver().delete(uri, null, null);
                scannedImageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap convertByteArrayToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}

