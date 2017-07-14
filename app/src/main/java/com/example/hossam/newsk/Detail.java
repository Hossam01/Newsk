package com.example.hossam.newsk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {
    Listitem item;
    ImageView image;
    TextView titel, subtitle, body;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        image = (ImageView) findViewById(R.id.photo);
        titel = (TextView) findViewById(R.id.Title);
        subtitle = (TextView) findViewById(R.id.subtitle);
        body = (TextView) findViewById(R.id.article_body);
        Bundle be = getIntent().getExtras();
        if (be != null) {
            item = (Listitem) be.getSerializable(getString(R.string.path));
            Glide.with(this).load(item.getUrlToImage()).into(image);
            titel.setText(item.getTitle());
            subtitle.setText(item.getPublishedAt() + getString(R.string.By) + item.getAuthor());
            body.setText(item.getDescription());

        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(Detail.this)
                        .setType("text/plain")
                        .setText(item.getUrl())
                        .getIntent(), getString(R.string.Share)));
            }
        });

    }
}
