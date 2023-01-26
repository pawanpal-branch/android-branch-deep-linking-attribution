package io.branch.branchandroiddemo;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import io.branch.referral.Branch;
import io.branch.referral.PrefHelper;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupVersionTextView();
        setupDisableAdNetworkCalloutsSwitch();
     }

        void setupVersionTextView() {
            final TextView versionTextView = findViewById(R.id.version_text_view);
            versionTextView.setText(String.format("Branch SDK v%s", Branch.getSdkVersionNumber()));
        }

        void setupDisableAdNetworkCalloutsSwitch() {
        final Switch disableAdNetworkCalloutsSwitch = findViewById(R.id.disable_ad_network_callouts);

        /*
         * Initialize switch state from SharedPreferences.
         */
        final PrefHelper prefHelper = PrefHelper.getInstance(this);
        disableAdNetworkCalloutsSwitch.setChecked(prefHelper.getAdNetworkCalloutsDisabled());

        /*
         * Update the setting whenever the switch changes state.
         */
        disableAdNetworkCalloutsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Branch.getInstance().disableAdNetworkCallouts(disableAdNetworkCalloutsSwitch.isChecked());
            }
        });

    }
}
