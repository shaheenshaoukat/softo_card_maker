package softocardmaker.softoappa.softobook.com;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import Adapter.ctgry_adapter;
import Model.ctgry_model;
import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.FragmentHomefrgmntBinding;

public class homefrgmnt extends Fragment {


    public homefrgmnt() {
        // Required empty public constructor
    }

    private FragmentHomefrgmntBinding binding;
    ArrayList<ctgry_model> holder = new ArrayList<>();
    ctgry_adapter myadapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomefrgmntBinding.inflate(inflater, container, false);
        View view = binding.getRoot();








        /*ads code start*/
        MobileAds.initialize(getContext()
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


        binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        holder.add(new ctgry_model("Cousin's Birthday", R.drawable.cousin));
        holder.add(new ctgry_model("Brother", R.drawable.brother));
        holder.add(new ctgry_model("Sister", R.drawable.sister));
        holder.add(new ctgry_model("Grandapa", R.drawable.grandma));
        holder.add(new ctgry_model("Grandma", R.drawable.grandma));
        holder.add(new ctgry_model("Brother_in_law", R.drawable.brother_in_law));
        holder.add(new ctgry_model("Sister_in_law", R.drawable.sister_in_law));
        holder.add(new ctgry_model("Mother_in_law", R.drawable.mother_in_law));
        holder.add(new ctgry_model("Father", R.drawable.father));
        holder.add(new ctgry_model("Mother",R.drawable.mother));
        holder.add(new ctgry_model("Boyfraind_Birthday", R.drawable.boyfraind));
        holder.add(new ctgry_model("GirlFraind_Birthday", R.drawable.girlfraind));
        holder.add(new ctgry_model("Son's_Birthday", R.drawable.son));
        holder.add(new ctgry_model("Daughter Birthday",R.drawable.daughter));
        holder.add(new ctgry_model("nephew's Birthday", R.drawable.nephew));
        holder.add(new ctgry_model("niece's Birthday", R.drawable.niece));
        holder.add(new ctgry_model("Husband", R.drawable.husband));
        holder.add(new ctgry_model("Wife", R.drawable.wife));
        holder.add(new ctgry_model("Wedding anniversary", R.drawable.anniversary));
        holder.add(new ctgry_model("Best Fraind", R.drawable.best_fraind));
        holder.add(new ctgry_model("Thanks", R.drawable.thanks));
        holder.add(new ctgry_model("Funny", R.drawable.funny));
        holder.add(new ctgry_model("Happy Birthday", R.drawable.happy_birthday));
        holder.add(new ctgry_model("Kids", R.drawable.baby));


        myadapter = new ctgry_adapter(holder, getContext());
        binding.recyclerview.setAdapter(myadapter);


        return view;
    }
}