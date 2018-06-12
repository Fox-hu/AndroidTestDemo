package app.mrobot.cn.toutiaoexample;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.mrobot.cn.toutiaoexample.business.news.NewsFragment;
import app.mrobot.cn.toutiaoexample.business.video.VideoFragment;
import app.mrobot.cn.toutiaoexample.module.BaseActivity;
import app.mrobot.cn.toutiaoexample.widget.helper.BottomNavigationViewHepler;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String POSITION = "position";
    private static final String SELECTED_ITEM = "navigation_item";

    private static final int POSITION_NEWS = 0;
    private static final int POSITION_VIDEO = 1;
    private NewsFragment mNewsFragment;
    private VideoFragment mVideoFragment;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;

    private int position;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (savedInstanceState != null) {
            mNewsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag(
                    NewsFragment.class.getSimpleName());
            mVideoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag(
                    VideoFragment.class.getSimpleName());
            showFragment(savedInstanceState.getInt(POSITION));
            mBottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECTED_ITEM));

        } else {
            showFragment(POSITION_NEWS);
        }
    }


    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_activity_main);
        setSupportActionBar(mToolbar);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHepler.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this :: handleBottomNavOnclick);

        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private boolean handleBottomNavOnclick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_news:
                showFragment(POSITION_NEWS);
                break;
            case R.id.action_video:
                showFragment(POSITION_VIDEO);
                break;
            default:
                break;
        }
        return true;
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index) {
            case POSITION_NEWS:
                mToolbar.setTitle(R.string.app_name);
                if (mNewsFragment == null) {
                    mNewsFragment = NewsFragment.get();
                    ft.add(R.id.container, mNewsFragment, NewsFragment.class.getSimpleName());
                } else {
                    ft.show(mNewsFragment);
                }
                break;
            case POSITION_VIDEO:
                mToolbar.setTitle(R.string.video);
                if (mVideoFragment == null) {
                    mVideoFragment = VideoFragment.get();
                    ft.add(R.id.container, mVideoFragment, VideoFragment.class.getSimpleName());
                } else {
                    ft.show(mVideoFragment);
                }
                break;
            default:
                break;
        }

        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (mNewsFragment != null) {
            ft.hide(mNewsFragment);
        }
        if (mVideoFragment != null) {
            ft.hide(mVideoFragment);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
        outState.putInt(SELECTED_ITEM, mBottomNavigationView.getSelectedItemId());
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
