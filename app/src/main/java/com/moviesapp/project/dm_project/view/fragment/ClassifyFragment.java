package com.moviesapp.project.dm_project.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moviesapp.project.dm_project.R;

import static com.moviesapp.project.dm_project.util.ConstansUtil.CLASSIFIER_NAME;

public class ClassifyFragment extends BaseFragment {

    private ContentCustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void init_Data() {

    }

    @Override
    public View init_View(ViewGroup container) {
        View w = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify, container, false);
//        View w= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classify, null);
        mCustomPagerAdapter = new ContentCustomPagerAdapter(getActivity().getSupportFragmentManager(), getActivity());
        mViewPager = w.findViewById(R.id.cla_view_pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        TextView title = w.findViewById(R.id.title);
        title.setText("Classifier");
        return w;
    }

    class ContentCustomPagerAdapter extends FragmentPagerAdapter {

        Context mContext;

        public ContentCustomPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {

            // Create fragment object
            Fragment fragment = new ClassifyContentFragment();
            Bundle args = new Bundle();
            args.putInt("cla_page_position", position);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return CLASSIFIER_NAME.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CLASSIFIER_NAME[position];
        }
    }
}
