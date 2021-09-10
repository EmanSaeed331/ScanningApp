package com.example.lenovo.scannapp.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.example.lenovo.scannapp.CustomAdapter;
import com.example.lenovo.scannapp.PDFDoc;
import com.example.lenovo.scannapp.R;

import java.io.File;
import java.util.ArrayList;

public class FilesFragment extends Fragment {
    FrameLayout linearLayout;
    ToggleButton toggleButton;
    Activity activity;
    FloatingActionButton floatingActionButton;



    public FilesFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_files, container, false);
        linearLayout = (FrameLayout) view.findViewById(R.id.LinearL);
       // final GridView gv= (GridView) view.findViewById(R.id.gv);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);

        activity=getActivity();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gv.setAdapter(new CustomAdapter(activity,getPDFs()));
            }
        });
        return view;
    }/*
    private ArrayList<PDFDoc> getPDFs()
    {
        ArrayList<PDFDoc> pdfDocs=new ArrayList<>();
        //TARGET FOLDER
        File downloadsFolder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        PDFDoc pdfDoc;

        if(downloadsFolder.exists())
        {
            //GET ALL FILES IN DOWNLOAD FOLDER
            File[] files=downloadsFolder.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0;i<files.length;i++)
            {
                File file=files[i];

                if(file.getPath().endsWith("pdf"))
                {
                    pdfDoc=new PDFDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getAbsolutePath());

                    pdfDocs.add(pdfDoc);
                }

            }
        }

        return pdfDocs;
    }*/


}
