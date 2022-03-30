package com.smartlens.fragments;

import static android.Manifest.permission.CAMERA;
import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.smartlens.R;

import java.util.Locale;


public class WordSearchFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView placeholderImage;
    private TextView resultsTextView, actionBarTv;
    private MaterialButton fetchResultBtn;
    //    private Chip translateChip;
    private Chip copyChip, shareChip, listenChip;
    private ChipGroup translateChipGroup;
    private Bitmap bitmap;
    TextToSpeech tts;
    final Handler handler = new Handler();

    public WordSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_word_search, container, false);

        resultsTextView = root.findViewById(R.id.results_btn);
        placeholderImage = root.findViewById(R.id.placeholder_image);
        Button photoBtn = root.findViewById(R.id.photo_btn);
        fetchResultBtn = root.findViewById(R.id.fetch_resultsbtn);
//        translateChip = root.findViewById(R.id.translate_chip);
        copyChip = root.findViewById(R.id.copy_chip);
        shareChip = root.findViewById(R.id.share_chip);
        listenChip = root.findViewById(R.id.listen_chip);
        translateChipGroup = root.findViewById(R.id.translate_chip_grp);

        actionBarTv = root.findViewById(R.id.activity_name);
        actionBarTv.setText("Word Search");

        int[] placeHolderImages = {R.drawable.word_search, R.drawable.word_search1};
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

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissions()) {
                    clickImage();
                } else {
                    requestPermissions();
                }
            }
        });

        fetchResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchResults();
            }
        });

//        translateChip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//             Intent translateIntent = new Intent(getContext(), WordSearchActivityResult.class);
//             startActivity(translateIntent);
//            }
//        });

        copyChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataCopyOrShareListen = resultsTextView.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextCopied", dataCopyOrShareListen);
                clipboard.setPrimaryClip(clipData);

                Toast.makeText(getContext(), "Copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });
        shareChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataCopyOrShareListen = resultsTextView.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, dataCopyOrShareListen);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        listenChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //apply text to speech on tts
                tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if (i == TextToSpeech.SUCCESS) {
                            tts.setLanguage(Locale.US);
                            //change the text to speech voice to male voice

                            String text = resultsTextView.getText().toString();
                            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        } else {
                            Toast.makeText(getContext(), " Text to Speech Initialization failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


        return root;
    }

    private boolean checkPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), CAMERA);
        return cameraPermission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean cameraPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (cameraPermission) {
                Toast.makeText(getActivity(), "Permission Granted!", Toast.LENGTH_SHORT).show();
                clickImage();
            } else {
                Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clickImage() {
        resultsTextView.setText("");
        Intent takeImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takeImage.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takeImage, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            handler.removeCallbacksAndMessages(null);
            placeholderImage.setImageBitmap(bitmap);
            fetchResultBtn.setVisibility(View.VISIBLE);
        }
    }

    private void requestPermissions() {
        int PERMISSION_CODE = 200;
        requestPermissions(new String[]{
                CAMERA
        }, PERMISSION_CODE);
    }

    private void fetchResults() {
//placeholderImage.getBackground();
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();

        if (!textRecognizer.isOperational()) {
//            Log.i("LiveWordSearchFragment", "startCamera: Dependencies not loaded yet");
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                TextBlock myItem = items.valueAt(i);
                stringBuilder.append(myItem.getValue());
                stringBuilder.append("\n");
            }
            String result = stringBuilder.toString();
            resultsTextView.setText(result);
            if (resultsTextView.getVisibility() == View.VISIBLE && result.length() >= 1) {
                translateChipGroup.setVisibility(View.VISIBLE);
            }
        }
    }
}