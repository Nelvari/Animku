package com.example.animku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.animku.Adapter.EpisodeAdapter;
import com.example.animku.Model.AnimeModel;
import com.example.animku.Model.EpisodeModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class VideoPlayer extends AppCompatActivity {

    private RecyclerView rvListEpisode;
    private EpisodeAdapter episodeAdapter;
    private ArrayList<EpisodeModel> listEpisodes;
    TextView tvJudul, tvInfo;
    private Realm realm;
    private List<AnimeModel> mAnimeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        rvListEpisode = findViewById(R.id.rvListEpisode);
        tvJudul = findViewById(R.id.tvJudul);
        tvInfo = findViewById(R.id.tvInfo);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        if (mAnimeList == null)
            mAnimeList = new ArrayList<>();

        RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
        mAnimeList.addAll(homeModels);

        Bundle extras = getIntent().getExtras();
        final int position = extras.getInt("position");

        listEpisodes = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(mAnimeList.get(position).getJmlepisode()); i++) {
            listEpisodes.add(new EpisodeModel(String.valueOf(i), mAnimeList.get(position).getJudul() + " eps " + i, position));
        }

        episodeAdapter = new EpisodeAdapter(listEpisodes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListEpisode.setLayoutManager(layoutManager);
        rvListEpisode.setAdapter(episodeAdapter);

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VideoPlayer.this, InfoAnime.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

    }
}