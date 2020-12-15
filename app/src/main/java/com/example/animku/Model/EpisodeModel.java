package com.example.animku.Model;

public class EpisodeModel {

    String id, episode;

    public EpisodeModel(String id, String episode) {
        this.id = id;
        this.episode = episode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }
}
