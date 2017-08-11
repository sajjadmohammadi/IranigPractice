package ir.s_mohammadi.iranigpractice.Retrofit;


import java.util.List;
import ir.s_mohammadi.iranigpractice.Pojo.NewsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sajjad
 */

public interface APIService {
    @GET("news")
    Call<List<NewsModel>> get_News(
            @Query("code") String code,
            @Query("deviceId") String deviceId,
            @Query("catNews") String catNews);
}