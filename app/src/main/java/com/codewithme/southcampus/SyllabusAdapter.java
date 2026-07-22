package com.codewithme.southcampus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SyllabusAdapter extends RecyclerView.Adapter<SyllabusAdapter.ViewHolder> {

    private final List<String> syllabusItems;
    private final Context context;

    public SyllabusAdapter(List<String> syllabusItems, Context context) {
        this.syllabusItems = syllabusItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = syllabusItems.get(position);
        holder.textView.setText(item);
        holder.itemView.setOnClickListener(v ->
                        Toast.makeText(context, "Selected: " + item, Toast.LENGTH_SHORT).show()
                // Optionally: open PDF or detailed activity here
        );
    }

    @Override
    public int getItemCount() {
        return syllabusItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
