package com.example.animku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.animku.Fragment.MainFragment;
import com.example.animku.Model.AnimeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class GetAnime extends AppCompatActivity {

    private ArrayList mAnimeList = new ArrayList<AnimeModel>();
    private Realm realm;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);

        progressDialog = new ProgressDialog(this);
        mAnimeList.clear();

        progressDialog.setTitle("Please wait...");
        progressDialog.show();
        AndroidNetworking.get("https://animendo.000webhostapp.com/API/viewanime.php")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("result");
                            for (int i = 0; i < data.length(); i++) {
                                AnimeModel model = new AnimeModel();
                                JSONObject object = data.getJSONObject(i);
                                model.setId(object.getString("id"));
                                model.setJudul(object.getString("judul"));
                                model.setGenre(object.getString("genre"));
                                model.setJmlepisode(object.getString("jmlepisode"));
                                model.setTipe(object.getString("tipe"));
                                model.setDurasi(object.getString("durasi"));
                                model.setStudio(object.getString("studio"));
                                model.setSkor(object.getString("skor"));
                                model.setStatus(object.getString("status"));
                                model.setTayang(object.getString("tayang"));
                                model.setSinopsis(object.getString("sinopsis"));
                                model.setSinonim(object.getString("sinonim"));
                                model.setGambar(object.getString("gambar"));
                                mAnimeList.add(model);
                            }
                            Intent intent = new Intent(GetAnime.this, MainFragment.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                            realm.executeTransactionAsync(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realma) {
                                    realma.insertOrUpdate(mAnimeList);
                                }
                            }, new Realm.Transaction.OnSuccess() {
                                @Override
                                public void onSuccess() {
                                    Log.e("RBA", "Realm onSuccess: success insert");
                                    RealmResults<AnimeModel> homeModels = realm.where(AnimeModel.class).findAll();
                                    Log.d("RBA", "Realm Size From Api : " + homeModels.size());
                                }
                            }, new Realm.Transaction.OnError() {
                                @Override
                                public void onError(Throwable error) {
                                    Log.e("RBA", "Realm onError: " + error.getLocalizedMessage());
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}
