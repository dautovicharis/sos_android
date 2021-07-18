package dh.sos;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView2 = findViewById(R.id.imgView2);
        final SeekBar seekbar = findViewById(R.id.simpleSeekBar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView2.setImageBitmap(getPart(imageView2, progress, R.drawable.nature2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        imageView2.post(new Runnable() {
            @Override
            public void run() {
                imageView2.setImageBitmap(getPart(imageView2, 50, R.drawable.nature2));
            }
        });
    }

    private Bitmap getPart(ImageView imageView, int progress, int resource) {
        imageView.invalidate();
        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(resource);
        Bitmap bitmap = drawable.getBitmap();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        int dynamicWidth = (int) (width * progress) / 100;
        if (dynamicWidth > 0) {
            return Bitmap.createBitmap(bitmap, 0, 0, dynamicWidth, bitmap.getHeight());
        }
        return null;
    }
}