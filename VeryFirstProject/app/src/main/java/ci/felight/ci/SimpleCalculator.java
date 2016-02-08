package ci.felight.ci;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 1/29/2016.
 */
public class SimpleCalculator extends Activity{

    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplecalculator);
        tvResult = (TextView)findViewById(R.id.tvResult);
    }

    public void doSomethingAdd(View view)
    {
        tvResult.setText("Add is not implemented yet");
    }

    public void doSomethingSub(View view){
        tvResult.setText("Subtraction is not yet implemented");
    }

    public void doSomethingMul(View view){
        tvResult.setText("Multiplication is not yet implemented");
    }

    public void doSomethingDiv(View view){
        tvResult.setText("Division is not implemented yet");
    }
}
