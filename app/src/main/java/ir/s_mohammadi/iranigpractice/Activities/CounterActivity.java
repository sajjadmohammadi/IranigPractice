package ir.s_mohammadi.iranigpractice.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.s_mohammadi.iranigpractice.Adapters.NewsAdapter;
import ir.s_mohammadi.iranigpractice.Pojo.NewsModel;
import ir.s_mohammadi.iranigpractice.R;
import ir.s_mohammadi.iranigpractice.Retrofit.APIService;
import ir.s_mohammadi.iranigpractice.Retrofit.RetrofitProvider;
import ir.s_mohammadi.iranigpractice.Utilities.G;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sajjad on 8/9/2017.
 */
public class CounterActivity extends Master {
    private ImageView iv_refresh;
    private RelativeLayout rl_loading;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private RetrofitProvider retrofitProvider;
    private APIService apiService;
    private List<NewsModel> newsModelList = new ArrayList<>();
    private ImageView iv_wifi;
    private LinearLayoutManager llm;
    private RecyclerView rv;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        initToolbar();
        initDrawer();
        initLayout();
        getDataFromServer();
    }


    private void initLayout() {
        rv = findView(R.id.rv);
        iv_refresh = findView(R.id.iv_refresh);
        iv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromServer();
            }
        });

        rl_loading = findView(R.id.rl_loading);
        coordinatorLayout = findView(R.id.coordinatorLayout);
        iv_wifi = findView(R.id.iv_wifi);
    }

    private void getDataFromServer() {
        if (!G.NetAvailable()) {
            showSnackBar("خطا در اتصال به اینترنت");
            rl_loading.setVisibility(View.GONE);
            iv_wifi.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        } else {
            iv_wifi.setVisibility(View.GONE);
            rl_loading.setVisibility(View.VISIBLE);
            rv.setVisibility(View.VISIBLE);
            //Un-clicking  and lock the screen
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            retrofitProvider = new RetrofitProvider();
            apiService = retrofitProvider.getAPIService();
            Call<List<NewsModel>> call = apiService.get_News(G.code, G.device_id, SettingActivity.selected_category);
            call.enqueue(new Callback<List<NewsModel>>() {
                @Override
                public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                    //remove Un-clicking  and lock the screen
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    rl_loading.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        newsModelList.clear();
                        newsModelList = response.body();
                        llm = new LinearLayoutManager(CounterActivity.this);
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(llm);
                        newsAdapter = new NewsAdapter(newsModelList);
                        rv.setAdapter(newsAdapter);

                    } else {
                        showSnackBar("اطلاعات دریافتی نامعتبر !");
                    }
                }

                @Override
                public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                    //remove Un-clicking  and lock the screen
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    rl_loading.setVisibility(View.GONE);
                    showSnackBar("خطای ناخواسته !");
                }
            });
        }
    }

    private void showSnackBar(String massage) {
        //declare RTL snackBar
        snackbar = Snackbar.make(coordinatorLayout, "", 10000);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        layout.setPadding(0, 0, 0, 0);
        LayoutInflater mInflater = LayoutInflater.from(CounterActivity.this);
        View snackView = mInflater.inflate(R.layout.custom_snackbar, null);
        TextView tv_action = (TextView) snackView.findViewById(R.id.tv_action);
        tv_action.setText("تلاش مجدد");
        tv_action.setVisibility(View.VISIBLE);
        tv_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                getDataFromServer();
            }
        });
        TextView tv_massage = (TextView) snackView.findViewById(R.id.tv_massage);
        tv_massage.setText(massage);

        layout.addView(snackView, 0);
        snackbar.show();
    }
}
