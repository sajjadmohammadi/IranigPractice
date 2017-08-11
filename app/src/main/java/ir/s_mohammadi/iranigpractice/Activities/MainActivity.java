package ir.s_mohammadi.iranigpractice.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import ir.s_mohammadi.iranigpractice.R;
import ir.s_mohammadi.iranigpractice.Utilities.G;

public class MainActivity extends Master {
    private Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initLayout();
    }

    private void initLayout() {
        btn_setting = findView(R.id.btn_setting);
        btn_setting.setTypeface(G.typeFace_Medium);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
            }
        });
    }
}
