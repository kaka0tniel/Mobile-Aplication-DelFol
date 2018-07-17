package com.otniel.delfol.delfol;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.otniel.delfol.delfol.Tab.MyAdapter;
import com.otniel.delfol.delfol.Tab.SlidingTabLayout;

public class Setting extends AppCompatActivity {
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    private Switch myswitch;
    private Button btnCrudNews;
    private Button btnCrudPlayer;
    private Button btnCrud;
    SharedPref sharedpref;
    private Button btnLogin;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.Contact_Provider).withIcon(R.drawable.ic_contacts_black_24dp);
    SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.title_home).withIcon(R.drawable.ic_home_black_24dp);
    SecondaryDrawerItem item3 = new SecondaryDrawerItem().withIdentifier(3).withName(R.string.setting).withIcon(R.drawable.ic_settings_black_24dp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);

        myswitch=(Switch)findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState()==true) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
        final Fragment fragment = null;

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
        btnCrudNews = (Button) findViewById(R.id.btnCrudNews);
        btnCrudPlayer = (Button) findViewById(R.id.btnCrudPlayer);
        btnCrud = (Button) findViewById(R.id.btnCrud);

        btnCrudNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new
                        Intent(getApplicationContext(),CrudSqlLiteNews.class);
                startActivity(nextScreen);
            }
        });

        btnCrudPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new
                        Intent(getApplicationContext(),CrudRealmPlayer.class);
                startActivity(nextScreen);
            }
        });

        btnCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new
                        Intent(getApplicationContext(),beritaActivity.class);
                startActivity(nextScreen);
            }
        });
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),Setting.class);
        startActivity(i);
        finish();
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
