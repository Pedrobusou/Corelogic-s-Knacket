package com.example.pedroramos.testtab2.ui.activity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pedroramos.testtab2.data.model.Buyer;
import com.example.pedroramos.testtab2.ui.adapter.BuyerAdapter;
import com.example.pedroramos.testtab2.ui.fragments.FilterFragment;
import com.example.pedroramos.testtab2.ui.fragments.NavigationButtonsFragment;
import com.example.pedroramos.testtab2.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FilterFragment.OnFragmentInteractionListener, NavigationButtonsFragment.OnFragmentInteractionListener {
    @BindView(R.id.container) ViewPager mViewPager;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar_title) TextView toolbar_title;
    //@Bind(R.id.listBuyers) ListView listBuyers;

    public String[] titles = {"Buyers", "Sellers"};
    BuyerAdapter buyerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        setUpTabs();

        Buyer buyer = new Buyer();
        ArrayList<Buyer> buyers = buyer.add3Buyers();

        /*buyerAdapter = new BuyerAdapter(this, R.layout.listview_row_buyers, buyers);
        listBuyers.setAdapter(buyerAdapter);
        buyerAdapter.notifyDataSetChanged();*/
    }

    public void setUpTabs(){
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar_title.setText(titles[mViewPager.getCurrentItem()]);
    }

    @OnClick(R.id.btnDone) void doneClicked(){
        drawer.closeDrawer(GravityCompat.END);
        //REFRESH LIST
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.END)) drawer.closeDrawer(GravityCompat.END);
        else moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds FILTER ICON, which pops up FILTER MENU (navigationDrawer).
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawer.openDrawer(GravityCompat.END);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() { }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View tab;
            Bundle args = getArguments();
            int currentView = args.getInt(PlaceholderFragment.ARG_SECTION_NUMBER)-2;

            if(currentView == 0){
                tab = inflater.inflate(R.layout.fragment_filter_menu, container, false);
            }else {
                tab = inflater.inflate(R.layout.fragment_list, container, false);
            }

            return tab;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class TabsAdapter extends FragmentPagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Buyers";
                case 1:
                    return "Sellers";
            }
            return null;
        }
    }
}