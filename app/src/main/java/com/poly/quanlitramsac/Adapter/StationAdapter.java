package com.poly.quanlitramsac.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.quanlitramsac.DatabaseHelper;
import com.poly.quanlitramsac.Model.Station;
import com.poly.quanlitramsac.PaymentActivity;
import com.poly.quanlitramsac.R;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {

    private final Context context;
    private final List<Station> stationList;

    public StationAdapter(Context context, List<Station> stationList) {
        this.context = context;
        this.stationList = stationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_station, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Station station = stationList.get(position);

        // Hiển thị thông tin trạm sạc
        holder.addressTextView.setText("Địa chỉ: " + station.getAddress());
        holder.quantityTextView.setText("Số lượng: " + station.getQuantity() + " trụ");
        holder.priceTextView.setText("Giá: " + station.getPrice() + "/1h");

        // Nút "Đặt trước"
        holder.bookButton.setOnClickListener(v -> showHourSelectionDialog(station));

        // Nút "Chỉ đường"
        holder.directionButton.setOnClickListener(v -> {
            if (station.getLatitude() != 0 && station.getLongitude() != 0) {
                String geoUri = "geo:0,0?q=" + station.getLatitude() + "," + station.getLongitude() + "(" + station.getAddress() + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                intent.setPackage("com.google.android.apps.maps");
                try {
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, "Không tìm thấy ứng dụng bản đồ!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Toạ độ không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });

        // Nhấn giữ để sửa
        holder.itemView.setOnClickListener(v -> showEditDialog(station, position));

        // Nhấn giữ để xóa
        holder.itemView.setOnLongClickListener(v -> {
            showDeleteConfirmationDialog(station, position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    // Dialog chọn số giờ
    private void showHourSelectionDialog(Station station) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Chọn số giờ sạc");

        // Layout tùy chỉnh cho Dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_select_hours, null);
        builder.setView(dialogView);

        EditText hoursEditText = dialogView.findViewById(R.id.hoursEditText);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        AlertDialog dialog = builder.create();

        confirmButton.setOnClickListener(v -> {
            String hoursText = hoursEditText.getText().toString().trim();
            Log.d("HourSelectionDialog", "Entered hours: " + hoursText);

            if (hoursText.isEmpty()) {
                Log.e("HourSelectionDialog", "Hours input is empty!");
                Toast.makeText(context, "Vui lòng nhập số giờ hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int hours = Integer.parseInt(hoursText);
                if (hours <= 0) {
                    Log.e("HourSelectionDialog", "Invalid hours: " + hours);
                    Toast.makeText(context, "Số giờ phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Xử lý giá (loại bỏ ký tự không cần thiết)
                String priceString = station.getPrice().replace(" VND/1h", "").replace(".", "").trim();
                Log.d("HourSelectionDialog", "Processed price string: " + priceString);

                int pricePerHour = Integer.parseInt(priceString);
                Log.d("HourSelectionDialog", "Parsed price per hour: " + pricePerHour);

                // Chuyển sang màn hình thanh toán
                Intent intent = new Intent(context, PaymentActivity.class);
                intent.putExtra("ADDRESS", station.getAddress());
                intent.putExtra("TYPE", station.getType());
                intent.putExtra("PRICE_PER_HOUR", pricePerHour);
                intent.putExtra("STATION_ID", station.getId());
                intent.putExtra("HOURS", hours);
                intent.putExtra("LATITUDE", station.getLatitude());
                intent.putExtra("LONGITUDE", station.getLongitude());
                Log.d("HourSelectionDialog", "Intent data prepared, navigating to PaymentActivity");
                context.startActivity(intent);

                dialog.dismiss();
            } catch (NumberFormatException e) {
                Log.e("HourSelectionDialog", "Failed to parse input!", e);
                Toast.makeText(context, "Dữ liệu không hợp lệ! Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
            }
        });

        cancelButton.setOnClickListener(v -> {
            Log.d("HourSelectionDialog", "Dialog canceled by user");
            dialog.dismiss();
        });
        dialog.show();
    }

    // Dialog sửa trạm
    private void showEditDialog(Station station, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Sửa trạm sạc");

        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_station, null);
        builder.setView(dialogView);

        EditText addressEditText = dialogView.findViewById(R.id.editAddressEditText);
        EditText quantityEditText = dialogView.findViewById(R.id.editQuantityEditText);
        EditText priceEditText = dialogView.findViewById(R.id.editPriceEditText);

        addressEditText.setText(station.getAddress());
        quantityEditText.setText(String.valueOf(station.getQuantity()));
        priceEditText.setText(station.getPrice());

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String newAddress = addressEditText.getText().toString();
            int newQuantity = Integer.parseInt(quantityEditText.getText().toString());
            String newPrice = priceEditText.getText().toString();

            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            station.setAddress(newAddress);
            station.setQuantity(newQuantity);
            station.setPrice(newPrice);
            databaseHelper.updateStation(station);

            stationList.set(position, station);
            notifyItemChanged(position);
            Toast.makeText(context, "Đã cập nhật trạm sạc!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    // Dialog xóa trạm
    private void showDeleteConfirmationDialog(Station station, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xóa trạm sạc");
        builder.setMessage("Bạn có chắc muốn xóa trạm sạc này?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            databaseHelper.deleteStationByAddress(station.getAddress());

            stationList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xóa trạm sạc!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView addressTextView, quantityTextView, priceTextView;
        Button directionButton, bookButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            directionButton = itemView.findViewById(R.id.directionButton);
            bookButton = itemView.findViewById(R.id.bookButton);
        }
    }
}
