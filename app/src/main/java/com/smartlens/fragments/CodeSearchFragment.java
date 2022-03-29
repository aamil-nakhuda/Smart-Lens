package com.smartlens.fragments;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.button.MaterialButton;
import com.smartlens.R;


public class CodeSearchFragment extends Fragment {

    private static final int ASK_PERMISSION = 1;
    private ImageView placeholderImage;
    CardView cardView, cCV, cppCV, csCV, javaCV, pyCV, rubyCV, swiftCV, dartCV, jsCV, tsCV, vsCV, asCV, intellijCV, pcharmCV, ecliCV, jupCV;
    SurfaceView surfaceView;
    private CameraSource cameraSource;
    private TextView resultsTextView, actionBarTv;
    private MaterialButton copyResultBtn;

    final Handler handler = new Handler();

    public CodeSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_code_search, container, false);

        cardView = root.findViewById(R.id.card_view);
        surfaceView = root.findViewById(R.id.surface_view);
        resultsTextView = root.findViewById(R.id.results_code);
        placeholderImage = root.findViewById(R.id.placeholder_image_code);
        Button startScan = root.findViewById(R.id.start_scan_code);
        copyResultBtn = root.findViewById(R.id.copy_code);


        cCV = root.findViewById(R.id.c_CV);
        cppCV = root.findViewById(R.id.cpp_CV);
        csCV = root.findViewById(R.id.cs_CV);
        javaCV = root.findViewById(R.id.java_CV);
        pyCV = root.findViewById(R.id.py_CV);
        rubyCV = root.findViewById(R.id.ruby_CV);
        swiftCV = root.findViewById(R.id.swift_CV);
        dartCV = root.findViewById(R.id.dart_CV);
        jsCV = root.findViewById(R.id.js_CV);
        tsCV = root.findViewById(R.id.ts_CV);

        vsCV = root.findViewById(R.id.vscode_CV);
        asCV = root.findViewById(R.id.as_CV);
        intellijCV = root.findViewById(R.id.intellij_CV);
        pcharmCV = root.findViewById(R.id.pycharm_CV);
        ecliCV = root.findViewById(R.id.eclipse_CV);
        jupCV = root.findViewById(R.id.jupyter_CV);

        actionBarTv = root.findViewById(R.id.activity_name);
        actionBarTv.setText("Code Search");

        int[] placeHolderImages = {R.drawable.code_search, R.drawable.code_search1, R.drawable.code_search2};
        Runnable runnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                placeholderImage.setImageResource(placeHolderImages[i]);
                i++;
                if (i >= placeHolderImages.length) {
                    i = 0;
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 0);

        startScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (startScan.getText().toString().equals("Stop Scan")) {
                    placeholderImage.setVisibility(View.VISIBLE);
                    copyResultBtn.setEnabled(false);
                    cardView.setVisibility(View.GONE);
                    surfaceView.setVisibility(View.GONE);
                    cameraSource.stop();
                    startScan.setText("Start Scan");
                } else {
                    placeholderImage.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                    surfaceView.setVisibility(View.VISIBLE);
                    try {
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, ASK_PERMISSION);
                            return;
                        }
                        cameraSource.start(surfaceView.getHolder());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    startCamera();
                    copyResultBtn.setEnabled(true);
                    startScan.setText("Stop Scan");
                }
            }
        });

        copyResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataCopyOrShareListen = resultsTextView.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextCopied", dataCopyOrShareListen);
                clipboard.setPrimaryClip(clipData);

                Toast.makeText(getContext(), "Copied to Clipboard", Toast.LENGTH_SHORT).show();

                resultsTextView.setText(dataCopyOrShareListen);
            }
        });

        //Set onClickListener for each cardView
        cCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/c-online-compiler/");

            }
        });

        //Copy the same onClickListener for each cardView
        cppCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/online-compiler-c++/");

            }
        });

        csCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/compile-c-sharp-online/");
            }
        });

        javaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/online-java-compiler/");
            }
        });

        pyCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/python3-programming-online/");
            }
        });

        rubyCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/execute-ruby-online/");
            }
        });

        swiftCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/execute-swift-online/");
            }
        });

        dartCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/execute-dart-online/");
            }
        });

        jsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/html-css-javascript-online-editor/");
            }
        });

        tsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://www.jdoodle.com/compile-scala-online/");
            }
        });

        vsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://vscode.dev/");
            }
        });

        asCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://neverinstall.com/vapp?appName=Android%20Studio&id=671160");
            }
        });

        intellijCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://neverinstall.com/vapp?appName=IntelliJ&id=671164");
            }
        });

        ecliCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://neverinstall.com/vapp?appName=Eclipse&id=671165");
            }
        });

        pcharmCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://neverinstall.com/vapp?appName=PyCharm&id=671172");
            }
        });

        jupCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                browserIntent("https://neverinstall.com/vapp?appName=Jupyter&id=671175");
            }
        });

        return root;
    }

    private void browserIntent(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    //make a method startCamera
    private void startCamera() {
        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();

        if (!textRecognizer.isOperational()) {
//            Log.i("LiveWordSearchFragment", "startCamera: Dependencies not loaded yet");
        } else {
            cameraSource = new CameraSource.Builder(getContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK).setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(2.0f).build();

            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, ASK_PERMISSION);
                            return;
                        }
                        cameraSource.start(surfaceView.getHolder());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                    //Not required but we still need to override to release the resources
                }

                @Override
                public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }

            });
        }

        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {
                //d
            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {
                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if (items.size() != 0) {
                    resultsTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();

                            for (int i = 0; i < items.size(); i++) {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }
                            resultsTextView.setText(stringBuilder.toString());
                            if (resultsTextView.getText().toString().length() >= 1) {
                                resultsTextView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        });

    }
}