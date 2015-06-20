package com.devpkjain.telly.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.devpkjain.telly.R;
import com.devpkjain.telly.adapters.RecyclerViewAdapter;
import com.devpkjain.telly.model.CurrentTVShow;
import com.devpkjain.telly.utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class BaseDrawerActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

  public static final String AVATAR_URL = "http://i.imgur.com/pk8zPBK.jpg?1";
  Toolbar toolbar;
  private DrawerLayout drawerLayout;
  private ViewGroup contentLayout;

  public Toolbar getToolbar() {
    return toolbar;
  }

  public ViewGroup getContentLayout() {
    return contentLayout;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initToolbar();
    setupDrawerLayout();

    contentLayout = (ViewGroup) findViewById(R.id.contentLayout);

    final ImageView avatar = (ImageView) findViewById(R.id.avatar);
    Picasso.with(this).load(AVATAR_URL).transform(new CircleTransform()).into(avatar);
  }

  private void initToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    final ActionBar actionBar = getSupportActionBar();

    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(R.drawable.toolbar_close_button);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  private void setupDrawerLayout() {
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
    view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override public boolean onNavigationItemSelected(MenuItem menuItem) {
        Snackbar.make(contentLayout, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        return true;
      }
    });
    View trending = view.findViewById(R.id.menu_trending);
    trending.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Snackbar.make(contentLayout, "Trending" + " pressed", Snackbar.LENGTH_LONG).show();

        drawerLayout.closeDrawers();
      }
    });
    View full = view.findViewById(R.id.menu_full);
    full.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Snackbar.make(contentLayout, "Full Schedule" + " pressed", Snackbar.LENGTH_LONG).show();

        drawerLayout.closeDrawers();
      }
    });
    View search = view.findViewById(R.id.menu_search);
    search.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Snackbar.make(contentLayout, "Search" + " pressed", Snackbar.LENGTH_LONG).show();

        drawerLayout.closeDrawers();
      }
    });
    View favorite = view.findViewById(R.id.menu_favorite);
    favorite.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Snackbar.make(contentLayout, "Favorite" + " pressed", Snackbar.LENGTH_LONG).show();

        drawerLayout.closeDrawers();
      }
    });
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onItemClick(View view, CurrentTVShow show) {
    startActivity(DetailActivity.getDetailsActivityIntent(this, show));
  }
}
