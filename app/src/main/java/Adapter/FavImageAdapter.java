package Adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import Activities.DatabaseHelper;
import Activities.details_activity;
import Activities.home;
import Model.FavImageModel;
import softocardmaker.softoappa.softobook.com.Favourite;
import softocardmaker.softoappa.softobook.com.R;
import softocardmaker.softoappa.softobook.com.databinding.ItemListBinding;

public class FavImageAdapter extends RecyclerView.Adapter<FavImageAdapter.ViewHolder> {

    ArrayList<FavImageModel> mylist;
    DatabaseHelper myDb;
    Context context;

    public FavImageAdapter(ArrayList<FavImageModel> mylist, DatabaseHelper myDb, Context context) {
        this.mylist = mylist;
        this.myDb = myDb;
        this.context = context;
    }

   /* public FavImageAdapter(ArrayList<FavImageModel> mylist) {
        this.mylist = mylist;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.rcImage.setImageResource(Integer.parseInt(mylist.get(position).getName()));




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,details_activity.class);
                intent.putExtra("image", String.valueOf(mylist.get(position).getName()));
                intent.putExtra("name","Favourite");

                context.startActivity(intent);
                ((Activity) context).finish();

            }
        });
        
        holder.binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                myDb = new DatabaseHelper(v.getContext());

                myDb.getWritableDatabase();

                Integer deletedRow =  myDb.deleteData(mylist.get(position).getId());

                if(deletedRow>0)
                {
                    Toast.makeText(v.getContext(), "Data Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(v.getContext(), Favourite.class);
                    holder.binding.button.getContext().startActivity(intent);
                    ((Activity)holder.binding.rcImage.getContext()).finish();


                }
                else
                {
                    Toast.makeText(v.getContext(), "Data not Deleted", Toast.LENGTH_SHORT).show();



                }





            }
        });

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ItemListBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemListBinding.bind(itemView);
        }
    }

}
