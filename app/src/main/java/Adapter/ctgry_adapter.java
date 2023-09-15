package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import softocardmaker.softoappa.softobook.com.R;
import Activities.category;
import Model.ctgry_model;
import softocardmaker.softoappa.softobook.com.databinding.ItemLayoutBinding;

public class ctgry_adapter extends RecyclerView.Adapter<ctgry_adapter.myviewholder> {

    ArrayList<ctgry_model> data;
    Context context;
    private int lastPosition = -1;

    public ctgry_adapter(ArrayList<ctgry_model> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.name.setText(data.get(position).getName());
        holder.binding.image.setImageResource(data.get(position).getImg());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, category.class);
                intent.putExtra("name",data.get(position).getName());
                context.startActivity(intent);
            }
        });

        setAnimation(holder.itemView,position);



        }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            binding=ItemLayoutBinding.bind(itemView);
        }
    }


    public void setAnimation(View viewToAnimate, int position ) {

        if (position > lastPosition) {

            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;


        }


    }}
