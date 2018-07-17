package com.otniel.delfol.delfol;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.ProgressDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.otniel.delfol.delfol.API.ApiInterface;
import com.otniel.delfol.delfol.API.RESTClient;
import com.otniel.delfol.delfol.Adapter.beritaAdapter;
import com.otniel.delfol.delfol.Model.ResponsBerita;
import com.otniel.delfol.delfol.Model.modelBerita;
import com.otniel.delfol.delfol.Tab.SlidingTabLayout;
import com.roughike.bottombar.BottomBar;

public class TampilBerita extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<modelBerita> mItems = new ArrayList<>();
    ProgressDialog pd;


    private BottomBar bottomBar;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Drawer.Result navigationDrawerLeft;
    private AccountHeader.Result headerNavigationLeft;
    SharedPref sharedpref;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_berita);


        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiInterface api = RESTClient.getClient().create(ApiInterface.class);
        Call<ResponsBerita> getdata = api.getResult();
        getdata.enqueue(new Callback<ResponsBerita>() {
            @Override
            public void onResponse(Call<ResponsBerita> call, Response<ResponsBerita> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new beritaAdapter(TampilBerita.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponsBerita> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });


    }
}
