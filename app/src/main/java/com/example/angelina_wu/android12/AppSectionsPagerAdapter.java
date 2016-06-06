package com.example.angelina_wu.android12;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AppSectionsPagerAdapter extends FragmentPagerAdapter {
    public AppSectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainSectionFragment();
            case 1:
                return new TabSectionFragment();
            case 2:
                //return new NotificationFragment();
                return new NotificationSectionFragment();
        }
        return new TabSectionFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    public static class TabSectionFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.tab_tab, container, false);
            return view;
        }
    }

    public static class MainSectionFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_main, container, false);
            return view;
        }
    }
    public static class NotificationSectionFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_notification, container, false);
            return view;
        }
    }
}
