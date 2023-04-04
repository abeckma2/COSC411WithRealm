package com.example.cosc411realm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class MainActivity extends AppCompatActivity {
    String appId="application-0-lqrja";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(appId).build());

    }
}