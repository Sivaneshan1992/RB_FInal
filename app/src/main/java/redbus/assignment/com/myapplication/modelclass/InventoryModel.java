package redbus.assignment.com.myapplication.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by guna on 03-01-2019.
 */

public class InventoryModel {

    @SerializedName("inventory")
    @Expose
    private List<Inventory> inventory = null;
    @SerializedName("busType")
    @Expose
    private BusType busType;
    @SerializedName("travels")
    @Expose
    private Travels travels;

    public List<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<Inventory> inventory) {
        this.inventory = inventory;
    }

    public BusType getBusType() {
        return busType;
    }

    public void setBusType(BusType busType) {
        this.busType = busType;
    }

    public Travels getTravels() {
        return travels;
    }

    public void setTravels(Travels travels) {
        this.travels = travels;
    }


    public class BusType {

        @SerializedName("1")
        @Expose
        private String _1;
        @SerializedName("2")
        @Expose
        private String _2;
        @SerializedName("3")
        @Expose
        private String _3;
        @SerializedName("4")
        @Expose
        private String _4;
        @SerializedName("5")
        @Expose
        private String _5;

        public String get1() {
            return _1;
        }

        public void set1(String _1) {
            this._1 = _1;
        }

        public String get2() {
            return _2;
        }

        public void set2(String _2) {
            this._2 = _2;
        }

        public String get3() {
            return _3;
        }

        public void set3(String _3) {
            this._3 = _3;
        }

        public String get4() {
            return _4;
        }

        public void set4(String _4) {
            this._4 = _4;
        }

        public String get5() {
            return _5;
        }

        public void set5(String _5) {
            this._5 = _5;
        }

    }

    public class Inventory {

        @SerializedName("startTime")
        @Expose
        private String startTime;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("reachesLocationIn")
        @Expose
        private Integer reachesLocationIn;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("nosRating")
        @Expose
        private Integer nosRating;
        @SerializedName("seats")
        @Expose
        private Seats seats;
        @SerializedName("bus")
        @Expose
        private Bus bus;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Integer getReachesLocationIn() {
            return reachesLocationIn;
        }

        public void setReachesLocationIn(Integer reachesLocationIn) {
            this.reachesLocationIn = reachesLocationIn;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Integer getNosRating() {
            return nosRating;
        }

        public void setNosRating(Integer nosRating) {
            this.nosRating = nosRating;
        }

        public Seats getSeats() {
            return seats;
        }

        public void setSeats(Seats seats) {
            this.seats = seats;
        }

        public Bus getBus() {
            return bus;
        }

        public void setBus(Bus bus) {
            this.bus = bus;
        }

    }
    public class Seats {

        @SerializedName("seatsRemaining")
        @Expose
        private Integer seatsRemaining;
        @SerializedName("baseFare")
        @Expose
        private Integer baseFare;
        @SerializedName("discount")
        @Expose
        private Integer discount;

        public Integer getSeatsRemaining() {
            return seatsRemaining;
        }

        public void setSeatsRemaining(Integer seatsRemaining) {
            this.seatsRemaining = seatsRemaining;
        }

        public Integer getBaseFare() {
            return baseFare;
        }

        public void setBaseFare(Integer baseFare) {
            this.baseFare = baseFare;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
            this.discount = discount;
        }

    }

    public class Travels {

        @SerializedName("1")
        @Expose
        private String _1;
        @SerializedName("2")
        @Expose
        private String _2;
        @SerializedName("3")
        @Expose
        private String _3;
        @SerializedName("4")
        @Expose
        private String _4;
        @SerializedName("5")
        @Expose
        private String _5;
        @SerializedName("6")
        @Expose
        private String _6;
        @SerializedName("7")
        @Expose
        private String _7;
        @SerializedName("8")
        @Expose
        private String _8;
        @SerializedName("9")
        @Expose
        private String _9;
        @SerializedName("10")
        @Expose
        private String _10;

        public String get1() {
            return _1;
        }

        public void set1(String _1) {
            this._1 = _1;
        }

        public String get2() {
            return _2;
        }

        public void set2(String _2) {
            this._2 = _2;
        }

        public String get3() {
            return _3;
        }

        public void set3(String _3) {
            this._3 = _3;
        }

        public String get4() {
            return _4;
        }

        public void set4(String _4) {
            this._4 = _4;
        }

        public String get5() {
            return _5;
        }

        public void set5(String _5) {
            this._5 = _5;
        }

        public String get6() {
            return _6;
        }

        public void set6(String _6) {
            this._6 = _6;
        }

        public String get7() {
            return _7;
        }

        public void set7(String _7) {
            this._7 = _7;
        }

        public String get8() {
            return _8;
        }

        public void set8(String _8) {
            this._8 = _8;
        }

        public String get9() {
            return _9;
        }

        public void set9(String _9) {
            this._9 = _9;
        }

        public String get10() {
            return _10;
        }

        public void set10(String _10) {
            this._10 = _10;
        }

    }

    public class Bus {

        @SerializedName("travelsName")
        @Expose
        private Integer travelsName;
        @SerializedName("regno")
        @Expose
        private String regno;
        @SerializedName("type")
        @Expose
        private Integer type;

        public Integer getTravelsName() {
            return travelsName;
        }

        public void setTravelsName(Integer travelsName) {
            this.travelsName = travelsName;
        }

        public String getRegno() {
            return regno;
        }

        public void setRegno(String regno) {
            this.regno = regno;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

    }
}
