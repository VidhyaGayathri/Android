package ci.felight.ci;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class InstagramCloneActivity extends AppCompatActivity {


    private ImageView ivResult;
    private Button btnBlur;
    private static final int CAMERA_REQUEST =12;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap)data.getExtras().get("data");
        ivResult.setImageBitmap(photo);

    }


    public void getImageFromCamera(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(intent,CAMERA_REQUEST);
    }

     public void processImage(View view){
        switch (view.getId()){
            case R.id.btngreyScale:
                ivResult.buildDrawingCache();
                Bitmap bitmap = ivResult.getDrawingCache();
                Bitmap bitmapResult = toGrayScale(bitmap);
                ivResult.setImageBitmap(bitmapResult);
                /*ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivResult,"rotation",0,360,0,360,90,0,90,-360,0);
                objectAnimator.setDuration(3000);
                objectAnimator.start();*/


                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(btnBlur,"translationX",0,360,0,360,90,0,90,-360,0);
                objectAnimator1.setDuration(1500);
                objectAnimator1.start();


                Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.shake);
                animation.setDuration(3000);
                animation.start();
                ivResult.setAnimation(animation);

                break;

            case R.id.btnBlur:
                ivResult.buildDrawingCache();
                Bitmap bitmap1 = ivResult.getDrawingCache();
                Bitmap bitmapResult2 =toBlur(bitmap1);
                ivResult.setImageBitmap(bitmapResult2);
                break;

            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;


        }

    }

    private Bitmap toGrayScale(Bitmap bmpOriginal) {

        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }



    Bitmap toBlur (Bitmap input) {
        try {
            RenderScript rsScript = RenderScript.create(getApplicationContext());
            Allocation alloc = Allocation.createFromBitmap(rsScript, input);

            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rsScript, Element.U8_4(rsScript));
            blur.setRadius(21);
            blur.setInput(alloc);

            Bitmap result = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
            Allocation outAlloc = Allocation.createFromBitmap(rsScript, result);

            blur.forEach(outAlloc);
            outAlloc.copyTo(result);


            rsScript.destroy();
            return result;
        } catch (Exception e) {
            // TODO: handle exception
            return input;
        }
    }



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_clone_activity);
            ivResult = (ImageView)findViewById(R.id.ivResult);
            try{
            Intent incomingIntent = getIntent();
            Uri imageUri = (Uri)incomingIntent.getExtras().get(Intent.EXTRA_STREAM);
            ivResult.setImageURI(imageUri);
            }catch (NullPointerException ex){
                Log.e("Vidhya",ex.toString());
            }
            btnBlur = (Button)findViewById(R.id.btnBlur);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram_clone, menu);
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
