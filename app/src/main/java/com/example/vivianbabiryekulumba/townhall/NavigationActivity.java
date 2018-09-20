package com.example.vivianbabiryekulumba.townhall;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.vivianbabiryekulumba.townhall.fragments.CommBoardsFrag;
import com.example.vivianbabiryekulumba.townhall.views.ViewPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "NavActivity.class";
    ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    PagerTabStrip pagerTabStrip;
    NavigationView navigationView;

    String[] boroughs = new String[]{
            "Bronx",
            "Brooklyn",
            "Manhattan",
            "Queens",
            "Staten Island"
    };

    final List<String> boroughsList = Arrays.asList(boroughs);
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setHomeAsUpIndicator(R.drawable.list_white);
        setNavigationViewListner();

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        navigationView = findViewById(R.id.nav_view);

        pagerTabStrip = findViewById(R.id.pager_header);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setOffscreenPageLimit(3);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        buildAlertDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void buildAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
        builder.setTitle("Select your borough");

        for (int i = 0; i < boroughsList.size(); i++) {
            builder.setItems(boroughs, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String currentItem = boroughsList.get(which);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    CommBoardsFrag commBoardsFrag = new CommBoardsFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("borough", currentItem);
                    commBoardsFrag.setArguments(bundle);
                    fragmentTransaction.add(commBoardsFrag, "CommBrdActivity.class");
                    fragmentTransaction.addToBackStack(currentItem);
                    fragmentTransaction.commit();
                    Log.d(TAG, "onClick: " + currentItem + bundle);
                }
            });
        }

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLACK));
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // close drawer when item is tapped
        mDrawerLayout.closeDrawers();

        // Add code here to update the UI based on the item selected
        // For example, swap UI fragments here

        Log.d(TAG, "onNavigationItemSelected: made it to onNavItemSelected");

        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            CommBoardsFrag commBoardsFrag = new CommBoardsFrag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, commBoardsFrag, "CommBrdFrag");
            transaction.addToBackStack(null);
            transaction.commit();
            buildAlertDialog();
        } else if (id == R.id.nav_petitions) {
            Intent intent = new Intent(NavigationActivity.this, PetitionListActivity.class);
            startActivity(intent);
            //Start database
        } else if (id == R.id.nav_opportunities) {
            Intent intent2 = new Intent(NavigationActivity.this, FavVolunteerOppActivity.class);
            startActivity(intent2);
            //Start database
        } else if (id == R.id.nav_profile) {
            //Build alert dialog to log in to profile
            //Start database
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
