package com.example.cosc411realm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.bson.Document;
import org.bson.types.ObjectId;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MainActivity extends AppCompatActivity {
    String appId="application-0-lqrja";
    MongoClient client;
    User person;
    Credentials cred;
    MongoDatabase database;
    int sumOfAscii=0;
    int chosenCharPosition=0;
    String string;
    String apiKey="2cFDik4F1Eil2cOO3XGZ72biSvhHXxhnkbE9IwUopbfZnasRyeMriubFV6X2Ef0z";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        App app=new App(new AppConfiguration.Builder(appId).build());

        //cred=Credentials.apiKey(apiKey);
        cred=Credentials.emailPassword("abeckma2@emich.edu","123456");
        app.loginAsync(cred, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess())
                {
                    Log.v("user","logged in right");
                }
                else
                {
                    Log.v("User","Failed");
                }
            }
        });
        /*app.getEmailPassword().registerUserAsync("abeckma2@emich.edu","123456",it->{
            if(it.isSuccess())
            {
                Log.v("User","success");
            }
            else
            {
                Log.v("User", "Failed");
            }
        });*/
        person=app.currentUser();
        client= person.getMongoClient("mongodb-atlas");
        database= client.getDatabase("COSC411Database");
    }
    public void enter(View v)
    {
        MongoCollection chat= database.getCollection("chatboxes");
        MongoCollection message1= database.getCollection("messages");
        MongoCollection user= database.getCollection("users");
        EditText encryptMessage=(EditText) findViewById(R.id.edit_one);
        TextView detext=(TextView) findViewById(R.id.textview_decrypt);
        string=encryptMessage.getText().toString();
        Encrypt encrypt=new Encrypt(string);
        String encryptString=encrypt.getEncryptedMsg();
        message1.insertOne(new Document("messageid",1).append("chatboxid",1).append("fromuser",1)
                .append("message",encryptString)).getAsync(result->{
            if(result.isSuccess())
            {
                Log.v("Data","Success");
            }
            else
            {
                Log.v("Data","Failed: "+result.getError().toString());
            }
        });
    }
}