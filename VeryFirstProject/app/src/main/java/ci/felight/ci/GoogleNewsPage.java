package ci.felight.ci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Vidhya Gayathri Raja on 2/3/2016.
 */
public class GoogleNewsPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_new_page_layout);
        Intent intent = getIntent();
        String newstype = intent.getStringExtra(ActivityNavigator.NEWS_TYPE);
        TextView tvResult = (TextView)findViewById(R.id.tvResult);
        tvResult.setText(newstype+" news will be displayed");

    }


}
