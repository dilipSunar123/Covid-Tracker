package com.example.iii;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements Filterable {

    Context mContext;
    List<Item> itemList;
    List<Item> itemListFull;

    public CustomAdapter(Context context, ArrayList<Item> itemList) {
        mContext = context;
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.state_report_template, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        Item currentItem = itemList.get(position);

        holder.stateName.setText(currentItem.getRegion());
        holder.activeCasesView.setText("Active Case : " + currentItem.getActiveCases());
        holder.newInfectedView.setText("Newly Infected : " + currentItem.getNewInfected());
        holder.recoveredView.setText("Recovered : " + currentItem.getRecovered());
        holder.newRecoveredView.setText("Newly Recovered : " + currentItem.getNewRecovered());
        holder.deceasedView.setText("Deceased : " + currentItem.getDeceased());
        holder.newDeceasedView.setText("Newly Deceased : " + currentItem.getNewDeceased());
        holder.totalInfectedView.setText("Total Infected : " + currentItem.getTotalInfected());

        holder.activeCasesView.setTextColor(mContext.getResources().getColor(R.color.red));
        holder.newInfectedView.setTextColor(mContext.getResources().getColor(R.color.red));
        holder.recoveredView.setTextColor(mContext.getResources().getColor(R.color.green));
        holder.newRecoveredView.setTextColor(mContext.getResources().getColor(R.color.green));
        holder.deceasedView.setTextColor(mContext.getResources().getColor(R.color.red));
        holder.newDeceasedView.setTextColor(mContext.getResources().getColor(R.color.red));
        holder.totalInfectedView.setTextColor(mContext.getResources().getColor(R.color.red));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public Filter getFilter() {
        return filterFn;
    }

    private Filter filterFn = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Item> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String filteredPattern = constraint.toString().toLowerCase().trim();

                for (Item item: itemListFull) {
                    if (item.getRegion().toLowerCase().trim().contains(filteredPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName;
        TextView activeCasesView;
        TextView newInfectedView;
        TextView recoveredView;
        TextView newRecoveredView;
        TextView deceasedView;
        TextView newDeceasedView;
        TextView totalInfectedView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.stateNameView);
            activeCasesView = itemView.findViewById(R.id.activeCasesView);
            newInfectedView = itemView.findViewById(R.id.newInfectedView);
            recoveredView = itemView.findViewById(R.id.recoveredView);
            newRecoveredView = itemView.findViewById(R.id.newRecoveredView);
            deceasedView = itemView.findViewById(R.id.deceasedView);
            newDeceasedView = itemView.findViewById(R.id.newDeceasedView);
            totalInfectedView = itemView.findViewById(R.id.totalInfectedView);
        }
    }

}
