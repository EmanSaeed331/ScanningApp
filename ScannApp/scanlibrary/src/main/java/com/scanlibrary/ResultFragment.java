package com.scanlibrary;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ResultFragment extends Fragment {

    private View view;
    private ImageView scannedImageView;
    private Button doneButton;
    private Bitmap original;
    private Button originalButton;
    private Button MagicColorButton;
    private Button grayModeButton;
    private Button bwButton;
    private Bitmap transformed;
    private static ProgressDialogFragment progressDialogFragment;

    public ResultFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.result_layout, null);
        init();
        return view;
    }

    private void init() {
        scannedImageView = (ImageView) view.findViewById(R.id.scannedImage);
        originalButton = (Button) view.findViewById(R.id.original);
        originalButton.setOnClickListener(new OriginalButtonClickListener());
        MagicColorButton = (Button) view.findViewById(R.id.magicColor);
        MagicColorButton.setOnClickListener(new MagicColorButtonClickListener());
        grayModeButton = (Button) view.findViewById(R.id.grayMode);
        grayModeButton.setOnClickListener(new GrayButtonClickListener());
        bwButton = (Button) view.findViewById(R.id.BWMode);
        bwButton.setOnClickListener(new BWButtonClickListener());
        Bitmap bitmap = getBitmap();
        setScannedImage(bitmap);
        doneButton = (Button) view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new DoneButtonClickListener());
    }

    private Bitmap getBitmap() {
        Uri uri = getUri();
        try {
            original = Utils.getBitmap(getActivity(), uri);
            getActivity().getContentResolver().delete(uri, null, null);
            return original;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Uri getUri() {
        Uri uri = getArguments().getParcelable(ScanConstants.SCANNED_RESULT);
        return uri;
    }

    public void setScannedImage(Bitmap scannedImage) {
        scannedImageView.setImageBitmap(scannedImage);
    }





   
    private class DoneButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showProgressDialog(getResources().getString(R.string.loading));
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                AsyncTask.execute(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void run() {
                        try {
                            Intent data = new Intent();
                            Bitmap bitmap = transformed;
                            //SHOWING IMAGES IN JPEG FORM AND SAVING IT IN PHOTO FOLDER

                            File root1 = new File(Environment.getExternalStorageDirectory() , "PHOTO");
                            if(!root1.exists()){

                                root1.mkdir();
                            }


                            String Time = new SimpleDateFormat("yyyyMMdd_HHmmss"  ).format(new
                                    Date());

                            File r = new File(root1 , "IMAGE__"+Time + ".jpg");
                            FileOutputStream fileOutputStream2 = new FileOutputStream(r);


                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , fileOutputStream2);


                            if (bitmap == null) {
                                bitmap = original;
                            }





/*

                            PdfDocument pdfDocument = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                pdfDocument = new PdfDocument();
                            }
                            PdfDocument.PageInfo pi = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                pi = new PdfDocument.PageInfo.Builder(bitmap.getWidth() , bitmap.getHeight() ,1).create();
                            }
                            PdfDocument.Page page = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                page = pdfDocument.startPage(pi);
                            }
                            Canvas canvas = null;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                canvas = page.getCanvas();
                            }
                            Paint paint = new Paint();
                            paint.setColor(Color.parseColor("#ffffff"));
                            canvas.drawPaint(paint);

                            bitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth() ,bitmap.getHeight(),true);
                            paint.setColor(Color.BLUE);
                            canvas.drawBitmap(bitmap, 0 , 0, null);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                pdfDocument.finishPage(page);
                            }

                            File root = new File(Environment.getExternalStorageDirectory() , "SCANAPP");
                            if(!root.exists()){

                                root.mkdir();
                            }


                            try{



                                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss"  ).format(new
                                        Date());


                                File file = new File(root , "IMG_" + timeStamp + ".pdf");
                                //to make a captured image as jpg
                            //    File file = new File(root , "IMG_" + timeStamp + ".jpg");





                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    pdfDocument.writeTo(fileOutputStream);
                                }

                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                pdfDocument.close();
                            }

*/


                             //for showing the transformed image in main activity
                            /*

                                Uri uri = Utils.getUri(getActivity(), bitmap);
                            data.putExtra(ScanConstants.SCANNED_RESULT, uri);
                            getActivity().setResult(Activity.RESULT_OK, data);
                            */






                            original.recycle();
                            System.gc();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dismissDialog();
                                    getActivity().finish();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private class BWButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        transformed = ((ScanActivity) getActivity()).getBWBitmap(original);
                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                                scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scannedImageView.setImageBitmap(transformed);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }

    private class MagicColorButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        transformed = ((ScanActivity) getActivity()).getMagicColorBitmap(original);
                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                                scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scannedImageView.setImageBitmap(transformed);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }

    private class OriginalButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                showProgressDialog(getResources().getString(R.string.applying_filter));
                transformed = original;
                scannedImageView.setImageBitmap(original);
                dismissDialog();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                dismissDialog();
            }
        }
    }

    private class GrayButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            showProgressDialog(getResources().getString(R.string.applying_filter));
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        transformed = ((ScanActivity) getActivity()).getGrayBitmap(original);
                    } catch (final OutOfMemoryError e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                transformed = original;
                                scannedImageView.setImageBitmap(original);
                                e.printStackTrace();
                                dismissDialog();
                                onClick(v);
                            }
                        });
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            scannedImageView.setImageBitmap(transformed);
                            dismissDialog();
                        }
                    });
                }
            });
        }
    }

    protected synchronized void showProgressDialog(String message) {
        if (progressDialogFragment != null && progressDialogFragment.isVisible()) {
            // Before creating another loading dialog, close all opened loading dialogs (if any)
            progressDialogFragment.dismissAllowingStateLoss();
        }
        progressDialogFragment = null;
        progressDialogFragment = new ProgressDialogFragment(message);
        FragmentManager fm = getFragmentManager();
        progressDialogFragment.show(fm, ProgressDialogFragment.class.toString());
    }

    protected synchronized void dismissDialog() {
        progressDialogFragment.dismissAllowingStateLoss();
    }
}