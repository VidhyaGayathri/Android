package ci.felight.ci;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 1/29/2016.
 */
public class SecondActivity extends Activity{

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_layout);
        tvResult =(TextView)findViewById(R.id.tvResult);
        tvResult.setText("This is second activity");
        tvResult.setTextColor(Color.GREEN);
    }
}
