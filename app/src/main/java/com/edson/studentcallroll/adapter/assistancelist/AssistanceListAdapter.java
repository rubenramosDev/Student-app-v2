package com.edson.studentcallroll.adapter.assistancelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edson.studentcallroll.R;
import com.edson.studentcallroll.model.AssistanceSheetDto;

import java.util.ArrayList;

public class AssistanceListAdapter extends RecyclerView.Adapter<AssistanceListHolder> {

    private Context context;
    private ArrayList<AssistanceSheetDto> assistanceSheetDtos;

    public AssistanceListAdapter(Context context, ArrayList<AssistanceSheetDto> assistanceSheetDtos) {
        this.context = context;
        this.assistanceSheetDtos = assistanceSheetDtos;
    }

    @NonNull
    @Override
    public AssistanceListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new AssistanceListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssistanceListHolder holder, int position) {
        AssistanceSheetDto assistanceSheetDto = assistanceSheetDtos.get(position);
        holder.setDetails(assistanceSheetDto);
    }

    @Override
    public int getItemCount() {
        return assistanceSheetDtos.size();
    }
}
