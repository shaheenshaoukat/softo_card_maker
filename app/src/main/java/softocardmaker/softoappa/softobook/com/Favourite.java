package softocardmaker.softoappa.softobook.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import Activities.DatabaseHelper;
import Activities.home;
import Adapter.FavImageAdapter;
import Model.FavImageModel;
import softocardmaker.softoappa.softobook.com.databinding.ActivityFavouriteBinding;

public class Favourite extends AppCompatActivity {
    ActivityFavouriteBinding binding;
    ArrayList<FavImageModel> list;
    DatabaseHelper myDb;


    FavImageAdapter adapter;
    FavImageModel modelclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());






        /*ads code start*/
        MobileAds.initialize(this
                , new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(InitializationStatus initializationStatus) {
                    }
                });


        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        binding.adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        });

        /*ads code end*/




        list=new ArrayList<>();

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        myDb = new DatabaseHelper(this);

        myDb.getWritableDatabase();


        Cursor cursor = myDb.getAllData();



        if(cursor.getCount() == 0 )
        {
            Toast.makeText(this, "data not found", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), home.class);
            startActivity(intent);

            return;
        }

        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext())
        {


            modelclass= new FavImageModel(cursor.getString(0),
                    cursor.getString(1));





            list.add(modelclass);
            adapter = new FavImageAdapter(list,myDb,this);
            binding.recyclerview.setAdapter(adapter);

        }





        // showMessage("Data",buffer.toString());
    }


}