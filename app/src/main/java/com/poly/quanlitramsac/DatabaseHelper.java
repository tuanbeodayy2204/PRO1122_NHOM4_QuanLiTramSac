package com.poly.quanlitramsac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.poly.quanlitramsac.Model.ChargingHistoryWithStationDetails;
import com.poly.quanlitramsac.Model.Station;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "charging_stations.db";
    private static final int DATABASE_VERSION = 9;

    // Table and Column Names
    private static final String TABLE_STATION = "stations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";

    private static final String TABLE_CHARGING_HISTORY = "charging_history";
    private static final String COLUMN_HISTORY_ID = "history_id";
    private static final String COLUMN_STATION_ID = "station_id";
    private static final String COLUMN_CHARGING_HOURS = "charging_hours";
    private static final String COLUMN_TOTAL_PRICE = "total_price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Stations Table
        String CREATE_STATIONS_TABLE = "CREATE TABLE " + TABLE_STATION + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_PRICE + " TEXT, " +
                COLUMN_STATUS + " TEXT, " +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL)";
        db.execSQL(CREATE_STATIONS_TABLE);

        // Create Charging History Table
        String CREATE_CHARGING_HISTORY_TABLE = "CREATE TABLE " + TABLE_CHARGING_HISTORY + " (" +
                COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STATION_ID + " INTEGER, " +
                COLUMN_CHARGING_HOURS + " REAL, " +
                COLUMN_TOTAL_PRICE + " REAL, " +
                "FOREIGN KEY(" + COLUMN_STATION_ID + ") REFERENCES " + TABLE_STATION + "(" + COLUMN_ID + "))";
        db.execSQL(CREATE_CHARGING_HISTORY_TABLE);

        // Add Sample Data
        addSampleStations(db);
    }

    private void addSampleStations(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        // Station 1
        values.put(COLUMN_ADDRESS, "Trạm Sạc 1");
        values.put(COLUMN_QUANTITY, 5);
        values.put(COLUMN_TYPE, "Loại A");
        values.put(COLUMN_PRICE, "5000 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0285);
        values.put(COLUMN_LONGITUDE, 105.8542);
        db.insert(TABLE_STATION, null, values);

        // Station 2
        values.put(COLUMN_ADDRESS, "Trạm Sạc 2");
        values.put(COLUMN_QUANTITY, 3);
        values.put(COLUMN_TYPE, "Loại B");
        values.put(COLUMN_PRICE, "4000 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0275);
        values.put(COLUMN_LONGITUDE, 105.8350);
        db.insert(TABLE_STATION, null, values);

        // Station 3
        values.put(COLUMN_ADDRESS, "Trạm Sạc 3");
        values.put(COLUMN_QUANTITY, 2);
        values.put(COLUMN_TYPE, "Loại C");
        values.put(COLUMN_PRICE, "6000 VND/1h");
        values.put(COLUMN_STATUS, "Không hoạt động");
        values.put(COLUMN_LATITUDE, 21.0350);
        values.put(COLUMN_LONGITUDE, 105.8380);
        db.insert(TABLE_STATION, null, values);
        // Station 4
        values.put(COLUMN_ADDRESS, "Trạm Sạc Hoàng Quốc Việt");
        values.put(COLUMN_QUANTITY, 4);
        values.put(COLUMN_TYPE, "Loại A");
        values.put(COLUMN_PRICE, "7000 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0401);
        values.put(COLUMN_LONGITUDE, 105.7829);
        db.insert(TABLE_STATION, null, values);

        // Station 5
        values.put(COLUMN_ADDRESS, "Trạm Sạc Cầu Giấy");
        values.put(COLUMN_QUANTITY, 3);
        values.put(COLUMN_TYPE, "Loại B");
        values.put(COLUMN_PRICE, "8000 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0333);
        values.put(COLUMN_LONGITUDE, 105.7890);
        db.insert(TABLE_STATION, null, values);

        // Station 6
        values.put(COLUMN_ADDRESS, "Trạm Sạc Mỹ Đình");
        values.put(COLUMN_QUANTITY, 5);
        values.put(COLUMN_TYPE, "Loại C");
        values.put(COLUMN_PRICE, "6000 VND/1h");
        values.put(COLUMN_STATUS, "Không hoạt động");
        values.put(COLUMN_LATITUDE, 21.0285);
        values.put(COLUMN_LONGITUDE, 105.7762);
        db.insert(TABLE_STATION, null, values);

        // Station 7
        values.put(COLUMN_ADDRESS, "Trạm Sạc Tôn Đức Thắng");
        values.put(COLUMN_QUANTITY, 2);
        values.put(COLUMN_TYPE, "Loại A");
        values.put(COLUMN_PRICE, "5500 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0234);
        values.put(COLUMN_LONGITUDE, 105.8442);
        db.insert(TABLE_STATION, null, values);

        // Station 8
        values.put(COLUMN_ADDRESS, "Trạm Sạc Ngọc Khánh");
        values.put(COLUMN_QUANTITY, 6);
        values.put(COLUMN_TYPE, "Loại B");
        values.put(COLUMN_PRICE, "7500 VND/1h");
        values.put(COLUMN_STATUS, "Hoạt động");
        values.put(COLUMN_LATITUDE, 21.0305);
        values.put(COLUMN_LONGITUDE, 105.8203);
        db.insert(TABLE_STATION, null, values);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic for older database versions
        if (oldVersion < DATABASE_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARGING_HISTORY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATION);
            onCreate(db);
        }
    }

    // CRUD Operations
    public void addStation(Station station) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, station.getAddress());
        values.put(COLUMN_QUANTITY, station.getQuantity());
        values.put(COLUMN_TYPE, station.getType());
        values.put(COLUMN_PRICE, station.getPrice());
        values.put(COLUMN_STATUS, station.getStatus());
        values.put(COLUMN_LATITUDE, station.getLatitude());
        values.put(COLUMN_LONGITUDE, station.getLongitude());
        db.insert(TABLE_STATION, null, values);
        db.close();
    }

    public List<Station> getAllStations() {
        List<Station> stationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STATION, null);

        if (cursor.moveToFirst()) {
            do {
                Station station = new Station(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE))
                );
                stationList.add(station);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return stationList;
    }

    public void deleteStationByAddress(String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATION, COLUMN_ADDRESS + " = ?", new String[]{address});
        db.close();
    }

    public void updateStation(Station station) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADDRESS, station.getAddress());
        values.put(COLUMN_QUANTITY, station.getQuantity());
        values.put(COLUMN_TYPE, station.getType());
        values.put(COLUMN_PRICE, station.getPrice());
        values.put(COLUMN_STATUS, station.getStatus());
        values.put(COLUMN_LATITUDE, station.getLatitude());
        values.put(COLUMN_LONGITUDE, station.getLongitude());
        db.update(TABLE_STATION, values, COLUMN_ID + " = ?", new String[]{String.valueOf(station.getId())});
        db.close();
    }

    public void addChargingHistory(int stationId, double chargingHours, double totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATION_ID, stationId);
        values.put(COLUMN_CHARGING_HOURS, chargingHours);
        values.put(COLUMN_TOTAL_PRICE, totalPrice);
        db.insert(TABLE_CHARGING_HISTORY, null, values);
        db.close();
    }

    public List<ChargingHistoryWithStationDetails> getAllChargingHistoryWithStationDetails() {
        List<ChargingHistoryWithStationDetails> historyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT ch.*, st.* FROM " + TABLE_CHARGING_HISTORY + " ch " +
                "JOIN " + TABLE_STATION + " st ON ch." + COLUMN_STATION_ID + " = st." + COLUMN_ID;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Station station = new Station(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ADDRESS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TYPE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_LONGITUDE))
                );

                ChargingHistoryWithStationDetails history = new ChargingHistoryWithStationDetails(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HISTORY_ID)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STATION_ID)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_CHARGING_HOURS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_PRICE)),
                        station
                );
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return historyList;
    }
}
