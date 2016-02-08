package ci.felight.ci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserDetailsActivity extends AppCompatActivity {



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.i("Vidhya", "onActivityResultExecuted");

        if(requestCode==1 && resultCode==RESULT_OK){
        String name = intent.getStringExtra("name");
        Toast.makeText(getBaseContext(), "Name:" + name, Toast.LENGTH_LONG).show();
        }

            if(requestCode==2 && resultCode==RESULT_OK) {
                String company = intent.getStringExtra("companyName");
                Toast.makeText(getBaseContext(), "Company Name:" + company, Toast.LENGTH_LONG).show();
            }

    }



    public void getUserDetails(View view){

        switch (view.getId()){

            case R.id.btnPersonalInfo:
                Intent intentPeronal = new Intent(getBaseContext(),UserPersonalInformation.class);
                startActivityForResult(intentPeronal,1);
                break;
            case R.id.btnOfficialInfo:
                Intent intentOfficial = new Intent(getBaseContext(),UserOfficialInformation.class);
                startActivityForResult(intentOfficial,2);
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
