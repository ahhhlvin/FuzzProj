package ahhhlvin.c4q.nyc.fuzzproj;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        ImageView bigView = (ImageView) findViewById(R.id.big_image);

        Intent prevIntent = getIntent();
        Picasso.with(this).load(prevIntent.getStringExtra("image")).into(bigView);


    }


}
