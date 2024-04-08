package com.google.BTL_Quanlychitieu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.BTL_Quanlychitieu.Entity.user;
import com.google.BTL_Quanlychitieu.Other.CustomPicture;
import com.google.BTL_Quanlychitieu.Other.DataLocalManager;
import com.google.BTL_Quanlychitieu.Other.MyApplication;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.BTL_Quanlychitieu.databinding.ActivityMainBinding;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    ImageView img_header;

    TextView email_header, name_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        if (bundle != null) inituser(bundle);
        Toolbar toolbar = binding.appBarMain.toolbar;
        setSupportActionBar(toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chi, R.id.nav_thu, R.id.nav_thongke, R.id.nav_gioithieu)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View view = binding.navView.getHeaderView(0);
        img_header = view.findViewById(R.id.imageView_header);
        email_header = view.findViewById(R.id.email_header);
        name_header = view.findViewById(R.id.name_header);

        Loadinfouser();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_Signout)
                {
                    signout();
                }
                return false;
            }
        });


        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "vao activitimain r", Toast.LENGTH_SHORT).show();
            }
        };

    }

    private void signout() {
        DataLocalManager.remove_user();
        Intent it = new Intent(MainActivity.this, Signin.class);
        MyApplication.User = null;
        startActivity(it);
        finish();
    }

    private void Loadinfouser() {
        String tmpuser = DataLocalManager.get_user();
        if (tmpuser != null) {
            Gson gson = new Gson();
            Type objtype = new TypeToken<user>() {
            }.getType();
            user tmp = gson.fromJson(tmpuser, objtype);
            img_header.setImageURI(CustomPicture.getUri(tmp.avatar));
            name_header.setText(tmp.name);
            email_header.setText(tmp.username);
        }
    }

    private void inituser(Bundle bundle) {
        user tmp = (user) bundle.getSerializable("dulieu");
        Gson gson = new Gson();
        DataLocalManager.update_user(gson.toJson(tmp));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}