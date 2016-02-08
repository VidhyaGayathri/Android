package ci.felight.ci;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 2/1/2016.[
 */
public class Calculator extends Activity{
    TextView tvResult;
    EditText etSNum;
    EditText etFNum;
    private static final String TAG = "SimpleCalculator";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
         etFNum = (EditText)findViewById(R.id.etFirstNumber);
         etSNum = (EditText)findViewById(R.id.etSecondNumber);
         tvResult = (TextView)findViewById(R.id.tv);
    }

    public void doCalculation(View view){
        double num1=0;
        double num2=0;
      try {
         num1 = Double.parseDouble(etFNum.getText().toString());
      } catch (NumberFormatException ex ){
          tvResult.setText("Please enter  the first number");
          return;
      }
        try {
            num2 = Double.parseDouble(etSNum.getText().toString());
        }catch (NumberFormatException ex ){
            tvResult.setText("Please enter  the second number");
            return;
        }
        Log.d(TAG, "inside method ");
        switch (view.getId()){
            case R.id.btnAdd:
               tvResult.setText("Result:"+(num1+num2));
                break;
            case R.id.btnSub:
                tvResult.setText("Result:"+(num1-num2));
                break;
            case R.id.btnMul:
                tvResult.setText("Result:"+(num1*num2));
                break;
            case R.id.btnDiv:

                tvResult.setText("Result:"+(num1/num2));


              break;

        }
    }
}
