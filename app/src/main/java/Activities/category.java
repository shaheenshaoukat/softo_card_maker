package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import Adapter.card_adapter;
import Model.card_model;
import Model.ctgry_model;
import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.ActivityCategoryBinding;

public class category extends AppCompatActivity {

    String ctgryname;


    ActivityCategoryBinding binding;

    ArrayList<card_model> holder = new ArrayList<>();
    card_adapter myadapter;
    ArrayList<ctgry_model> holder2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
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



        ctgryname = getIntent().getStringExtra("name");
        binding.ctgryname.setText(ctgryname);
        if (ctgryname.equals("Cousin's Birthday")) {
            cusin_birthday();


        } else if (ctgryname.equals("Brother")) {

            brother();
        } else if (ctgryname.equals("Sister")) {
            sister();

        } else if (ctgryname.equals("Grandapa")) {

            grandpa();

        } else if (ctgryname.equals("Grandma")) {

            grandma();

        } else if (ctgryname.equals("Brother_in_law")) {

            brother_in_law();

        } else if (ctgryname.equals("Sister_in_law")) {
            sister_in_law();

        } else if (ctgryname.equals("Mother_in_law")) {
            mother_in_law();

        } else if (ctgryname.equals("Father")) {
            father();

        } else if (ctgryname.equals("Mother")) {
            mother();

        } else if (ctgryname.equals("Boyfraind_Birthday")) {
            boyfraind();

        } else if (ctgryname.equals("GirlFraind_Birthday")) {
            girlfraind();

        } else if (ctgryname.equals("Son's_Birthday")) {
            son();

        } else if (ctgryname.equals("Daughter Birthday")) {
            daughter();

        } else if (ctgryname.equals("nephew's Birthday")) {
            nephew();

        } else if (ctgryname.equals("niece's Birthday")) {
            niece();

        } else if (ctgryname.equals("Husband")) {
            husband();

        } else if (ctgryname.equals("Wife")) {
            wife();

        } else if (ctgryname.equals("Wedding anniversary")) {
            wedding_anniversary();

        } else if (ctgryname.equals("Best Fraind")) {
            best_fraind();

        } else if (ctgryname.equals("Thanks")) {
            thanks();

        } else if (ctgryname.equals("Funny")) {
            funny();

        } else if (ctgryname.equals("Happy Birthday")) {
            happy_birthday();

        } else if (ctgryname.equals("Kids")) {

            kids();
        }


    }

    public void cusin_birthday() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String name = "Cusin Birthday";

        holder.add(new card_model(R.drawable.cone, name));
        holder.add(new card_model(R.drawable.ctwo, name));
        holder.add(new card_model(R.drawable.cthree, name));
        holder.add(new card_model(R.drawable.cfour, name));
        holder.add(new card_model(R.drawable.cfive, name));
        holder.add(new card_model(R.drawable.csix, name));
        holder.add(new card_model(R.drawable.cseven, name));
        holder.add(new card_model(R.drawable.ceight, name));
        holder.add(new card_model(R.drawable.cnine, name));
        holder.add(new card_model(R.drawable.cten, name));
        holder.add(new card_model(R.drawable.celeven, name));
        holder.add(new card_model(R.drawable.ctwelve, name));
        holder.add(new card_model(R.drawable.cthrteen, name));
        holder.add(new card_model(R.drawable.cfourteen, name));
        holder.add(new card_model(R.drawable.cfifteen, name));
        holder.add(new card_model(R.drawable.csixteen, name));
        holder.add(new card_model(R.drawable.cseventeen, name));
        holder.add(new card_model(R.drawable.ceighteen, name));
        holder.add(new card_model(R.drawable.cninteen, name));
        holder.add(new card_model(R.drawable.ctwenty, name));

        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void brother() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String brother = "Brother";
        holder.add(new card_model(R.drawable.brone, brother));
        holder.add(new card_model(R.drawable.brtwo, brother));
        holder.add(new card_model(R.drawable.brthree, brother));
        holder.add(new card_model(R.drawable.brfour, brother));
        holder.add(new card_model(R.drawable.brfive, brother));
        holder.add(new card_model(R.drawable.brseven, brother));
        holder.add(new card_model(R.drawable.brsix, brother));
        holder.add(new card_model(R.drawable.breight, brother));
        holder.add(new card_model(R.drawable.brnine, brother));
        holder.add(new card_model(R.drawable.brten, brother));
        holder.add(new card_model(R.drawable.breleven, brother));
        holder.add(new card_model(R.drawable.brtwelve, brother));
        holder.add(new card_model(R.drawable.brthiteen, brother));
        holder.add(new card_model(R.drawable.brfourteen, brother));
        holder.add(new card_model(R.drawable.brsixteen, brother));
        holder.add(new card_model(R.drawable.brseventeen, brother));
        holder.add(new card_model(R.drawable.breighteen, brother));
        holder.add(new card_model(R.drawable.brninteen, brother));
        myadapter = new card_adapter(holder, category.this);

        binding.recyclerview.setAdapter(myadapter);

    }

    public void sister() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String sister = "Sister";
        holder.add(new card_model(R.drawable.sone, sister));
        holder.add(new card_model(R.drawable.stwo, sister));
        holder.add(new card_model(R.drawable.sthree, sister));
        holder.add(new card_model(R.drawable.sfour, sister));
        holder.add(new card_model(R.drawable.sfive, sister));
        holder.add(new card_model(R.drawable.ssix, sister));
        holder.add(new card_model(R.drawable.sseven, sister));
        holder.add(new card_model(R.drawable.seight, sister));
        holder.add(new card_model(R.drawable.snine, sister));
        holder.add(new card_model(R.drawable.sten, sister));
        holder.add(new card_model(R.drawable.seleven, sister));
        holder.add(new card_model(R.drawable.stwelve, sister));
        holder.add(new card_model(R.drawable.sthirteen, sister));
        holder.add(new card_model(R.drawable.sfourteen, sister));
        holder.add(new card_model(R.drawable.sfifteen, sister));
        holder.add(new card_model(R.drawable.ssixteen, sister));
        holder.add(new card_model(R.drawable.sseventeen, sister));
        holder.add(new card_model(R.drawable.seighteen, sister));
        holder.add(new card_model(R.drawable.sninteen, sister));
        holder.add(new card_model(R.drawable.stwenty, sister));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void grandpa() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String grandpa = "Grandpa";
        holder.add(new card_model(R.drawable.gpone, grandpa));
        holder.add(new card_model(R.drawable.gptwo, grandpa));
        holder.add(new card_model(R.drawable.gpthree, grandpa));
        holder.add(new card_model(R.drawable.gpfour, grandpa));
        holder.add(new card_model(R.drawable.gpfive, grandpa));
        holder.add(new card_model(R.drawable.gpsix, grandpa));
        holder.add(new card_model(R.drawable.gpseven, grandpa));
        holder.add(new card_model(R.drawable.gpeight, grandpa));
        holder.add(new card_model(R.drawable.gpnine, grandpa));
        holder.add(new card_model(R.drawable.gpten, grandpa));
        holder.add(new card_model(R.drawable.gpeleven, grandpa));
        holder.add(new card_model(R.drawable.gptwelve, grandpa));
        holder.add(new card_model(R.drawable.gpthirteen, grandpa));
        holder.add(new card_model(R.drawable.gpfourteen, grandpa));
        holder.add(new card_model(R.drawable.gpfifteen, grandpa));
        holder.add(new card_model(R.drawable.gpsixteen, grandpa));
        holder.add(new card_model(R.drawable.gpseventeen, grandpa));
        holder.add(new card_model(R.drawable.gpeighteen, grandpa));
        holder.add(new card_model(R.drawable.gpninteen, grandpa));
        holder.add(new card_model(R.drawable.gptwenty, grandpa));

        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void grandma() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));
        String grandma = "Grandma";
        holder.add(new card_model(R.drawable.gmone, grandma));
        holder.add(new card_model(R.drawable.gmtwo, grandma));
        holder.add(new card_model(R.drawable.gmthree, grandma));
        holder.add(new card_model(R.drawable.gmfour, grandma));
        holder.add(new card_model(R.drawable.gmfive, grandma));
        holder.add(new card_model(R.drawable.gmsix, grandma));
        holder.add(new card_model(R.drawable.gmseven, grandma));
        holder.add(new card_model(R.drawable.gmeight, grandma));
        holder.add(new card_model(R.drawable.gmnine, grandma));
        holder.add(new card_model(R.drawable.gmten, grandma));
        holder.add(new card_model(R.drawable.gmeleven, grandma));
        holder.add(new card_model(R.drawable.gmtwelve, grandma));
        holder.add(new card_model(R.drawable.gmthirteen, grandma));
        holder.add(new card_model(R.drawable.gmfourteen, grandma));
        holder.add(new card_model(R.drawable.gmfifteen, grandma));
        holder.add(new card_model(R.drawable.gmsixteen, grandma));
        holder.add(new card_model(R.drawable.gmseventeen, grandma));
        holder.add(new card_model(R.drawable.gmeighteen, grandma));
        holder.add(new card_model(R.drawable.gmninteen, grandma));
        holder.add(new card_model(R.drawable.gmtwenty, grandma));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void brother_in_law() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String brotherinlaw = "Brother In Law";

        holder.add(new card_model(R.drawable.blone, brotherinlaw));
        holder.add(new card_model(R.drawable.bltwo, brotherinlaw));
        holder.add(new card_model(R.drawable.blthree, brotherinlaw));
        holder.add(new card_model(R.drawable.blfour, brotherinlaw));
        holder.add(new card_model(R.drawable.blfive, brotherinlaw));
        holder.add(new card_model(R.drawable.blsix, brotherinlaw));
        holder.add(new card_model(R.drawable.blseven, brotherinlaw));
        holder.add(new card_model(R.drawable.bleight, brotherinlaw));
        holder.add(new card_model(R.drawable.blnine, brotherinlaw));
        holder.add(new card_model(R.drawable.blten, brotherinlaw));
        holder.add(new card_model(R.drawable.bleleven, brotherinlaw));
        holder.add(new card_model(R.drawable.bltwelve, brotherinlaw));
        holder.add(new card_model(R.drawable.blthirteen, brotherinlaw));
        holder.add(new card_model(R.drawable.blfourteen, brotherinlaw));
        holder.add(new card_model(R.drawable.blfifteen, brotherinlaw));
        holder.add(new card_model(R.drawable.blsixteen, brotherinlaw));
        holder.add(new card_model(R.drawable.blseventeen, brotherinlaw));
        holder.add(new card_model(R.drawable.bleighteen, brotherinlaw));
        holder.add(new card_model(R.drawable.blninteen, brotherinlaw));
        holder.add(new card_model(R.drawable.bltwenty, brotherinlaw));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void sister_in_law() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String brotherinlaw = "Sister In Law";
        holder.add(new card_model(R.drawable.slone, brotherinlaw));
        holder.add(new card_model(R.drawable.sltwo, brotherinlaw));
        holder.add(new card_model(R.drawable.slthree, brotherinlaw));
        holder.add(new card_model(R.drawable.slfour, brotherinlaw));
        holder.add(new card_model(R.drawable.slfive, brotherinlaw));
        holder.add(new card_model(R.drawable.slsix, brotherinlaw));
        holder.add(new card_model(R.drawable.slseven, brotherinlaw));
        holder.add(new card_model(R.drawable.sleight, brotherinlaw));
        holder.add(new card_model(R.drawable.slnine, brotherinlaw));
        holder.add(new card_model(R.drawable.slten, brotherinlaw));
        holder.add(new card_model(R.drawable.sleleven, brotherinlaw));
        holder.add(new card_model(R.drawable.sltwelve, brotherinlaw));
        holder.add(new card_model(R.drawable.slthirteen, brotherinlaw));
        holder.add(new card_model(R.drawable.slfourteen, brotherinlaw));
        holder.add(new card_model(R.drawable.slfifteen, brotherinlaw));
        holder.add(new card_model(R.drawable.slsixteen, brotherinlaw));
        holder.add(new card_model(R.drawable.slseventeen, brotherinlaw));
        holder.add(new card_model(R.drawable.sleighteen, brotherinlaw));
        holder.add(new card_model(R.drawable.slninteen, brotherinlaw));
        holder.add(new card_model(R.drawable.sltwenty, brotherinlaw));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void mother_in_law() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String motherinlaw = "Mother in law";
        holder.add(new card_model(R.drawable.mlone, motherinlaw));
        holder.add(new card_model(R.drawable.mltwo, motherinlaw));
        holder.add(new card_model(R.drawable.mlthree, motherinlaw));
        holder.add(new card_model(R.drawable.mlfour, motherinlaw));
        holder.add(new card_model(R.drawable.mlfive, motherinlaw));
        holder.add(new card_model(R.drawable.mlsix, motherinlaw));
        holder.add(new card_model(R.drawable.mlseven, motherinlaw));
        holder.add(new card_model(R.drawable.mleight, motherinlaw));
        holder.add(new card_model(R.drawable.mlnine, motherinlaw));
        holder.add(new card_model(R.drawable.mlten, motherinlaw));
        holder.add(new card_model(R.drawable.mleleven, motherinlaw));
        holder.add(new card_model(R.drawable.mltwelve, motherinlaw));
        holder.add(new card_model(R.drawable.mlthirteen, motherinlaw));
        holder.add(new card_model(R.drawable.mlfourteen, motherinlaw));
        holder.add(new card_model(R.drawable.mlfifteen, motherinlaw));
        holder.add(new card_model(R.drawable.mlsixteen, motherinlaw));
        holder.add(new card_model(R.drawable.mlseventeen, motherinlaw));
        holder.add(new card_model(R.drawable.mleighteen, motherinlaw));
        holder.add(new card_model(R.drawable.mlninteen, motherinlaw));
        holder.add(new card_model(R.drawable.mltwenty, motherinlaw));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void father() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));
        String father = "Father";
        holder.add(new card_model(R.drawable.fone, father));
        holder.add(new card_model(R.drawable.ftwo, father));
        holder.add(new card_model(R.drawable.fthree, father));
        holder.add(new card_model(R.drawable.ffour, father));
        holder.add(new card_model(R.drawable.ffive, father));
        holder.add(new card_model(R.drawable.fsix, father));
        holder.add(new card_model(R.drawable.fseven, father));
        holder.add(new card_model(R.drawable.feight, father));
        holder.add(new card_model(R.drawable.fnine, father));
        holder.add(new card_model(R.drawable.ften, father));
        holder.add(new card_model(R.drawable.feleven, father));
        holder.add(new card_model(R.drawable.ftwelve, father));
        holder.add(new card_model(R.drawable.fthirteen, father));
        holder.add(new card_model(R.drawable.ffourteen, father));
        holder.add(new card_model(R.drawable.ffifteen, father));
        holder.add(new card_model(R.drawable.fsixteen, father));
        holder.add(new card_model(R.drawable.fseventeen, father));
        holder.add(new card_model(R.drawable.feighteen, father));
        holder.add(new card_model(R.drawable.fninteen, father));
        holder.add(new card_model(R.drawable.ftwentet, father));
        holder.add(new card_model(R.drawable.fone, father));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void mother() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));
        String mother = "Mother";
        holder.add(new card_model(R.drawable.mlone, mother));
        holder.add(new card_model(R.drawable.mltwo, mother));
        holder.add(new card_model(R.drawable.mlthree, mother));
        holder.add(new card_model(R.drawable.mlfour, mother));
        holder.add(new card_model(R.drawable.mlfive, mother));
        holder.add(new card_model(R.drawable.mlsix, mother));
        holder.add(new card_model(R.drawable.mleight, mother));
        holder.add(new card_model(R.drawable.mlnine, mother));
        holder.add(new card_model(R.drawable.mlten, mother));
        holder.add(new card_model(R.drawable.mltwelve, mother));
        holder.add(new card_model(R.drawable.mlthirteen, mother));
        holder.add(new card_model(R.drawable.mlfourteen, mother));
        holder.add(new card_model(R.drawable.mlfifteen, mother));
        holder.add(new card_model(R.drawable.mlsixteen, mother));
        holder.add(new card_model(R.drawable.mlseventeen, mother));
        holder.add(new card_model(R.drawable.mleighteen, mother));
        holder.add(new card_model(R.drawable.mlninteen, mother));
        holder.add(new card_model(R.drawable.mltwenty, mother));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void boyfraind() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String boyfraind = "BoyFraind";
        holder.add(new card_model(R.drawable.bone, boyfraind));
        holder.add(new card_model(R.drawable.btwo, boyfraind));
        holder.add(new card_model(R.drawable.bthree, boyfraind));
        holder.add(new card_model(R.drawable.bfour, boyfraind));
        holder.add(new card_model(R.drawable.bfive, boyfraind));
        holder.add(new card_model(R.drawable.bsix, boyfraind));
        holder.add(new card_model(R.drawable.bseven, boyfraind));
        holder.add(new card_model(R.drawable.beight, boyfraind));
        holder.add(new card_model(R.drawable.bnine, boyfraind));
        holder.add(new card_model(R.drawable.bten, boyfraind));
        holder.add(new card_model(R.drawable.beleven, boyfraind));
        holder.add(new card_model(R.drawable.btwelve, boyfraind));
        holder.add(new card_model(R.drawable.bthirteen, boyfraind));
        holder.add(new card_model(R.drawable.bfourteen, boyfraind));
        holder.add(new card_model(R.drawable.bfifteen, boyfraind));
        holder.add(new card_model(R.drawable.bsixteen, boyfraind));
        holder.add(new card_model(R.drawable.bseventeen, boyfraind));
        holder.add(new card_model(R.drawable.beighteen, boyfraind));
        holder.add(new card_model(R.drawable.bninteen, boyfraind));
        holder.add(new card_model(R.drawable.btwenty, boyfraind));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void girlfraind() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String girlfraind = "GirlFraind";
        holder.add(new card_model(R.drawable.gone, girlfraind));
        holder.add(new card_model(R.drawable.gtwo, girlfraind));
        holder.add(new card_model(R.drawable.gthree, girlfraind));
        holder.add(new card_model(R.drawable.gfour, girlfraind));
        holder.add(new card_model(R.drawable.gfive, girlfraind));
        holder.add(new card_model(R.drawable.gsix, girlfraind));
        holder.add(new card_model(R.drawable.gseveen, girlfraind));
        holder.add(new card_model(R.drawable.geight, girlfraind));
        holder.add(new card_model(R.drawable.gnine, girlfraind));
        holder.add(new card_model(R.drawable.gten, girlfraind));
        holder.add(new card_model(R.drawable.geleven, girlfraind));
        holder.add(new card_model(R.drawable.gtwelve, girlfraind));
        holder.add(new card_model(R.drawable.gthirteen, girlfraind));
        holder.add(new card_model(R.drawable.gfourteen, girlfraind));
        holder.add(new card_model(R.drawable.gfifteen, girlfraind));
        holder.add(new card_model(R.drawable.gsixteen, girlfraind));
        holder.add(new card_model(R.drawable.gseventeen, girlfraind));
        holder.add(new card_model(R.drawable.geighteen, girlfraind));
        holder.add(new card_model(R.drawable.gninteen, girlfraind));
        holder.add(new card_model(R.drawable.gtwenty, girlfraind));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void son() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String son = "Son";
        holder.add(new card_model(R.drawable.gsone, son));
        holder.add(new card_model(R.drawable.gstwo, son));
        holder.add(new card_model(R.drawable.gsthree, son));
        holder.add(new card_model(R.drawable.gsfour, son));
        holder.add(new card_model(R.drawable.gsfive, son));
        holder.add(new card_model(R.drawable.gssix, son));
        holder.add(new card_model(R.drawable.gsseven, son));
        holder.add(new card_model(R.drawable.gseight, son));
        holder.add(new card_model(R.drawable.gsnine, son));
        holder.add(new card_model(R.drawable.gsten, son));
        holder.add(new card_model(R.drawable.gseleven, son));
        holder.add(new card_model(R.drawable.gstwelve, son));
        holder.add(new card_model(R.drawable.gsthirteen, son));
        holder.add(new card_model(R.drawable.gsfourteen, son));
        holder.add(new card_model(R.drawable.gsfifteen, son));
        holder.add(new card_model(R.drawable.gssixteen, son));
        holder.add(new card_model(R.drawable.gsseventeen, son));
        holder.add(new card_model(R.drawable.gseighteen, son));
        holder.add(new card_model(R.drawable.gsninteen, son));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void daughter() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String daughter = "Daughter";
        holder.add(new card_model(R.drawable.gdone, daughter));
        holder.add(new card_model(R.drawable.gdtwo, daughter));
        holder.add(new card_model(R.drawable.gdthree, daughter));
        holder.add(new card_model(R.drawable.gdfour, daughter));
        holder.add(new card_model(R.drawable.gdfive, daughter));
        holder.add(new card_model(R.drawable.gdsix, daughter));
        holder.add(new card_model(R.drawable.gdseven, daughter));
        holder.add(new card_model(R.drawable.gdeight, daughter));
        holder.add(new card_model(R.drawable.gdnine, daughter));
        holder.add(new card_model(R.drawable.gdten, daughter));
        holder.add(new card_model(R.drawable.gdeleven, daughter));
        holder.add(new card_model(R.drawable.gdtwelve, daughter));
        holder.add(new card_model(R.drawable.gdthirteen, daughter));
        holder.add(new card_model(R.drawable.gdfourteen, daughter));
        holder.add(new card_model(R.drawable.gdfifteen, daughter));
        holder.add(new card_model(R.drawable.gdsixteen, daughter));
        holder.add(new card_model(R.drawable.gsseventeen, daughter));
        holder.add(new card_model(R.drawable.gdeighteen, daughter));
        holder.add(new card_model(R.drawable.gdninteen, daughter));
        holder.add(new card_model(R.drawable.gdtwenty, daughter));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void nephew() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String nephew = "Nephew";
        holder.add(new card_model(R.drawable.none, nephew));
        holder.add(new card_model(R.drawable.ntwo, nephew));
        holder.add(new card_model(R.drawable.nthree, nephew));
        holder.add(new card_model(R.drawable.nfour, nephew));
        holder.add(new card_model(R.drawable.nfive, nephew));
        holder.add(new card_model(R.drawable.nsix, nephew));
        holder.add(new card_model(R.drawable.nseven, nephew));
        holder.add(new card_model(R.drawable.neight, nephew));
        holder.add(new card_model(R.drawable.nnine, nephew));
        holder.add(new card_model(R.drawable.nten, nephew));
        holder.add(new card_model(R.drawable.neleven, nephew));
        holder.add(new card_model(R.drawable.ntwelve, nephew));
        holder.add(new card_model(R.drawable.nthirteen, nephew));
        holder.add(new card_model(R.drawable.nfourteen, nephew));
        holder.add(new card_model(R.drawable.nfifteen, nephew));
        holder.add(new card_model(R.drawable.nsixteen, nephew));
        holder.add(new card_model(R.drawable.neventeen, nephew));
        holder.add(new card_model(R.drawable.nninteen, nephew));
        holder.add(new card_model(R.drawable.ntwenty, nephew));

        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void niece() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String niece = "Niece";
        holder.add(new card_model(R.drawable.nione, niece));
        holder.add(new card_model(R.drawable.nitwo, niece));
        holder.add(new card_model(R.drawable.nifour, niece));
        holder.add(new card_model(R.drawable.niseven, niece));
        holder.add(new card_model(R.drawable.ninine, niece));
        holder.add(new card_model(R.drawable.niten, niece));
        holder.add(new card_model(R.drawable.nieleven, niece));
        holder.add(new card_model(R.drawable.nitwelve, niece));
        holder.add(new card_model(R.drawable.nithirteen, niece));
        holder.add(new card_model(R.drawable.nififteen, niece));
        holder.add(new card_model(R.drawable.nifourteen, niece));
        holder.add(new card_model(R.drawable.nisixteen, niece));
        /*holder.add(new card_model(R.drawable.niseventeen, niece));
        holder.add(new card_model(R.drawable.nineeighteen, niece));*/
        holder.add(new card_model(R.drawable.nininteen, niece));
        holder.add(new card_model(R.drawable.nitwenty, niece));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void husband() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));
        String husband = "Husband";
        holder.add(new card_model(R.drawable.huone, husband));
        holder.add(new card_model(R.drawable.hutwo, husband));
        holder.add(new card_model(R.drawable.huthree, husband));
        holder.add(new card_model(R.drawable.hufour, husband));
        holder.add(new card_model(R.drawable.hufive, husband));
        holder.add(new card_model(R.drawable.husix, husband));
        holder.add(new card_model(R.drawable.huseven, husband));
        holder.add(new card_model(R.drawable.hueight, husband));
        holder.add(new card_model(R.drawable.hunine, husband));
        holder.add(new card_model(R.drawable.huten, husband));
        holder.add(new card_model(R.drawable.hueleven, husband));
        holder.add(new card_model(R.drawable.hutwelve, husband));
        holder.add(new card_model(R.drawable.huthirteen, husband));
        holder.add(new card_model(R.drawable.hufourteen, husband));
        holder.add(new card_model(R.drawable.hufifteen, husband));
        holder.add(new card_model(R.drawable.husixteen, husband));
        holder.add(new card_model(R.drawable.huseventeen, husband));
        holder.add(new card_model(R.drawable.hueighteen, husband));
        holder.add(new card_model(R.drawable.huninteen, husband));
        holder.add(new card_model(R.drawable.hutwenty, husband));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void wife() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String wife = "Wife";
        holder.add(new card_model(R.drawable.wone, wife));
        holder.add(new card_model(R.drawable.wtwo, wife));
        holder.add(new card_model(R.drawable.wthree, wife));
        holder.add(new card_model(R.drawable.wfour, wife));
        holder.add(new card_model(R.drawable.wfive, wife));
        holder.add(new card_model(R.drawable.wsix, wife));
        holder.add(new card_model(R.drawable.wseven, wife));
        holder.add(new card_model(R.drawable.weight, wife));
        holder.add(new card_model(R.drawable.wnine, wife));
        holder.add(new card_model(R.drawable.wten, wife));
        holder.add(new card_model(R.drawable.weleven, wife));
        holder.add(new card_model(R.drawable.wtwelve, wife));
        holder.add(new card_model(R.drawable.wthirteen, wife));
        holder.add(new card_model(R.drawable.wfourteen, wife));
        holder.add(new card_model(R.drawable.wfifteen, wife));
        holder.add(new card_model(R.drawable.wsixteen, wife));
        holder.add(new card_model(R.drawable.wseventeen, wife));
        holder.add(new card_model(R.drawable.weighteen, wife));
        holder.add(new card_model(R.drawable.wninteen, wife));
        holder.add(new card_model(R.drawable.wtwenty, wife));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void wedding_anniversary() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String anniv = "Wedding Anniversary";
        holder.add(new card_model(R.drawable.waone, anniv));
        holder.add(new card_model(R.drawable.watwo, anniv));
        holder.add(new card_model(R.drawable.wathree, anniv));
        holder.add(new card_model(R.drawable.wafour, anniv));
        holder.add(new card_model(R.drawable.wafive, anniv));
        holder.add(new card_model(R.drawable.wasix, anniv));
        holder.add(new card_model(R.drawable.waseven, anniv));
        holder.add(new card_model(R.drawable.waeight, anniv));
        holder.add(new card_model(R.drawable.wanine, anniv));
        holder.add(new card_model(R.drawable.waten, anniv));
        holder.add(new card_model(R.drawable.waeleven, anniv));
        holder.add(new card_model(R.drawable.watwelve, anniv));
        holder.add(new card_model(R.drawable.wathirteen, anniv));
        holder.add(new card_model(R.drawable.wafourteen, anniv));
        holder.add(new card_model(R.drawable.wafifteen, anniv));
        holder.add(new card_model(R.drawable.wasixteen, anniv));
        holder.add(new card_model(R.drawable.waseventeen, anniv));
        holder.add(new card_model(R.drawable.waeighteen, anniv));
        holder.add(new card_model(R.drawable.wanine, anniv));
        holder.add(new card_model(R.drawable.waten, anniv));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void best_fraind() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String bestfraind = "BestFraind";
        holder.add(new card_model(R.drawable.fone, bestfraind));
        holder.add(new card_model(R.drawable.ftwo, bestfraind));
        holder.add(new card_model(R.drawable.fthree, bestfraind));
        holder.add(new card_model(R.drawable.ffour, bestfraind));
        holder.add(new card_model(R.drawable.ffive, bestfraind));
        holder.add(new card_model(R.drawable.fsix, bestfraind));
        holder.add(new card_model(R.drawable.fseven, bestfraind));
        holder.add(new card_model(R.drawable.feight, bestfraind));
        holder.add(new card_model(R.drawable.fnine, bestfraind));
        holder.add(new card_model(R.drawable.ften, bestfraind));
        holder.add(new card_model(R.drawable.feleven, bestfraind));
        holder.add(new card_model(R.drawable.ftwelve, bestfraind));
        holder.add(new card_model(R.drawable.fthirteen, bestfraind));
        holder.add(new card_model(R.drawable.ffourteen, bestfraind));
        holder.add(new card_model(R.drawable.ffifteen, bestfraind));
        holder.add(new card_model(R.drawable.fsixteen, bestfraind));
        holder.add(new card_model(R.drawable.fseventeen, bestfraind));
        holder.add(new card_model(R.drawable.seighteen, bestfraind));
        holder.add(new card_model(R.drawable.fninteen, bestfraind));
        holder.add(new card_model(R.drawable.ftwentet, bestfraind));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }


    public void thanks() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String thank = "Thanks";
        holder.add(new card_model(R.drawable.tone, thank));
        holder.add(new card_model(R.drawable.ttwo, thank));
        holder.add(new card_model(R.drawable.tthree, thank));
        holder.add(new card_model(R.drawable.tfour, thank));
        holder.add(new card_model(R.drawable.tfive, thank));
        holder.add(new card_model(R.drawable.tsix, thank));
        holder.add(new card_model(R.drawable.tseven, thank));
        holder.add(new card_model(R.drawable.teight, thank));
        holder.add(new card_model(R.drawable.tnine, thank));
        holder.add(new card_model(R.drawable.tten, thank));
        holder.add(new card_model(R.drawable.televen, thank));
        holder.add(new card_model(R.drawable.ttwelve, thank));
        holder.add(new card_model(R.drawable.tthirteen, thank));
        holder.add(new card_model(R.drawable.tfourteen, thank));
        holder.add(new card_model(R.drawable.tfifteen, thank));
        holder.add(new card_model(R.drawable.tsixteen, thank));
        holder.add(new card_model(R.drawable.tseventeen, thank));
        holder.add(new card_model(R.drawable.teighteen, thank));
        holder.add(new card_model(R.drawable.tninteen, thank));
        holder.add(new card_model(R.drawable.ttwenty, thank));

        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void funny() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String funny = "Funny";
        holder.add(new card_model(R.drawable.fnone, funny));
        holder.add(new card_model(R.drawable.fntwo, funny));
        holder.add(new card_model(R.drawable.fnthree, funny));
        holder.add(new card_model(R.drawable.fnfour, funny));
        holder.add(new card_model(R.drawable.fnfive, funny));
        holder.add(new card_model(R.drawable.fnseven, funny));
        holder.add(new card_model(R.drawable.fneight, funny));
        holder.add(new card_model(R.drawable.fnnine, funny));
        holder.add(new card_model(R.drawable.fnten, funny));
        holder.add(new card_model(R.drawable.fneleven, funny));
        holder.add(new card_model(R.drawable.fntwelve, funny));
        holder.add(new card_model(R.drawable.fnthirteen, funny));
        holder.add(new card_model(R.drawable.fnfourteen, funny));
        holder.add(new card_model(R.drawable.fnfifteen, funny));
        holder.add(new card_model(R.drawable.fnsixteen, funny));
        holder.add(new card_model(R.drawable.fnseventeen, funny));
        holder.add(new card_model(R.drawable.fneighteen, funny));
        holder.add(new card_model(R.drawable.fnninteen, funny));
        holder.add(new card_model(R.drawable.fntwenty, funny));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void happy_birthday() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String happybirthday = "Happy Birthday";
        holder.add(new card_model(R.drawable.haone, happybirthday));
        holder.add(new card_model(R.drawable.hatwo, happybirthday));
        holder.add(new card_model(R.drawable.hathree, happybirthday));
        holder.add(new card_model(R.drawable.hafour, happybirthday));
        holder.add(new card_model(R.drawable.hafive, happybirthday));
        holder.add(new card_model(R.drawable.hasix, happybirthday));
        holder.add(new card_model(R.drawable.haseven, happybirthday));
        holder.add(new card_model(R.drawable.haeight, happybirthday));
        holder.add(new card_model(R.drawable.hanine, happybirthday));
        holder.add(new card_model(R.drawable.haten, happybirthday));
        holder.add(new card_model(R.drawable.haeleven, happybirthday));
        holder.add(new card_model(R.drawable.hatwelve, happybirthday));
        holder.add(new card_model(R.drawable.hathirteen, happybirthday));
        holder.add(new card_model(R.drawable.hafourteen, happybirthday));
        holder.add(new card_model(R.drawable.hafifteen, happybirthday));
        holder.add(new card_model(R.drawable.hasixten, happybirthday));
        holder.add(new card_model(R.drawable.haseventeen, happybirthday));
        holder.add(new card_model(R.drawable.haninteen, happybirthday));
        holder.add(new card_model(R.drawable.hatwenty, happybirthday));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

    public void kids() {


        binding.recyclerview.setLayoutManager(new LinearLayoutManager(category.this));

        String kids = "Kids";
        holder.add(new card_model(R.drawable.kone, kids));
        holder.add(new card_model(R.drawable.ktwo, kids));
        holder.add(new card_model(R.drawable.kthree, kids));
        holder.add(new card_model(R.drawable.kfour, kids));
        holder.add(new card_model(R.drawable.kfive, kids));
        holder.add(new card_model(R.drawable.ksix, kids));
        holder.add(new card_model(R.drawable.kseven, kids));
        holder.add(new card_model(R.drawable.keight, kids));
        holder.add(new card_model(R.drawable.knine, kids));
        holder.add(new card_model(R.drawable.kten, kids));
        holder.add(new card_model(R.drawable.keleven, kids));
        holder.add(new card_model(R.drawable.ktwelve, kids));
        holder.add(new card_model(R.drawable.kthirteen, kids));
        holder.add(new card_model(R.drawable.kfourteen, kids));
        holder.add(new card_model(R.drawable.kfifteen, kids));
        holder.add(new card_model(R.drawable.ksixteen, kids));
        holder.add(new card_model(R.drawable.kseventeen, kids));
        holder.add(new card_model(R.drawable.keighteen, kids));
        holder.add(new card_model(R.drawable.ktwenty, kids));
        holder.add(new card_model(R.drawable.ktwentyone, kids));
        holder.add(new card_model(R.drawable.ktwentytwo, kids));
        holder.add(new card_model(R.drawable.ktwentythree, kids));
        holder.add(new card_model(R.drawable.ktwentyfour, kids));
        holder.add(new card_model(R.drawable.ktwentyfive, kids));
        holder.add(new card_model(R.drawable.ktwentysix, kids));
        holder.add(new card_model(R.drawable.ktwentyseven, kids));
        holder.add(new card_model(R.drawable.ktwentyeight, kids));
        holder.add(new card_model(R.drawable.ktwentynine, kids));
        holder.add(new card_model(R.drawable.kthirty, kids));


        myadapter = new card_adapter(holder, category.this);
        binding.recyclerview.setAdapter(myadapter);

    }

}