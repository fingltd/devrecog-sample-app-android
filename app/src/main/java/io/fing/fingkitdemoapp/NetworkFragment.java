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

@SuppressWarnings("FieldCanBeLocal")
public class NetworkFragment extends Fragment {

    public static final String TAG = "fingk-kit:network";

    private View mRootView;
    private TextView mOutput;

    public NetworkFragment() { }

    // --------------------------------
    // FRAGMENT LICEFYCLE
    // --------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRootView = inflater.inflate(R.layout.fragment_network, container, false);
        mOutput = mRootView.findViewById(R.id.textview_output);
        return mRootView;
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

    // --------------------------------
    // ACTIONS
    // --------------------------------

    private void onNetworkInfo() {
        FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.d(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null) {
                    doNetworkInfo(scanner);
                }
            });
        } else {
            doNetworkInfo(scanner);
        }
    }

    private void onNetworkScan() {
        FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.d(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null) {
                    doNetworkScan(scanner);
                }
            });
        } else {
            doNetworkScan(scanner);
        }
    }

    private void onNetworkStop() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.d(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null) {
                    doNetworkStop(scanner);
                }
            });
        } else {
            doNetworkStop(scanner);
        }
    }

    private void doNetworkInfo(final FingScanner scanner) {
        scanner.networkInfo(newResultDumper());
    }

    private void doNetworkScan(final FingScanner scanner) {
        scanner.networkScan(null, newResultDumper());
    }

    private void doNetworkStop(final FingScanner scanner) {
        scanner.networkScanStop();
    }

    // --------------------------------
    // SCANNER RESULT CALLBACK
    // --------------------------------

    @NonNull
    private FingScanner.FingResultCallback newResultDumper() {
        return (s, e) -> {
            if (e == null) {
                mOutput.setText(s);
                Log.d(TAG, s);
            } else {
                mOutput.setText(String.format("Error: %s", e.getMessage()));
                Log.e(TAG, "Error", e);
            }
        };
    }

}
