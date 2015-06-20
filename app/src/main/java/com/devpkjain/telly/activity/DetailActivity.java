package com.devpkjain.telly.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.devpkjain.telly.R;
import com.devpkjain.telly.model.CurrentTVShow;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

  private static final String EXTRA_IMAGE = "com.devpkjain.telly.extraImage";
  private static final String EXTRA_TITLE = "com.devpkjain.telly.extraTitle";
  private Toolbar toolbar;

  public static Intent getDetailsActivityIntent(Context context, CurrentTVShow show) {
    Intent intent = new Intent(context, DetailActivity.class);
    intent.putExtra(EXTRA_IMAGE, show.getImg());
    intent.putExtra(EXTRA_TITLE, show.getName());
    return intent;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setTitle(getResources().getString(R.string.title_shows));
    setContentView(R.layout.activity_detail);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
    setTitle(itemTitle);

    String imageName = getIntent().getStringExtra(EXTRA_IMAGE);
    ImageView imageView = (ImageView) findViewById(R.id.image);
    Picasso.with(imageView.getContext()).load(imageName).into(imageView);

    TextView title = (TextView) findViewById(R.id.title);
    title.setText(itemTitle);

    TextView text = (TextView) findViewById(R.id.text);
    text.setText(itemTitle);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {

    }

    return super.onOptionsItemSelected(item);
  }
}
