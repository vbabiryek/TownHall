package com.example.vivianbabiryekulumba.townhall.retrofit;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vivianbabiryekulumba.townhall.R;
import com.example.vivianbabiryekulumba.townhall.models.CommBoard;
import com.example.vivianbabiryekulumba.townhall.network_service.NetworkService;
import com.example.vivianbabiryekulumba.townhall.recycler.CommBoardAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuRetroFragment extends Fragment {

    private static final String TAG = "MainActivity.class";
    private RecyclerView recyclerView;
    private List<CommBoard> zipCodeList;
    Context context;

    public QuRetroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qu_retro, container, false);
        recyclerView = view.findViewById(R.id.recyclerviewQu);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final NetworkService networkService = retrofit.create(NetworkService.class);

        final Call<List<CommBoard>> commBoardCall = networkService.getQuCommBoardData();
        commBoardCall.enqueue(new Callback<List<CommBoard>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommBoard>> call, @NonNull Response<List<CommBoard>> response) {
                if (response.isSuccessful()) {
                    zipCodeList = response.body();
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    CommBoardAdapter adapter = new CommBoardAdapter(zipCodeList, context);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    Log.d(TAG, "onResponse: success");
                    Log.d(TAG, "onResponse: " + zipCodeList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CommBoard>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: not successful");
            }
        });

        return view;
    }

}
