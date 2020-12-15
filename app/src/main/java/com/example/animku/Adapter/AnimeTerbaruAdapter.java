package com.example.animku.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AnimeTerbaruAdapter extends RecyclerView.Adapter<AnimeTerbaruAdapter.TerbaruViewHolder>{

    Realm realm;
    private List<AnimeModel> dataList;
    Context mContext;
    View viewku;

    public AnimeTerbaruAdapter(Context mContext, ArrayList<AnimeModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public TerbaruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.anime_view, parent, false);
        return new TerbaruViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull TerbaruViewHolder holder, int position) {
        Realm.init(holder.itemView.getContext());
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        if (dataList.get(position).getStatus().equals("Ongoing") || dataList.get(position).getStatus().equals("Finished Airing")) {
            holder.cvAnimeBaru.setVisibility(View.VISIBLE);
            holder.tvJudul.setText(dataList.get(position).getJudul());
            holder.tvEpisode.setText(dataList.get(position).getJmlepisode() + " episode");
            holder.tvType.setText(dataList.get(position).getTipe());
            Glide.with(holder.itemView.getContext()).load(dataList.get(position).getGambar()).into(holder.ivFoto);
            holder.cvAnimeBaru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Intent in = new Intent(holder.itemView.getContext(), EpisodeAnime.class);
//                in.putExtra("judul", dataList.get(position).getJudul());
//                holder.itemView.getContext().startActivity(in);
                }
            });
        }else {
            holder.cvAnimeBaru.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TerbaruViewHolder extends RecyclerView.ViewHolder{
        private TextView tvJudul, tvEpisode, tvType;
        CardView cvAnimeBaru;
        ImageView ivFoto;

        TerbaruViewHolder(View itemView) {
            super(itemView);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            cvAnimeBaru = itemView.findViewById(R.id.cvAnime);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvEpisode = itemView.findViewById(R.id.tvEpisode);
            tvType = itemView.findViewById(R.id.tvType);

        }
    }

}
