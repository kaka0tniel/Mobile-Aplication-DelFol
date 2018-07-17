package com.otniel.delfol.delfol;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.otniel.delfol.delfol.Adapter.pertandinganAdapter;
import com.otniel.delfol.delfol.Fragment.HomeFragment;
import com.otniel.delfol.delfol.Fragment.Tab1;
import com.otniel.delfol.delfol.Fragment.Tab2;
import com.otniel.delfol.delfol.Fragment.Tab3;
import com.otniel.delfol.delfol.Model.hasilPertandingan;
import com.otniel.delfol.delfol.Tab.MyAdapter;
import com.otniel.delfol.delfol.Tab.SlidingTabLayout;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


import java.util.ArrayList;

import static android.R.attr.bottom;
import static android.R.attr.fragment;
public class MainActivity extends AppCompatActivity {
    private BottomBar bottomBar;
private SlidingTabLayout mSlidingTabLayout;
private ViewPager mViewPager;
private Drawer.Result navigationDrawerLeft;
private AccountHeader.Result headerNavigationLeft;
    SharedPref sharedpref;
    private RecyclerView recyclerView;
    private pertandinganAdapter adapter;
    private ArrayList<hasilPertandingan> hasilPertandingans;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.Contact_Provider).withIcon(R.drawable.ic_contacts_black_24dp);
    SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.title_home).withIcon(R.drawable.ic_home_black_24dp);
    SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName(R.string.setting).withIcon(R.drawable.ic_settings_black_24dp);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Fragment fragment = null;
        mViewPager=(ViewPager)findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        mSlidingTabLayout=(SlidingTabLayout)findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setCustomTabView(R.layout.tab_view,R.id.tv_tab);
        mSlidingTabLayout.setViewPager(mViewPager);

        //==========================

        headerNavigationLeft = new AccountHeader()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        new ProfileDrawerItem().withName("Guest").withEmail("Guest@gmail.com").withIcon(getResources().getDrawable(R.drawable.ic_person_white_24dp))
                )
                .build();
        navigationDrawerLeft = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withDisplayBelowToolbar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withAccountHeader(headerNavigationLeft)
                .withSelectedItem(0)
                .addDrawerItems(
                        item2,item1,item3,
                        new DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener(){
                    Fragment fragment1 = null;
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                    if (position==0){
                        Intent nextScreen = new
                                Intent(getApplicationContext(), MainActivity.class);
                        startActivity(nextScreen);
                    }else if (position==1){
                        Intent nextScreen = new
                                Intent(getApplicationContext(), ContentProvider.class);
                        startActivity(nextScreen);
                    }else if (position==2){
                        Intent nextScreen = new
                                Intent(getApplicationContext(), Setting.class);
                        startActivity(nextScreen);
                    }
                    }
                })
                .build();

//        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Contact").withIcon(getResources().getDrawable(R.drawable.ic_contacts_black_24dp)));
//        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Broadcasat Provider").withIcon(getResources().getDrawable(R.drawable.ic_battery_unknown_black_24dp)));
//        navigationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Settings").withIcon(getResources().getDrawable(R.drawable.ic_settings_black_24dp)));


        //==================
//        bottomBar=(BottomBar)findViewById(R.id.bottombar);
//        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            Fragment fragment = null;
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId==R.id.tab_home){
//               fragment = new HomeFragment();
//                        }else if(tabId==R.id.tab_1){
//                            fragment = new Tab1();
//                        }else if(tabId==R.id.tab_2){
//                            fragment = new Tab2();
//                        }else if(tabId==R.id.tab_3){
//                            fragment = new Tab3();
//                        }
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.content,fragment)
//                                .commit();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.ContentProvider:
                Intent nextScreen = new
                        Intent(getApplicationContext(), ContentProvider.class);
                startActivity(nextScreen);
                return true;
            case R.id.BroadcastReceiver:
                Intent nextScreen2 = new
                        Intent(getApplicationContext(),Broadcast_Receiver.class);
                startActivity(nextScreen2);
                return true;
            case R.id.settings:
                Intent nextScreen3 = new
                        Intent(getApplicationContext(),Setting.class);
                startActivity(nextScreen3);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
