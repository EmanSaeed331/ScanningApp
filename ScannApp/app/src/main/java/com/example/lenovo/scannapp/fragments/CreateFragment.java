package com.example.lenovo.scannapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.lenovo.scannapp.R;
import com.example.lenovo.scannapp.activities.MainActivity;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.IOException;


public class CreateFragment extends Fragment {

     public ImageButton cameraButton;
     Button mediaButton;
     Activity context;
     ImageView scannedImageView;
    private static final int REQUEST_CODE = 99;



    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create2, container, false);
        context=getActivity();

        cameraButton = (ImageButton) v.findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));
       // mediaButton = (ImageButton) v.findViewById(R.id.mediaButton);
       // mediaButton.setOnClickListener(new CreateFragment().ScanButtonClickListener(ScanConstants.OPEN_MEDIA));
       // scannedImageView = (ImageView)v.findViewById(R.id.iV);

        return v;

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
        Intent intent = new Intent(context, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);

              //  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
               context.getContentResolver().delete(uri, null, null);
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
