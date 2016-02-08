package ci.felight.ci;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_activity_layout);


    }


    public void newNavigator(View view){


        switch (view.getId()){

            case R.id.btnCamera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intentCamera);
                break;

            case R.id.btnBrowser:
                Uri uri = Uri.parse("http://www.felight.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.btnSms:
                try {

                    Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.putExtra("sms_body", "default content");
                    sendIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(sendIntent);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again later!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;

            case R.id.btnMusicPlayer:
                Intent intentMusicplayer = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(intentMusicplayer);
                break;

            case R.id.btnCall:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("914987654"));
                startActivity(callIntent);
                break;
            case R.id.btnDialer:
                Intent intentDial = new Intent(Intent.ACTION_DIAL);
                startActivity(intentDial);
                break;

            case R.id.btnToast:
                Toast t = Toast.makeText(getApplication(),"TOAST",Toast.LENGTH_LONG);
                t.setGravity(Gravity.TOP,0,0);
                t.show();
                break;


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
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
