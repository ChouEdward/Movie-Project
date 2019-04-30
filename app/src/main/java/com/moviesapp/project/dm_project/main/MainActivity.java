package com.moviesapp.project.dm_project.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.moviesapp.project.dm_project.R;
import com.moviesapp.project.dm_project.data.bean.GetMoviesResponse;
import com.moviesapp.project.dm_project.interfaces.ConnectSuccessListener;
import com.moviesapp.project.dm_project.interfaces.ILoginBiz;
import com.moviesapp.project.dm_project.util.CSVLoader;
import com.moviesapp.project.dm_project.util.ConstansUtil;
import com.moviesapp.project.dm_project.data.ModuleAddressBean;
import com.moviesapp.project.dm_project.util.OkHttpUtil;
import com.moviesapp.project.dm_project.view.fragment.ClassifierFragment;
import com.moviesapp.project.dm_project.view.fragment.ClassifyFragment;
import com.moviesapp.project.dm_project.view.fragment.HomeFragment;
import com.moviesapp.project.dm_project.view.fragment.RecommendFragment;
import com.moviesapp.project.dm_project.view.viewpager.NoScrollViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private NoScrollViewPager mViewPager;
    private CustomPagerAdapter mCustomPagerAdapter;
    private BottomNavigationBar bottomNavigationBar;
    private List<ModuleAddressBean> list;

    public List<ModuleAddressBean> getMovieData(){
//        if(list==null||list.isEmpty()){
//            list = new ArrayList<>();
//            list.addAll(ConstansUtil.movie_lists);
////            list = CSVLoader.readMonDataCsv();
//        }
        return ConstansUtil.movie_lists;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        testConnect();

        initialView();

    }
    private void testConnect() {
        List<String> key = new ArrayList<>();
        List<String> value = new ArrayList<>();

        key.add("userPhone");
        value.add("admin");
        key.add("password");
        value.add("57dd03ed397eabaeaa395eb740b770fd");
        key.add("isRememberMe");
        value.add("true");

        String url = "/u/mobileLogin.shtml";

        String s = "";
//        OkHttpUtil.getInstance().connect(key, value, url, new ConnectSuccessListener() {
//            @Override
//            public void onFailure(String message) {
//                Log.e("Test",message);
//            }
//
//            @Override
//            public void onResponse(final String response) {
//                GsonLogin gsonLogin = new Gson().fromJson(response, GsonLogin.class);
//
////                Headers headers = response.headers();
////                List<String> cookies = headers.values("Set-Cookie");
////                String session = cookies.get(0);
////                OkHttpUtil.loginSessionID = session.substring(0, session.indexOf(";"));
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
//                    }
//                });
//                Log.e("Test",response);
//                test2();
//            }
//        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstansUtil.SERVER_IP + "/u/")
                .build();
        ILoginBiz loginBiz = retrofit.create(ILoginBiz.class);
        Call<ResponseBody> call = loginBiz.login("admin", "57dd03ed397eabaeaa395eb740b770fd", true);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    if (response.body().string().contains("200")) {

                        Headers headers = response.headers();
                        List<String> cookies = headers.values("Set-Cookie");
                        String session = cookies.get(0);
                        OkHttpUtil.loginSessionID = session.substring(0, session.indexOf(";"));
                        Log.e("TestSearch",OkHttpUtil.loginSessionID+"");
//                        test2();
//                        testQuery();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void test2(){
        List<String> key = new ArrayList<>();
        List<String> value = new ArrayList<>();

        key.add("userPhone");
        value.add("admin");

        String url = "/user/getUserInfo.shtml";
        String s = "";
        OkHttpUtil.getInstance().connect(key, value, url, new ConnectSuccessListener() {
            @Override
            public void onFailure(String message) {
                Log.e("Test",message);
            }

            @Override
            public void onResponse(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_LONG).show();
                    }
                });
                Log.e("Test",response);
            }
        });
    }

    public void testQuery(){
        List<String> key = new ArrayList<>();
        List<String> value = new ArrayList<>();

        key.add("query");
        value.add("kill toy");

        String url = "/user/getMoviesBySearch.shtml";
        String s = "";
        OkHttpUtil.getInstance().connect(key, value, url, new ConnectSuccessListener() {
            @Override
            public void onFailure(String message) {
                Log.e("Test",message);
            }

            @Override
            public void onResponse(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this,response, Toast.LENGTH_LONG).show();
                        GetMoviesResponse moviesResponse = new Gson().fromJson(response,GetMoviesResponse.class);
                        List<ModuleAddressBean> list = moviesResponse.getModuleAddressBeans();
                        Log.e("Test",list.get(0).getTitle());
                    }
                });
//                Log.e("Test",response);
            }
        });
    }

    private void initialView() {
        bottominitial();
        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this);

        mViewPager = (NoScrollViewPager) findViewById(R.id.view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mCustomPagerAdapter);
    }

    private void bottominitial() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {

                mViewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.baseline_home_white_18dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.baseline_group_work_white_18dp, "Classifier"))
                .addItem(new BottomNavigationItem(R.drawable.baseline_favorite_white_18dp, "Recommend"))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public CustomPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt("page_position", position + 1);
            switch (position){
                default:
                case 0:
                    Fragment fragment = new HomeFragment();
                    fragment.setArguments(args);
                    return fragment;
                case 1:
                    Fragment cfragment = new ClassifierFragment();
                    cfragment.setArguments(args);
                    return cfragment;
                case 2:
                    Fragment rfragment = new RecommendFragment();
                    rfragment.setArguments(args);
                    return rfragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + (position + 1);
        }
    }
}
