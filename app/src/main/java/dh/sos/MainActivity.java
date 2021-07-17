package dh.sos;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageView = findViewById(R.id.iv);
        final SeekBar seekbar = findViewById(R.id.simpleSeekBar);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addOverlay(R.drawable.overlay, imageView, imageView.getWidth() * progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Initial overlay
        imageView.post(() -> addOverlay(R.drawable.overlay, imageView, 0));
    }

    private void addOverlay(int resourceId, ImageView imageView, int startMargin) {
        imageView.getOverlay().clear();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), resourceId, null);
        drawable.setBounds(new Rect(startMargin, 0, imageView.getWidth(), imageView.getHeight()));
        imageView.getOverlay().add(drawable);
    }
}