package com.example.a06_asynctasktest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progress_bar);

        new CounterTask().execute(0);
    }

    class CounterTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {      }

        protected Integer doInBackground(Integer... value) {
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                }
                mProgressStatus++;
                publishProgress(mProgressStatus);
            }
            return mProgressStatus;
        }

        protected void onProgressUpdate(Integer... value) {
            mProgress.setProgress(mProgressStatus);
        }

        protected void onPostExecute(Integer result) {
            mProgress.setProgress(mProgressStatus);
        }
    }
}