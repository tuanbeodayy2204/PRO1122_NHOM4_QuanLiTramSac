package com.poly.quanlitramsac.Model;

public class ChargingHistoryWithStationDetails {
    private int historyId;
    private int stationId;
    private double chargingHours;
    private double totalPrice;
    private Station station;

    public ChargingHistoryWithStationDetails(int historyId, int stationId,
                                             double chargingHours, double totalPrice,
                                             Station station) {
        this.historyId = historyId;
        this.stationId = stationId;
        this.chargingHours = chargingHours;
        this.totalPrice = totalPrice;
        this.station = station;
    }

    // Getters
    public int getHistoryId() { return historyId; }
    public int getStationId() { return stationId; }
    public double getChargingHours() { return chargingHours; }
    public double getTotalPrice() { return totalPrice; }
    public Station getStation() { return station; }
}