package com.example.animku.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Model.EpisodeModel;
import com.example.animku.R;
import com.example.animku.VideoPlayer;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder>{

    private ArrayList<EpisodeModel> dataList;
    View viewku;

    public EpisodeAdapter(ArrayList<EpisodeModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.episode_view, parent, false);
        return new EpisodeAdapter.ViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tvEpisode.setText(dataList.get(position).getEpisode());
        if (position % 2 == 1){
            holder.linear.setBackgroundColor(Color.parseColor("#000000"));
        }else {
            holder.linear.setBackgroundColor(Color.parseColor("#1a284a"));
        }
        holder.cvEpisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), VideoPlayer.class);
                intent.putExtra("position", dataList.get(position).getPosition());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvEpisode;
        LinearLayout linear;
        CardView cvEpisode;

        ViewHolder(View itemView) {
            super(itemView);
            cvEpisode = itemView.findViewById(R.id.cvEpisode);
            linear = itemView.findViewById(R.id.linear);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);

        }
    }

}
