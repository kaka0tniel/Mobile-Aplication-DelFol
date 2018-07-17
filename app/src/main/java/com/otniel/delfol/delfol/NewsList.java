package com.otniel.delfol.delfol;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.otniel.delfol.delfol.Adapter.NewsListAdapter;
import com.otniel.delfol.delfol.Model.News;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class NewsList extends AppCompatActivity {
    GridView gridView;
    ArrayList<News> list;
    NewsListAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new NewsListAdapter(this, R.layout.news_item, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = CreateNews.sqLiteHelper.getData("SELECT * FROM NEWS");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new News(title, content, image, id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(NewsList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = CreateNews.sqLiteHelper.getData("SELECT id FROM NEWS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }


                        } else {
                            // delete
                            Cursor c = CreateNews.sqLiteHelper.getData("SELECT id FROM NEWS");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }
    ImageView imageViewNews;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_news_activity);
        dialog.setTitle("Update");

        imageViewNews = (ImageView) dialog.findViewById(R.id.imageViewNews);
        final EditText edtTitle = (EditText) dialog.findViewById(R.id.edtTitle);
        final EditText edtContent = (EditText) dialog.findViewById(R.id.edtContent);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        NewsList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CreateNews.sqLiteHelper.updateData(
                            edtTitle.getText().toString().trim(),
                            edtContent.getText().toString().trim(),
                            CreateNews.imageViewToByte(imageViewNews),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateNewsList();
            }
        });
    }

    private void showDialogDelete(final int idNews){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(NewsList.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    CreateNews.sqLiteHelper.deleteData(idNews);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateNewsList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateNewsList(){
        // get all data from sqlite
        Cursor cursor = CreateNews.sqLiteHelper.getData("SELECT * FROM NEWS");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String content = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new News(title, content, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewNews.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
