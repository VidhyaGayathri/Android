package ci.felight.ci;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 2/2/2016.
 */
public class ActivityLifeCycleActivity extends Activity{

    private final String TAG = "lifecycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate Executed");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitylifecycle);
        TextView tvResult = (TextView)findViewById(R.id.tv);

    }

    @Override
    protected void onStart() {
        Log.i(TAG,"onStart Executed");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume Executed");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause Executed");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"onStop Executed");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy Executed");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG,"onRestart Executed");
        super.onRestart();
    }
}
