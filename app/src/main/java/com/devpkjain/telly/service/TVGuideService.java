package com.devpkjain.telly.service;

import com.devpkjain.telly.model.CurrentTVShow;
import com.devpkjain.telly.service.vo.CurrentTVShowEnvelope;
import java.util.ArrayList;
import java.util.List;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;
import rx.functions.Func1;

public class TVGuideService {
  public static final String URL_BASE = "http://guilmo.com";
  public static final String URL_FEED = "http://services.tvrage.com";

  private final RealTVGuideService mTVGuideWebService;
  private final RealTVScheduleService mTVScheduleWebService;

  public TVGuideService() {
    RequestInterceptor requestInterceptor = new RequestInterceptor() {
      @Override public void intercept(RequestInterceptor.RequestFacade request) {
        request.addHeader("Accept", "application/json");
      }
    };

    mTVGuideWebService = new RestAdapter.Builder().setEndpoint(URL_BASE)
        .setRequestInterceptor(requestInterceptor)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build()
        .create(RealTVGuideService.class);
    mTVScheduleWebService = new RestAdapter.Builder().setEndpoint(URL_FEED)
        .setRequestInterceptor(requestInterceptor)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .build()
        .create(RealTVScheduleService.class);
  }

  public Observable<List<CurrentTVShow>> getTrendingShows() {
    return mTVGuideWebService.getTrendingShows().map(new Func1<List<CurrentTVShowEnvelope>, List<CurrentTVShow>>() {

      @Override public List<CurrentTVShow> call(final List<CurrentTVShowEnvelope> data) {
        List<CurrentTVShow> result = new ArrayList<CurrentTVShow>();
        for (int i = 0; i < data.size(); i++) {
          CurrentTVShowEnvelope dataItem = data.get(i);
          result.add(new CurrentTVShow(dataItem.num, dataItem.img, dataItem.lnk, dataItem.name, dataItem.air));
        }
        return result;
      }
    });
  }

  private interface RealTVGuideService {
    @GET("/tvguide/showtrends.php") Observable<List<CurrentTVShowEnvelope>> getTrendingShows();

    @GET("/tvguide/searchShows.php") Observable<CurrentTVShowEnvelope> searchShows(@Query("url") String url);

    @GET("/tvguide/getShowInfo.php") Observable<CurrentTVShowEnvelope> getShowDetails(@Query("url") String url);
  }

  private interface RealTVScheduleService {
    @GET("/feeds/fullschedule.php") Observable<CurrentTVShowEnvelope> getFeed(@Query("country") String name);
  }
}