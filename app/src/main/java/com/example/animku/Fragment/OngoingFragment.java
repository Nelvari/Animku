package com.example.animku.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Adapter.GenreAdapter;
import com.example.animku.Adapter.OngoingAdapter;
import com.example.animku.Model.GenreModel;
import com.example.animku.Model.OngoingModel;
import com.example.animku.R;
import com.example.animku.SearchAnime;
import com.example.animku.R;

import java.util.ArrayList;

public class OngoingFragment extends Fragment {

    RecyclerView rv;
    ArrayList<OngoingModel> day;
    OngoingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Animku");

        rv = view.findViewById(R.id.rvOngoing);

        day = new ArrayList<>();
        day.add(new OngoingModel("Senin"));
        day.add(new OngoingModel("Selasa"));
        day.add(new OngoingModel("Rabu"));
        day.add(new OngoingModel("Kamis"));
        day.add(new OngoingModel("Jumat"));
        day.add(new OngoingModel("Sabtu"));
        day.add(new OngoingModel("Minggu"));
        day.add(new OngoingModel("Random"));

        adapter = new OngoingAdapter(day);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.dashboard_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mSearch){
            Intent intent = new Intent(getContext(), SearchAnime.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
