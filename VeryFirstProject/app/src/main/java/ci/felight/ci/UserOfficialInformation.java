package ci.felight.ci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class UserOfficialInformation extends AppCompatActivity {

    EditText etCompanyName;


    public void saveUserDetails(View view){

        String companyName = etCompanyName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("companyName", companyName);
        setResult(RESULT_OK, intent);

        finish();

    }

    public void cancelUserDetails(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_official_information_activity);
        etCompanyName = (EditText)findViewById(R.id.etCompanyName);
    }


}
