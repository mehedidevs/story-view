package com.example.my_stories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.my_stories.pview.CompletedSegmentListener;
import com.example.my_stories.pview.SegmentedProgressBar;

public class MainActivitySegmented extends AppCompatActivity {

    SegmentedProgressBar segmentedProgressBar;
    Button startButton;
    Button startWithoutAnimationButton;
    Button pauseResumeButton;
    Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segmented_activity_main);

        initSegmentedProgressBar();

        startButton = findViewById(R.id.button);
        pauseResumeButton = findViewById(R.id.button3);
        resetButton = findViewById(R.id.button2);
        startWithoutAnimationButton = findViewById(R.id.button4);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segmentedProgressBar.playSegment(3000);
            }
        });

        pauseResumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (segmentedProgressBar.isPaused()) {
                    segmentedProgressBar.resume();
                } else {
                    segmentedProgressBar.pause();
                }
            }
        });
        startWithoutAnimationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segmentedProgressBar.incrementCompletedSegments();
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segmentedProgressBar.reset();
            }
        });
    }

    private void initSegmentedProgressBar() {
        segmentedProgressBar = findViewById(R.id.segmented_progressbar);

        //set filled segments directly
       // segmentedProgressBar.setCompletedSegments(1);

        segmentedProgressBar.setCompletedSegmentListener(new CompletedSegmentListener() {
            @Override
            public void onSegmentCompleted(int segmentCount) {
                Toast.makeText(MainActivitySegmented.this, "Completed segment" + segmentCount, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
