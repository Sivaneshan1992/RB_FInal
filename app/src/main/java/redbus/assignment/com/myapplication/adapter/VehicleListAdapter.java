package redbus.assignment.com.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import redbus.assignment.com.myapplication.R;
import redbus.assignment.com.myapplication.modelclass.InventoryModel;

/**
 * Created by guna on 03-01-2019.
 */

public class VehicleListAdapter extends RecyclerView.Adapter<VehicleListAdapter.ItemHolder> {

    private List<InventoryModel.Inventory> inventories = new ArrayList<>();

    private static InventoryModel inventoryModel = new InventoryModel();

    private static JSONObject travelsNameJson, busTypeJson;

    private static Context context;

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ItemHolder.newInstance(parent);
    }


    public VehicleListAdapter(Context context, List<InventoryModel.Inventory> inventory, InventoryModel inventoryModel) {
        VehicleListAdapter.context = context;
        VehicleListAdapter.inventoryModel = inventoryModel;
        this.inventories = inventory;

        try {
            travelsNameJson = new JSONObject(new Gson().toJson(inventoryModel.getTravels()));
            busTypeJson = new JSONObject(new Gson().toJson(inventoryModel.getBusType()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {

        holder.bind(inventories.get(position));
    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }


    static class ItemHolder extends RecyclerView.ViewHolder {


        private  TextView busarrivetime, starttime, travelsname, vehiclenumber, bustype, reachtime, avgrating,
                totalrating, seatsleft, basefare;

        static ItemHolder newInstance(ViewGroup container) {
            View root = LayoutInflater.from(container.getContext()).inflate(getLayoutResourceId(), container, false);

            return new ItemHolder(root);
        }

        private ItemHolder(View itemView) {
            super(itemView);

            busarrivetime = itemView.findViewById(R.id.txt_reacheslocationin);
            starttime = itemView.findViewById(R.id.txt_starttime);
            travelsname = itemView.findViewById(R.id.txt_travelsname);
            vehiclenumber = itemView.findViewById(R.id.txt_vehicleno);
            bustype = itemView.findViewById(R.id.txt_vehicletype);
            reachtime = itemView.findViewById(R.id.txt_endtime);
            avgrating = itemView.findViewById(R.id.txt_avg_ratings);
            totalrating = itemView.findViewById(R.id.txt_totalratings);
            seatsleft = itemView.findViewById(R.id.txt_seatsleft);
            basefare = itemView.findViewById(R.id.txt_fare);
        }

        void bind(InventoryModel.Inventory inventory) {


            try {
                busarrivetime.setText(String.format("%s %s", String.valueOf(inventory.getReachesLocationIn()), context.getString(R.string.min)));
                starttime.setText(inventory.getStartTime());

                vehiclenumber.setText(inventory.getBus().getRegno());

                bustype.setText(busTypeJson.getString(String.valueOf(inventory.getBus().getType())));
                travelsname.setText(travelsNameJson.getString(String.valueOf(inventory.getBus().getTravelsName())));

                reachtime.setText(String.format("%s %s %s", context.getString(R.string.duration), String.valueOf(inventory.getDuration() / 60), context.getString(R.string.hrs)));

                avgrating.setText(String.valueOf(inventory.getRating()));

                /*Changing color based on rating*/
                if (inventory.getRating() >= 4.0)
                    avgrating.setBackgroundResource(R.drawable.roundcorner_layout_green);
                else
                    avgrating.setBackgroundResource(R.drawable.roundcorner_layout_yellow);


                totalrating.setText(String.format("%s %s", String.valueOf(inventory.getNosRating()), context.getString(R.string.ratings)));

                /*Changing color based on seats available*/
                if (inventory.getSeats().getSeatsRemaining() < 10) {
                    seatsleft.setText(String.format("%s %s %s", context.getString(R.string.only), String.valueOf(inventory.getSeats().getSeatsRemaining()), context.getString(R.string.seatsleft)));
                    seatsleft.setTextColor(context.getResources().getColor(R.color.colorYellow));
                } else {
                    seatsleft.setText(String.format("%s %s", String.valueOf(inventory.getSeats().getSeatsRemaining()), context.getString(R.string.seatsleft)));
                    seatsleft.setTextColor(context.getResources().getColor(R.color.colorDarkGrey));
                }


                basefare.setText(String.format("%s %s", String.valueOf(inventory.getSeats().getBaseFare() - inventory.getSeats().getDiscount()), context.getString(R.string.symbol_rupee)));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        private static int getLayoutResourceId() {
            int selectedLayoutResource;

            selectedLayoutResource = R.layout.container_item_layout;

            return selectedLayoutResource;
        }


    }

    /*Filtering data based on duration and fare*/
    public void refreshdata(List<InventoryModel.Inventory> inventory) {
        if (inventory != null && inventory.size() > 0) {
            this.inventoryModel = inventoryModel;
            notifyDataSetChanged();
        }

    }
}