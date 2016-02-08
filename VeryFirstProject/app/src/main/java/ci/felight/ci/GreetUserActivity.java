package ci.felight.ci;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 2/1/2016.
 */
public class GreetUserActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.greetuser_layout);
    }

    public void greetUser(View view){
        EditText etUserName = (EditText)findViewById(R.id.etName);
        TextView tvResult = (TextView)findViewById(R.id.tv1);
        TextView tvResult2 = (TextView)findViewById(R.id.tv2);
        tvResult.setText("Will Greet User");
        tvResult2.setTextColor(Color.RED);
        tvResult2.setText("Namaste"+etUserName.getText());

    }
}
