package com.edson.studentcallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.edson.studentcallroll.R;
import com.edson.studentcallroll.adapter.assistancelist.AssistanceListAdapter;
import com.edson.studentcallroll.model.AssistanceSheetDto;
import com.edson.studentcallroll.viewmodel.AssistanceListViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AssistanceListActivity extends AppCompatActivity {

    private RecyclerView rclVAssists;

    private ArrayList<AssistanceSheetDto> assistanceSheetDto;
    private AssistanceListAdapter adapter;
    private AssistanceListViewModel viewModel;
    private SharedPreferences shPref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistance_list);
        setupComponents();
        getAssistanceList();
    }

    public void setupComponents() {
        rclVAssists = findViewById(R.id.rclVAssists);
        viewModel = new ViewModelProvider(AssistanceListActivity.this).get(AssistanceListViewModel.class);
        shPref = AssistanceListActivity.this.getSharedPreferences("StudentCallRoll_ShPref", 0);
        rclVAssists.setLayoutManager(new LinearLayoutManager(AssistanceListActivity.this));
    }

    public void getAssistanceList() {
        viewModel.getAssistanceList(shPref.getString("auth_token", null),
                Integer.parseInt(shPref.getString("identifier_number", null)))
                .observe(AssistanceListActivity.this, (Observer<String>) jsonList -> {
                    if (viewModel.getHttpStatusCode() == 401 || viewModel.getHttpStatusCode() == 403) {
                        singOut();
                    } else {
                        if (jsonList != null) {
                            Type listAssistance = new TypeToken<ArrayList<AssistanceSheetDto>>() {
                            }.getType();
                            assistanceSheetDto = (ArrayList<AssistanceSheetDto>) new Gson().fromJson(jsonList, listAssistance);
                            adapter = new AssistanceListAdapter(AssistanceListActivity.this, assistanceSheetDto);
                            rclVAssists.addItemDecoration(new DividerItemDecoration(AssistanceListActivity.this,
                                    LinearLayoutManager.VERTICAL));
                            rclVAssists.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(AssistanceListActivity.this, "La liste n'est pas dispo !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void singOut() {
        SharedPreferences.Editor editor = shPref.edit();
        editor.clear();//delete all data from shared preferences
        editor.commit();//commit the changes
        intent = new Intent(AssistanceListActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}