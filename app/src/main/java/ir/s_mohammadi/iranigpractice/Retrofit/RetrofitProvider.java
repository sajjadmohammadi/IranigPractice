package ir.s_mohammadi.iranigpractice.Retrofit;



import ir.s_mohammadi.iranigpractice.Utilities.G;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sajjad on 10/14/2016.
 */
public class RetrofitProvider {

    APIService apiService;

    public RetrofitProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(G.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public APIService getAPIService() {
        return apiService;
    }
}
