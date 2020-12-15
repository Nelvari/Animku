package com.example.animku.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animku.Adapter.AnimeTerbaruAdapter;
import com.example.animku.Adapter.AnimeTerpopulerAdapter;
import com.example.animku.Model.AnimeModel;
import com.example.animku.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class HomeFragment extends Fragment {

    private Realm realm;
    private ArrayList mAnimeList;
    RecyclerView rvListTerbaru, rvListTerpopuler;
    AnimeTerbaruAdapter animeTerbaruAdapter;
    AnimeTerpopulerAdapter animeTerpopulerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("ANIMKU");

        rvListTerbaru = view.findViewById(R.id.rvListTerbaru);
        rvListTerpopuler = view.findViewById(R.id.rvListTerpopuler);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        animeTerbaruAdapter = new AnimeTerbaruAdapter(getContext(), mAnimeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvListTerbaru.setLayoutManager(layoutManager);
        rvListTerbaru.setAdapter(animeTerbaruAdapter);

        animePopuler();

        return view;
    }

    private void animePopuler() {
        animeTerpopulerAdapter = new AnimeTerpopulerAdapter(getContext(), mAnimeList);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvListTerpopuler.setLayoutManager(linearLayoutManager);
        rvListTerpopuler.setAdapter(animeTerpopulerAdapter);
    }

}
