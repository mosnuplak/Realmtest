package com.mfec.realmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by E5-473G on 7/21/2017.
 */

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRealm();
    }
    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("android.realm")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
