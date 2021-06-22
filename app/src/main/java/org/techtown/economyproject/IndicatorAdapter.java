package org.techtown.economyproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.ViewHolder>{
    ArrayList<EconomicIndicators> items = new ArrayList<EconomicIndicators>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.indicator_item , parent , false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EconomicIndicators item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(EconomicIndicators item){
        items.add(item);
    }

    public void setItem(ArrayList<EconomicIndicators> items){
        this.items = items;
    }
    public EconomicIndicators getItem(int position){
        return items.get(position);
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView){
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(EconomicIndicators item){
            textView.setText(item.KEYSTAT_NAME);
            textView2.setText(item.DATA_VALUE + " "+item.UNIT_NAME);
        }
    }
}
