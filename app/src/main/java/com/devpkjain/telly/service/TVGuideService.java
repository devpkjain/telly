package com.devpkjain.telly.service;

import com.devpkjain.telly.model.CurrentTVShow;
import com.devpkjain.telly.model.CurrentTVShowResult;
import com.devpkjain.telly.service.vo.CurrentTVShowListEnvelope;
import com.devpkjain.telly.service.vo.CurrentTVShowsEnvelope;
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

  public Observable<CurrentTVShowResult> getTrendingShows() {
    return mTVGuideWebService.getTrendingShows().map(new Func1<CurrentTVShowListEnvelope, CurrentTVShowResult>() {

      @Override public CurrentTVShowResult call(final CurrentTVShowListEnvelope data) {
        CurrentTVShowResult result = new CurrentTVShowResult();
        result.getList().clear();
        for (int i = 0; i < data.showList.size(); i++) {
          CurrentTVShowsEnvelope dataItem = data.showList.get(i);
          CurrentTVShow show = new CurrentTVShow(Integer.parseInt(dataItem.num), dataItem.img, dataItem.lnk, dataItem.name, dataItem.air);
          result.getList().add(show);
        }
        return result;
      }
    });
  }

  private interface RealTVGuideService {
    @GET("/tvguide/showtrends.php") Observable<CurrentTVShowListEnvelope> getTrendingShows();

    @GET("/tvguide/searchShows.php") Observable<CurrentTVShowsEnvelope> searchShows(@Query("url") String url);

    @GET("/tvguide/getShowInfo.php") Observable<CurrentTVShowsEnvelope> getShowDetails(@Query("url") String url);
  }

  private interface RealTVScheduleService {
    @GET("/feeds/fullschedule.php") Observable<CurrentTVShowsEnvelope> getFeed(@Query("country") String name);
  }
}