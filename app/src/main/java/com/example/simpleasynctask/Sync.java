package com.example.simpleasynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Sync  extends AsyncTask<Void,Integer,String> {
    Context context;
    Button button;
    TextView textView;
    ProgressBar progressBar;
    Sync(Context context, TextView textView, Button button){
        this.context = context;
        this.button = button;
        this.textView = textView;
    }



    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "Sleep";
    }

    @Override
    protected void onPreExecute() {
        progressBar = new ProgressBar(context);
        //progressBar.setTitle("Working...");
        progressBar.setMax(100);
        progressBar.setProgress(0);
        //progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressBar.setProgress(progress);
        textView.setText("Working...");
    }


}

