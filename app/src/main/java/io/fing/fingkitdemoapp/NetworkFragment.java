package io.fing.fingkitdemoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.overlook.android.fingkit.FingScanner;

/**
 * A placeholder fragment containing a simple view.
 */
public class NetworkFragment extends Fragment {

    public static final String TAG = "network";

    private View rootView;
    private TextView output;

    public NetworkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_network, container, false);
        setHasOptionsMenu(true);

        output = (TextView) rootView.findViewById(R.id.textview_output);
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_network, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            onNetworkInfo();
            return true;
        }
        if (id == R.id.action_scan) {
            onNetworkScan();
            return true;
        }
        if (id == R.id.action_stop) {
            onNetworkStop();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onNetworkInfo() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.wtf(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null)
                    doNetworkInfo(scanner);
            });
        } else {
            doNetworkInfo(scanner);
        }
    }

    private void onNetworkScan() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.wtf(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null)
                    doNetworkScan(scanner);
            });
        } else {
            doNetworkScan(scanner);
        }
    }


    private void onNetworkStop() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.wtf(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null)
                    doNetworkStop(scanner);
            });
        } else {
            doNetworkStop(scanner);
        }
    }

    // --------------------------------------------------------------------------------

    private void doNetworkInfo(final FingScanner scanner) {
        scanner.networkInfo(newResultDumper());
    }

    private void doNetworkScan(final FingScanner scanner) {
        scanner.networkScan(null, newResultDumper());
    }

    private void doNetworkStop(final FingScanner scanner) {
        scanner.networkScanStop();
    }

    @NonNull
    private FingScanner.FingResultCallback newResultDumper() {
        return new FingScanner.FingResultCallback() {
            @Override
            public void handle(String s, Exception e) {
                if (e == null) {
                    output.setText(s);
                    Log.wtf(TAG, s);
                } else {
                    output.setText("Error:" + e.getMessage());
                    Log.wtf(TAG, "Error", e);
                }
            }
        };
    }

}
