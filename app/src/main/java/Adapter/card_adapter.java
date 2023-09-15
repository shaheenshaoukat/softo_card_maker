package Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Activities.DatabaseHelper;
import Activities.details_activity;
import softocardmaker.softoappa.softobook.com.Favourite;
import softocardmaker.softoappa.softobook.com.R;
import Model.card_model;
import softocardmaker.softoappa.softobook.com.databinding.CardLayoutBinding;

public class card_adapter extends RecyclerView.Adapter<card_adapter.viewholder> {

    ArrayList<card_model> data;
    Context context;
    private int lastPosition = -1;

    DatabaseHelper myDb;
    private boolean isFavorite = false;


    public card_adapter(ArrayList<card_model> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_layout, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {

        myDb = new DatabaseHelper(context);
        myDb.getWritableDatabase();

        holder.binding.image.setImageResource(data.get(position).getImage());





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, details_activity.class);
                intent.putExtra("image", String.valueOf(data.get(position).getImage()));
                intent.putExtra("name",data.get(position).getName());
                context.startActivity(intent);



            }
        });
        holder.binding.favorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isFavorite = !isFavorite;
                if (isFavorite) {
                   holder.binding.favorit.setImageResource(R.drawable.ic_baseline_favorite_24);
                    holder.binding.favorit.setEnabled(false);

                    boolean IsInserted = myDb.insertData(Integer.parseInt(String.valueOf(data.get(position).getImage())));

                    if (IsInserted) {
                        Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Favourite.class);
                        context.startActivity(intent);


                    } else {
                        Toast.makeText(context, "Data not inserted", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    holder.binding.favorit.setImageResource(R.drawable.ic_baseline_favorite_border_24); // Set the appropriate non-favorite image resource

                }

            }
        });








        setAnimation(holder.itemView,position);



        }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        CardLayoutBinding binding;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            binding = CardLayoutBinding.bind(itemView);
        }
    }


    public void setAnimation(View viewToAnimate, int position ){

        if (position > lastPosition) {

            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;


        }


    }
}
