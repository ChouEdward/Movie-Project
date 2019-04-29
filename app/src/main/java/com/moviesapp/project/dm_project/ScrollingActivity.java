package com.moviesapp.project.dm_project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviesapp.project.dm_project.util.CSVLoader;
import com.moviesapp.project.dm_project.util.ConstansUtil;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.view.activity.SearchActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.moviesapp.project.dm_project.util.CSVLoader.readMonDataCsv;

public class ScrollingActivity extends AppCompatActivity {
    private List<ModuleAddressBean> list;
    private Long id=16L;
    private TextView overview;
    private ImageView poster;
    private ModuleAddressBean movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        list = ConstansUtil.movie_lists;
        id = getIntent().getLongExtra("id",0L);
        for (ModuleAddressBean moduleAddressBean:
             list) {
            if(moduleAddressBean.getId().equals(id)){
                movie = moduleAddressBean;
                break;
            }
        }

        overview = findViewById(R.id.overview);
        poster = findViewById(R.id.poster);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movie.getTitle());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Like", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        overview.setText(movie.getOverview());
        Picasso.get().load(ConstansUtil.POSTER_URL+movie.getPosterPath()).into(poster);
//        test();
//        OnRequestPeimiss();
        //List<ModuleAddressBean> list = readMonDataCsv();
       // Log.e("mytest",list.get(5).getOverview());
    }

    private void test(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/test.csv";
//                Uri.parse("android.resource://" + this.getPackageName() + "/"+R.raw.test).getPath();
//        Log.e("mytest",path);
        List<ModuleAddressBean>list = CSVLoader.readCSV(path,this);
        Log.e("mytest",list.get(0).getOverview());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE =1 ;
    private void OnRequestPeimiss(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请WRITE_EXTERNAL_STORAGE权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            }
            else{
                readMonDataCsv();
            }
        }else{
            readMonDataCsv();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readMonDataCsv();
            } else {
                readMonDataCsv();
            }
        }
    }
}
