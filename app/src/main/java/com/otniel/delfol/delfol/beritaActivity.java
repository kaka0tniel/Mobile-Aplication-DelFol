package com.otniel.delfol.delfol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.otniel.delfol.delfol.API.ApiInterface;
import com.otniel.delfol.delfol.API.RESTClient;
import com.otniel.delfol.delfol.Model.ResponsBerita;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class beritaActivity extends AppCompatActivity {

    EditText title, content;
    Button btnsave, btnTampildata, btnupdate,btndelete;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);


        title = (EditText) findViewById(R.id.edt_nama);
        content = (EditText) findViewById(R.id.edt_usia);

        btnTampildata = (Button) findViewById(R.id.btntampildata);
        btnupdate =(Button) findViewById(R.id.btnUpdate);
        btnsave = (Button) findViewById(R.id.btn_insertdata);
        btndelete=(Button) findViewById(R.id.btnhapus);

        Intent data = getIntent();
        final String iddata = data.getStringExtra("id");
        if(iddata != null) {
            btnsave.setVisibility(View.GONE);
            btnTampildata.setVisibility(View.GONE);
            btnupdate.setVisibility(View.VISIBLE);
            btndelete.setVisibility(View.VISIBLE);
            title.setText(data.getStringExtra("title"));
            content.setText(data.getStringExtra("content"));

        }

        pd = new ProgressDialog(this);



        btnTampildata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent godata = new Intent(beritaActivity.this, TampilBerita.class);
                startActivity(godata);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("Loading Hapus ...");
                pd.setCancelable(false);
                pd.show();

                ApiInterface api = RESTClient.getClient().create(ApiInterface.class);
                Call<ResponsBerita> del  = api.deleteData(iddata);
                del.enqueue(new Callback<ResponsBerita>() {
                    @Override
                    public void onResponse(Call<ResponsBerita> call, Response<ResponsBerita> response) {
                        Log.d("Retro", "onResponse");
                        Toast.makeText(beritaActivity.this, response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        Intent gotampil = new Intent(beritaActivity.this,TampilBerita.class);
                        startActivity(gotampil);

                    }

                    @Override
                    public void onFailure(Call<ResponsBerita> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "onFailure");
                    }
                });
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("update ....");
                pd.setCancelable(false);
                pd.show();

                ApiInterface api = RESTClient.getClient().create(ApiInterface.class);
                Call<ResponsBerita> update = api.updateData(iddata,title.getText().toString(),content.getText().toString());
                update.enqueue(new Callback<ResponsBerita>() {
                    @Override
                    public void onResponse(Call<ResponsBerita> call, Response<ResponsBerita> response) {
                        Log.d("Retro", "Response");
                        Toast.makeText(beritaActivity.this,response.body().getPesan(),Toast.LENGTH_SHORT).show();
                        pd.hide();
                    }

                    @Override
                    public void onFailure(Call<ResponsBerita> call, Throwable t) {
                        pd.hide();
                        Log.d("Retro", "OnFailure");

                    }
                });
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setMessage("send data ... ");
                pd.setCancelable(false);
                pd.show();

                String stitle = title.getText().toString();
                String scontent = content.getText().toString();
                ApiInterface api = RESTClient.getClient().create(ApiInterface.class);

                Call<ResponsBerita> sendbio = api.setResult(stitle,scontent);
                sendbio.enqueue(new Callback<ResponsBerita>() {
                    @Override
                    public void onResponse(Call<ResponsBerita> call, Response<ResponsBerita> response) {
                        pd.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String kode = response.body().getKode();

                        if(kode.equals("1"))
                        {
                            Toast.makeText(beritaActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(beritaActivity.this, "Data Error tidak berhasil disimpan", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsBerita> call, Throwable t) {
                        pd.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });
            }
        });
    }
}
