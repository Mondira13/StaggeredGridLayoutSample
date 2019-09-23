package com.example.staggeredgridlayoutsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.staggeredgridlayoutsample.adapter.DashboardRecyclerAdapter;
import com.example.staggeredgridlayoutsample.response.DashboardResponse;
import com.example.staggeredgridlayoutsample.model.Item;
import com.example.staggeredgridlayoutsample.staggergridsdk.APIClient;
import com.example.staggeredgridlayoutsample.staggergridsdk.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DashboardRecyclerAdapter dashboardRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        loadDataFromAPI();

    }


    private void loadDataFromAPI() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<DashboardResponse> call = apiInterface.getDashboardItems();
        call.enqueue(new Callback<DashboardResponse>() { // its retrive tab names
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        List<Item> retrievedData = response.body().getItems();
                        if(!retrievedData.isEmpty()){
                            addRecyclerAdapter(retrievedData);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure..!!! Tab name not uploaded..", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    private void addRecyclerAdapter(List<Item> myDataSet) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        dashboardRecyclerAdapter = new DashboardRecyclerAdapter(MainActivity.this, myDataSet);
        recyclerView.setAdapter(dashboardRecyclerAdapter);
    }

}

