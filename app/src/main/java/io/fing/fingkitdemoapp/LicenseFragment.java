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

/**
 * A placeholder fragment containing a simple view.
 */
public class LicenseFragment extends Fragment {

    private static final String TAG = "license";

    private View rootView;
    private EditText licenseKeyEditor;
    private TextView output;

    public LicenseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_license, container, false);
        setHasOptionsMenu(true);

        licenseKeyEditor = rootView.findViewById(R.id.edittext_license_key);
        licenseKeyEditor.setText("");
        output = rootView.findViewById(R.id.textview_output);
        return rootView;
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

    private void doVerifyLicense() {
        final FingScanner scanner = FingScanner.getInstance();
        if (!scanner.isConnected()) {
            Log.wtf(TAG, "Not connected. Connecting now...");
            scanner.connect(getContext(), (s, e) -> {
                if (e == null)
                    doValidateLicense(scanner);
            });
        } else {
            Log.wtf(TAG, "Already connected. Checking license");
            doValidateLicense(scanner);
        }
    }

    private void doValidateLicense(final FingScanner scanner) {
        Editable text = licenseKeyEditor.getText();
        if (text == null || text.toString().isEmpty()) {
            Snackbar.make(rootView, R.string.enter_license_key, Snackbar.LENGTH_SHORT).show();
            return;
        }

        scanner.validateLicenseKey(text.toString(), null, newResultDumper());
    }

    @NonNull
    private FingScanner.FingResultCallback newResultDumper() {
        return (s, e) -> {
            if (e == null) {
                output.setText(s);
                Log.wtf(TAG, s);
            } else {
                final String errorText = "Error:" + e.getMessage();
                output.setText(errorText);
                Log.wtf(TAG, "Error", e);
            }
        };
    }

}
