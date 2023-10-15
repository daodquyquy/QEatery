package com.example.qeatery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.qeatery.R;
import com.example.qeatery.Activity.LoginActivity;
import com.example.qeatery.Fragment.ChangPassFragment;
import com.example.qeatery.Fragment.DrinkFragment;
import com.example.qeatery.Fragment.EatFragment;
import com.example.qeatery.Fragment.HomeFragment;
import com.example.qeatery.Fragment.RevenueFragment;
import com.example.qeatery.Fragment.TopFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anh_xa();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_home){
                    Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    setTitle("Home");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new HomeFragment());
                }
                if(item.getItemId() == R.id.menu_doan){
                    Toast.makeText(MainActivity.this, "Đồ ăn", Toast.LENGTH_SHORT).show();
                    setTitle("Đồ ăn");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new EatFragment());
                }
                if(item.getItemId() == R.id.menu_douong){
                    Toast.makeText(MainActivity.this, "Đồ uống", Toast.LENGTH_SHORT).show();
                    setTitle("Đồ uống");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new DrinkFragment());
                }
                if(item.getItemId() == R.id.menu_doanhthu){
                    Toast.makeText(MainActivity.this, "Doanh thu", Toast.LENGTH_SHORT).show();
                    setTitle("Doanh thu");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new RevenueFragment());
                }
                if(item.getItemId() == R.id.menu_topsp){
                    Toast.makeText(MainActivity.this, "Top SP", Toast.LENGTH_SHORT).show();
                    setTitle("Top SP");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new TopFragment());
                }
                if(item.getItemId() == R.id.menu_doimk){
                    Toast.makeText(MainActivity.this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
                    setTitle("Đổi mật khẩu");
                    drawerLayout.closeDrawer(GravityCompat.START);
                    load_fragment(new ChangPassFragment());
                }
                if(item.getItemId() == R.id.menu_logout){
                    Toast.makeText(MainActivity.this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                    ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("Vui lòng chờ");
                    dialog.setMessage("Đang đăng xuất");
                    dialog.show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    },3000);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        load_fragment(new HomeFragment());
    }

    private void load_fragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_activity,fragment);
        fragmentTransaction.commit();
    }
    private void anh_xa(){
        this.drawerLayout = findViewById(R.id.drawerLayout);
        this.toolbar = findViewById(R.id.toolBar);
        this.navigationView = findViewById(R.id.NavigationDrawer);
        setSupportActionBar(toolbar);
    }
}