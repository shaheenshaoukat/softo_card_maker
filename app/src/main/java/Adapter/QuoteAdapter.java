package Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Activities.details_activity;
import Model.QuoteModel;
import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.QuoteitemBinding;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

    ArrayList<QuoteModel> data;
    Context context;

    public QuoteAdapter(ArrayList<QuoteModel> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quoteitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.quote.setText(data.get(position).getQuotes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, details_activity.class);
                intent.putExtra("quotes",data.get(position).getQuotes());
                context.startActivity(intent);
/*
                ((Activity) context).finish();
*/



            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        QuoteitemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=QuoteitemBinding.bind(itemView);
        }
    }
}
