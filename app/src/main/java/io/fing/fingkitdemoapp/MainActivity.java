package io.fing.fingkitdemoapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.overlook.android.fingkit.FingScanner;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "fing-kit:main";

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // --------------------------------
    // ACTIVITY LIFECYCLE
    // --------------------------------

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disconnectOnDestroy();
    }

    // --------------------------------
    // FING SERVICE CONNECTION
    // --------------------------------

    private void connectOnCreate() {
        FingScanner scanner = FingScanner.getInstance();
        if (scanner.isConnected()) {
            Log.w(TAG, "FingScanner already connected");
            return;
        }
        Log.d(TAG, "FingScanner not connected. Connecting now...");
        scanner.connect(this, (s, e) -> {
            if (e == null) {
                Log.d(TAG, "Connected to FingScanner");
            } else {
                Log.e(TAG, "Failed to connect to FingScanner", e);
            }
        });
    }

    private void disconnectOnDestroy() {
        FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.w(TAG, "FingScanner already disconnected");
            return;
        }
        Log.d(TAG, "FingScanner connected. Disconnecting now...");
        scanner.disconnect((s, e) -> {
            if (e == null) {
                Log.d(TAG, "Disconnected to FingScanner");
            } else {
                Log.e(TAG, "Failed to disconnect to FingScanner", e);
            }
        });
    }

    // --------------------------------
    // PAGER ADAPTER
    // --------------------------------

    public static class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new LicenseFragment();
            return new NetworkFragment();
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
