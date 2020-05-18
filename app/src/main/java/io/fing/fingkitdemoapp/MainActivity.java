package io.fing.fingkitdemoapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.overlook.android.fingkit.FingScanner;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "main";

    /**
     * The PagerAdapter that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        connectOnCreate();
    }

    private void connectOnCreate() {
        final FingScanner scanner = FingScanner.getInstance();
        if (scanner.isConnected()) {
            Log.wtf(TAG, "FingScanner already connected");
            return;
        }

        Log.wtf(TAG, "FingScanner not connected. Connecting now...");
        scanner.connect(this, (s, e) -> {
            if (e == null)
                Log.wtf(TAG, "Connected to FingScanner");
            else
                Log.wtf(TAG, "Failed to connect to FingScanner", e);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectOnDestroy();
    }

    private void disconnectOnDestroy() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.wtf(TAG, "FingScanner already disconnected");
            return;
        }

        Log.wtf(TAG, "FingScanner connected. Disconnecting now...");
        scanner.disconnect((s, e) -> {
            if (e == null)
                Log.wtf(TAG, "Disconnected to FingScanner");
            else
                Log.wtf(TAG, "Failed to disconnect to FingScanner", e);
        });
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new LicenseFragment();
                case 1:
                    return new NetworkFragment();

            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "License";
                case 1:
                    return "Network";
            }
            return null;
        }
    }
}
