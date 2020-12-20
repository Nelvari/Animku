package com.example.animku.Model;

public class EpisodeModel {

    String id, episode;
    int position;

    public EpisodeModel(String id, String episode, int position) {
        this.id = id;
        this.episode = episode;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
