package com.example.lenovo.android162;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    Button buttonStart;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4;
    MyTask mytask1, mytask2, mytask3, mytask4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting up UI components
        progressBar1 = (ProgressBar) findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar) findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar) findViewById(R.id.progressbar4);
        buttonStart=(Button)findViewById(R.id.button);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating object of myTask
                mytask1 = new MyTask(progressBar1);
                //passing parameter mytask in  StartAsyncTaskInParallel
                StartAsyncTaskInParallel(mytask1);
                mytask2 = new MyTask(progressBar2);
                StartAsyncTaskInParallel(mytask2);
                mytask3 = new MyTask(progressBar3);
                StartAsyncTaskInParallel(mytask3);
                mytask4 = new MyTask(progressBar4);
                StartAsyncTaskInParallel(mytask4);

            }
        });

    }
    //method StartAsyncTaskInParallel
    private void StartAsyncTaskInParallel(MyTask task) {
        //Applying condition for asyncTask
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else
            task.execute();
    }
    //creating method MyTask()
    public class MyTask extends AsyncTask<Void, Integer, Void> {
        ProgressBar mprogressbar;
        //creating constructor
        public MyTask(ProgressBar mprogressbar) {
            this.mprogressbar = mprogressbar;
        }

        @Override
        //Creating method doInBackground()
        protected Void doInBackground(Void... voids) {
            //Applying loop for progressbar
            for(int i=0; i<100; i++){
                try {
                    //applying sleep for some milliSecond
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //publishing progress
                publishProgress(i);
            }

                return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            //setting progress of Mytask
            mprogressbar.setProgress(progress);
            super.onProgressUpdate(values[0]);
        }
    }
}
