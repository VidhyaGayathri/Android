package ci.felight.ci;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class ActivityNavigator extends AppCompatActivity {

    public static final String NEWS_TYPE = "newstype";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

    }

    public void navigator(View view){
        switch(view.getId()){


            case R.id.btnPackage:
                Intent intentPackage = new Intent(getBaseContext(),NewActivity.class);
                startActivity(intentPackage);
                break;

            case R.id.btnGoogleNews:
                Intent intentGoogle = new Intent(getBaseContext(),GoogleNewsPage.class);
                intentGoogle.putExtra(NEWS_TYPE,"Google");
                startActivity(intentGoogle);
                break;

            case R.id.btnFelightNews:
                Intent intentFelight = new Intent(getBaseContext(),GoogleNewsPage.class);
                intentFelight.putExtra("newstype","Felight");
                startActivity(intentFelight);
                break;

            case R.id.btnGreetUser:
                Intent intentGreetUser = new Intent(this,GreetUserActivity.class);
                startActivity(intentGreetUser);
                break;

            case R.id.btnLaunchCalci:
                Intent intenetCalci = new Intent(this,Calculator.class);
                startActivity(intenetCalci);
                break;

            case R.id.btnActivityLifeCycle:
                Intent intentLifeCycle = new Intent(this,ActivityLifeCycleActivity.class);
                startActivity(intentLifeCycle);
                break;

            case R.id.btnUserRegisteration:
                Intent intentUserRegistration = new Intent(getBaseContext(),UserRegisteration.class);
                startActivity(intentUserRegistration);
                break;

            case R.id.btnInsta:
                Intent intentInsta = new Intent(getBaseContext(),InstagramCloneActivity.class);
                startActivity(intentInsta);
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_navigator, menu);
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
