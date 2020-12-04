package com.example.final_project_7082;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.final_project_7082.Model.AppDatabase;
import com.example.final_project_7082.Model.Event;
import com.example.final_project_7082.Model.EventDao;
import com.example.final_project_7082.Model.Journal;
import com.example.final_project_7082.Model.JournalDao;

import java.util.ArrayList;
import java.util.List;

public class FourthFragment extends Fragment {


    RecyclerView recyclerView;
    List<Event> EventlList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    EventAdapter adapter;
    AppDatabase appDatabase;
    EventDao eventDao;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = Room.databaseBuilder(view.getContext(), AppDatabase.class,"word database")
                .allowMainThreadQueries().build();
        Bundle bundle = this.getArguments();
        int day = bundle.getInt("day");
        int year = bundle.getInt("year");
        int month = bundle.getInt("month");
        Log.e("4 fragment: ", day + " ; " + month + " ; " + year);

        eventDao = appDatabase.getEventDao();
        recyclerView = view.findViewById(R.id.recycler_view);
        EventlList = eventDao.selectEvents(day);

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //maybe problem
        adapter = new EventAdapter((Activity) view.getContext(),EventlList);
        recyclerView.setAdapter(adapter);




    }
}