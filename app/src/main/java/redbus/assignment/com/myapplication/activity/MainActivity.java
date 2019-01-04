package redbus.assignment.com.myapplication.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import redbus.assignment.com.myapplication.R;
import redbus.assignment.com.myapplication.Utils.SortDuration;
import redbus.assignment.com.myapplication.Utils.SortFare;
import redbus.assignment.com.myapplication.adapter.VehicleListAdapter;
import redbus.assignment.com.myapplication.modelclass.InventoryModel;
import redbus.assignment.com.myapplication.retrofit.APIClient;
import redbus.assignment.com.myapplication.retrofit.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private  ProgressDialog dialog;
    private RecyclerView recyclerView;
    private VehicleListAdapter vehicleListAdapter;
    private List<InventoryModel.Inventory> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadToolbar();
        initializeUIComponents();

          /*fetching value from server*/
        if(isOnline(this))
            getVehciledetails();
        else
            Toast.makeText(MainActivity.this,getResources().getString(R.string.no_network),Toast.LENGTH_LONG).show();
    }

    private void loadToolbar()
    {
        try
        {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(getResources().getString(R.string.toolbar_title));

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private  void initializeUIComponents()
    {
        dialog = new ProgressDialog(this);
        recyclerView =  findViewById(R.id.listview_vehicle);

        /*Initially it wont visible, visible only after data loaded*/
        recyclerView.setVisibility(View.GONE);
    }

    private void getVehciledetails() {

        dialog.setMessage("Loading..");
        dialog.show();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<InventoryModel> call1 = apiInterface.getVehicleDetails();
        call1.enqueue(new Callback<InventoryModel>() {
            @Override
            public void onResponse(Call<InventoryModel> call, Response<InventoryModel> response) {

                dialog.cancel();

                if (response.body() != null) {

                    try {

                        InventoryModel inventoryModel = response.body();

                        if(inventoryModel != null && inventoryModel.getInventory() != null) {

                            datalist = inventoryModel.getInventory();
                            recyclerView.setVisibility(View.VISIBLE);
                            vehicleListAdapter = new VehicleListAdapter(MainActivity.this, datalist, inventoryModel);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerView.setAdapter(vehicleListAdapter);
                        }
                        else
                            Toast.makeText(MainActivity.this,getResources().getString(R.string.no_data),Toast.LENGTH_LONG).show();


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,getResources().getString(R.string.server_error),Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<InventoryModel> call, Throwable t) {
                dialog.cancel();
                call.cancel();
                Toast.makeText(MainActivity.this,getResources().getString(R.string.server_error),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {


            case R.id.action_filter:

                if(isOnline(this))
                {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.sort_alert_dialog, null);
                dialogBuilder.setView(dialogView);


                final AlertDialog dialog = dialogBuilder.create();

                final RadioGroup rgrp =  dialogView.findViewById(R.id.rgrp_sort);
                Button btn_ok =  dialogView.findViewById(R.id.btn_ok);
                Button btn_cancel =  dialogView.findViewById(R.id.btn_cancel);

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                        int rgrpid=rgrp.getCheckedRadioButtonId();

                        RadioButton radioButton = dialogView. findViewById(rgrpid);

                        /*Sorting based on selected value*/
                        if(Integer.parseInt(radioButton.getTag().toString()) == 1)
                            Collections.sort(datalist,new SortFare());
                        else
                            Collections.sort(datalist,new SortDuration());

                        /*refreshing data in adapter class*/
                        if(vehicleListAdapter !=null)
                            vehicleListAdapter.refreshdata(datalist);

                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
            else
                Toast.makeText(MainActivity.this,getResources().getString(R.string.no_network),Toast.LENGTH_LONG).show();



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager
                cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }


}
