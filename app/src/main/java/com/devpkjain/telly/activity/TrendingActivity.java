package com.devpkjain.telly.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import com.devpkjain.telly.R;
import com.devpkjain.telly.adapters.RecyclerViewAdapter;
import com.devpkjain.telly.model.CurrentTVShow;
import com.devpkjain.telly.model.CurrentTVShowResult;
import com.devpkjain.telly.service.TVGuideService;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TrendingActivity extends BaseDrawerActivity implements RecyclerViewAdapter.OnItemClickListener {

  public static final String AVATAR_URL = "http://i.imgur.com/pk8zPBK.jpg?1";

  private static List<CurrentTVShow> items = new ArrayList<>();

  static {
    for (int i = 1; i <= 10; i++) {
      items.add(new CurrentTVShow(i, "http://lorempixel.com/500/500/business/" + i, "Item" + i, "Title" + i, "Item" + i));
    }
  }

  final TVGuideService tvGuideService = new TVGuideService();
  private DrawerLayout drawerLayout;
  private View content;
  private RecyclerView recyclerView;
  private ImageButton fab;
  private Subscription sub;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initRecyclerView();
    setTitle(getResources().getString(R.string.title_trending));
  }

  private void initRecyclerView() {
    LayoutInflater inflater = getLayoutInflater();
    View view = inflater.inflate(R.layout.trending_layout, getContentLayout(), true);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    getDetails();
  }

  public void initList(List<CurrentTVShow> list) {
    RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
    adapter.setOnItemClickListener(this);
    recyclerView.setAdapter(adapter);
  }

  private void getDetails() {
    sub = tvGuideService.getTrendingShows().retry(2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onSuccess(), onError());
  }

  private Action1<Throwable> onError() {
    return new Action1<Throwable>() {
      @Override public void call(Throwable throwable) {
        Log.d("Error", "error");
        initList(items);
      }
    };
  }

  private Action1<CurrentTVShowResult> onSuccess() {
    return new Action1<CurrentTVShowResult>() {
      @Override public void call(CurrentTVShowResult result) {
        if (result != null && result.getList().size() > 0) {
          initList(result.getList());
        }
      }
    };
  }
}
