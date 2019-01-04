package redbus.assignment.com.myapplication.retrofit;

import redbus.assignment.com.myapplication.modelclass.InventoryModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by guna on 03-01-2019.
 */

public interface APIInterface {

    @GET("/test/generated.json")
    Call<InventoryModel> getVehicleDetails();

}