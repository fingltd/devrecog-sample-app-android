package io.fing.fingkitdemoapp;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.overlook.android.fingkit.FingScanner;

public class LicenseFragment extends Fragment {

    public static final String TAG = "fing-kit:license";

    private View mRootView;
    private EditText mLicenseKeyEditor;
    private TextView mOutput;

    public LicenseFragment() { }

    // --------------------------------
    // FRAGMENT LICEFYCLE
    // --------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mRootView = inflater.inflate(R.layout.fragment_license, container, false);
        mLicenseKeyEditor = mRootView.findViewById(R.id.edittext_license_key);
        mLicenseKeyEditor.setText("");
        mOutput = mRootView.findViewById(R.id.textview_output);
        return mRootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_license, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_verify) {
            doVerifyLicense();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // --------------------------------
    // ACTIONS
    // --------------------------------

    private void doVerifyLicense() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.d(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null) {
                    doValidateLicense(scanner);
                }
            });
        } else {
            Log.d(TAG, "Already connected. Checking license");
            doValidateLicense(scanner);
        }
    }

    private void doValidateLicense(final FingScanner scanner) {
        Editable text = mLicenseKeyEditor.getText();
        if (text == null || text.toString().isEmpty()) {
            Snackbar.make(mRootView, R.string.enter_license_key, Snackbar.LENGTH_SHORT).show();
            return;
        }
        scanner.validateLicenseKey(text.toString(), null, newResultDumper());
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
