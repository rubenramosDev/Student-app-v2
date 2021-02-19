package com.edson.studentcallroll.adapter.assistancelist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edson.studentcallroll.R;
import com.edson.studentcallroll.model.AssistanceSheetDto;

import java.text.SimpleDateFormat;

public class AssistanceListHolder extends RecyclerView.ViewHolder {

    private TextView txtVModule;
    private TextView txtVSemestre;
    private TextView txtVTeacherName;
    private TextView txtVDateAttendance;
    private TextView txtVStatusAttendance;
    private TextView txtVStatusSheet;

    public AssistanceListHolder(@NonNull View itemView) {
        super(itemView);
        txtVModule = itemView.findViewById(R.id.txtVModule);
        txtVSemestre = itemView.findViewById(R.id.txtVSemestre);
        txtVTeacherName = itemView.findViewById(R.id.txtVTeacherName);
        txtVDateAttendance = itemView.findViewById(R.id.txtVDateAttendance);
        txtVStatusAttendance = itemView.findViewById(R.id.txtVStatusAttendance);
        txtVStatusSheet = itemView.findViewById(R.id.txtVStatusSheet);
    }

    public void setDetails(AssistanceSheetDto assistanceSheetDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("'le' dd/MM/yy 'à' HH'h'mm");
        txtVModule.setText(assistanceSheetDto.getModule());
        txtVSemestre.setText("Semestre " + assistanceSheetDto.getSemestre());
        txtVTeacherName.setText(assistanceSheetDto.getTeacherName());
        txtVDateAttendance.setText(sdf.format(assistanceSheetDto.getMyAttendanceTime()));
        txtVStatusAttendance.setText("Mon statut : " + assistanceSheetDto.getStatusOfMyAssistance());
        txtVStatusSheet.setText("Feuille statut : " + assistanceSheetDto.getAssistanceSheetStatus());
    }
}
