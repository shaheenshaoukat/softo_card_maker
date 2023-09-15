package softocardmaker.softoappa.softobook.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

import Adapter.QuoteAdapter;
import Model.QuoteModel;
import softocardmaker.softoappa.softobook.com.databinding.ActivityQuotesBinding;

public class Quotes extends AppCompatActivity {

    String quote;
    ActivityQuotesBinding binding;
    ArrayList<QuoteModel> holder=new ArrayList<>();
    QuoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quote=getIntent().getStringExtra("quote");

        cusinBirthday();


    }

    public void cusinBirthday(){
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        holder.add(new QuoteModel(" You are just one of the best cousins I could have had. HBD!"));
        holder.add(new QuoteModel("I feel so lucky to have you as my cousin,so Happy Birthday!"));
        holder.add(new QuoteModel(" Happy birthday, my dear!I’m wishing you much greatness and joy on your special day"));
        holder.add(new QuoteModel(" Happy birthday to my best friend, who just sohappens to also be my cousin!"));
        holder.add(new QuoteModel(" Happy birthday to you, and thank you for all the happy memories, my dear."));
        holder.add(new QuoteModel(" I’m sending the very best birthday wishes your way today, my adorable little cousin"));
        holder.add(new QuoteModel("Happy birthday, my dear, and I hope that today brings you all the happiness in the world"));
        holder.add(new QuoteModel("Happy birthday to my partner in crime!Or should I say, my fabulous sidekick!"));
        holder.add(new QuoteModel("In you, I found a true friend and a brother!Happy Birthday"));
        holder.add(new QuoteModel(" Happy birthday, cousin! May your life be blessed with joy and success."));

        adapter=new QuoteAdapter(holder,this);
        binding.recyclerview.setAdapter(adapter);
    }
}