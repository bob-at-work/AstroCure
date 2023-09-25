package com.astrocure.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.astrocure.databinding.ItemConsultationDurationPriceBinding;
import com.astrocure.models.ConsultationPriceModel;

import java.util.List;

public class ConsultationPriceAdapter extends RecyclerView.Adapter<ConsultationPriceAdapter.PriceViewHolder> {
    Context context;
    List<ConsultationPriceModel> list;

    public ConsultationPriceAdapter(Context context, List<ConsultationPriceModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConsultationDurationPriceBinding binding = ItemConsultationDurationPriceBinding.inflate(LayoutInflater.from(context), parent, false);
        return new PriceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        ConsultationPriceModel model = list.get(position);
        holder.binding.pricePerMin.setText(model.getPricePerMin());
        holder.binding.price.setText(model.getPrice());
        holder.binding.time.setText(model.getTime());
        holder.binding.pricePerMin.setBackgroundColor(Color.parseColor(model.getBackgroundColor()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        ItemConsultationDurationPriceBinding binding;

        public PriceViewHolder(ItemConsultationDurationPriceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
