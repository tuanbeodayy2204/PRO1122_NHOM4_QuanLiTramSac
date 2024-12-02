package com.poly.quanlitramsac.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.poly.quanlitramsac.Model.ChargingHistoryWithStationDetails;
import com.poly.quanlitramsac.R;
import java.util.List;
import java.text.DecimalFormat;

public class ChargingHistoryAdapter extends RecyclerView.Adapter<ChargingHistoryAdapter.ChargingHistoryViewHolder> {
    private List<ChargingHistoryWithStationDetails> chargingHistoryList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ChargingHistoryWithStationDetails history);
    }

    public ChargingHistoryAdapter(List<ChargingHistoryWithStationDetails> chargingHistoryList, OnItemClickListener listener) {
        this.chargingHistoryList = chargingHistoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChargingHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_charging_history, parent, false);
        return new ChargingHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChargingHistoryViewHolder holder, int position) {
        ChargingHistoryWithStationDetails history = chargingHistoryList.get(position);
        holder.bind(history, listener);
    }

    @Override
    public int getItemCount() {
        return chargingHistoryList.size();
    }

    static class ChargingHistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvStationAddress, tvChargingHours, tvTotalPrice, tvStationType;
        private DecimalFormat decimalFormat = new DecimalFormat("#.##");

        public ChargingHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStationAddress = itemView.findViewById(R.id.tv_station_address);
            tvChargingHours = itemView.findViewById(R.id.tv_charging_hours);
            tvTotalPrice = itemView.findViewById(R.id.tv_total_price);
            tvStationType = itemView.findViewById(R.id.tv_station_type);
        }

        public void bind(final ChargingHistoryWithStationDetails history, final OnItemClickListener listener) {
            tvStationAddress.setText(history.getStation().getAddress());
            tvChargingHours.setText(decimalFormat.format(history.getChargingHours()) + "giờ");
            tvTotalPrice.setText(history.getTotalPrice() + " VND");
            tvStationType.setText(history.getStation().getType());

            itemView.setOnClickListener(v -> listener.onItemClick(history));
        }
    }

    public void updateData(List<ChargingHistoryWithStationDetails> newList) {
        chargingHistoryList = newList;
        notifyDataSetChanged();
    }
}