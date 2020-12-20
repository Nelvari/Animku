package com.example.animku.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Model.GenreModel;
import com.example.animku.R;

import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder>{

    private ArrayList<GenreModel> dataList;
    View viewku;

    public GenreAdapter(ArrayList<GenreModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.genre_view, parent, false);
        return new GenreAdapter.GenreViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.tvGenre.setText(dataList.get(position).getGenre());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class GenreViewHolder extends RecyclerView.ViewHolder{
        private TextView tvGenre;
        CardView cvGenre;

        GenreViewHolder(View itemView) {
            super(itemView);
            cvGenre = itemView.findViewById(R.id.cvGenre);
            tvGenre = itemView.findViewById(R.id.tvGenre);

        }
    }

}
