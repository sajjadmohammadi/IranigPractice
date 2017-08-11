package ir.s_mohammadi.iranigpractice.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.s_mohammadi.iranigpractice.R;
import ir.s_mohammadi.iranigpractice.Utilities.G;

/**
 * Created by Sajjad on 8/8/2017.
 */
public class SettingActivity extends Master {
    private ImageView iv_back;
    private LinearLayout ll_marked, ll_counter;
    private TextView tv_categories[];
    private String str_categories[];
    public static String selected_category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolbar();
        initLayout();
    }

    private void initLayout() {
        iv_back = findView(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ll_marked = findView(R.id.ll_marked);
        ll_marked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.ToastShort(getResources().getString(R.string.marked));
            }
        });
        ll_counter = findView(R.id.ll_counter);
        ll_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, CounterActivity.class));
            }
        });
        //Declare TextViews newsType (news category(sport,general,political and ...)) into a TextView Array
        tv_categories = new TextView[6];
        tv_categories[0] = findView(R.id.tv_general);
        tv_categories[1] = findView(R.id.tv_political);
        tv_categories[2] = findView(R.id.tv_sports);
        tv_categories[3] = findView(R.id.tv_economic);
        tv_categories[4] = findView(R.id.tv_technology);
        tv_categories[5] = findView(R.id.tv_international);
        //Declare String newsType (in string resource) into String Array
        str_categories = new String[6];
        str_categories[0] = getResources().getString(R.string.general);
        str_categories[1] = getResources().getString(R.string.political);
        str_categories[2] = getResources().getString(R.string.sports);
        str_categories[3] = getResources().getString(R.string.economic);
        str_categories[4] = getResources().getString(R.string.technology);
        str_categories[5] = getResources().getString(R.string.international);


        //set political for default selection
        tv_categories[1].setBackgroundResource(R.drawable.bg_solid_textsecondary);
        tv_categories[1].setTextColor(ContextCompat.getColor(this, R.color.gray200));
        selected_category = str_categories[1];

        //set listener for all textView inside for
        for (int i = 0; i < 6; i++) {
            final int finalI = i;
            tv_categories[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected_category = str_categories[finalI];
                    G.ToastShort(selected_category);
                    changeBackgroundColor(tv_categories[finalI]);
                }
            });
        }
    }

    //this method change background color and textColor in clicked textView
    private void changeBackgroundColor(TextView tv) {
        for (int i = 0; i < 6; i++) {
            tv_categories[i].setBackgroundResource(R.drawable.bg_border_textsecondary);
            tv_categories[i].setTextColor(ContextCompat.getColor(this, R.color.text_secondary));
        }
        tv.setBackgroundResource(R.drawable.bg_solid_textsecondary);
        tv.setTextColor(ContextCompat.getColor(this, R.color.gray200));
    }
}
