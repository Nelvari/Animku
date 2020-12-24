package com.example.animku.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Fragment.OngoingFragment;
import com.example.animku.Model.OngoingModel;
import com.example.animku.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.OngoingViewHolder> {

    private ArrayList<OngoingModel> dataList;
    View viewku;

    public OngoingAdapter(ArrayList<OngoingModel> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        viewku = layoutInflater.inflate(R.layout.activity_ongoingday_view, parent, false);
        return new OngoingAdapter.OngoingViewHolder(viewku);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingViewHolder holder, int position) {
        holder.tvDay.setText(dataList.get(position).getDay());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class OngoingViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDay;
        CardView cvOngoing;

        OngoingViewHolder(View itemView) {
            super(itemView);
            cvOngoing = itemView.findViewById(R.id.cvOngoing);
            tvDay = itemView.findViewById(R.id.day);

        }
    }
}
