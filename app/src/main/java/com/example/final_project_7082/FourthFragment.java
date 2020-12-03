package com.example.final_project_7082;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.ArrayList;
import java.util.List;

public class FourthFragment extends Fragment {

    EditText editText;
    Button btAdd,btReset;
    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;
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
/*
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

 */
        appDatabase = Room.databaseBuilder(view.getContext(), AppDatabase.class,"word database")
                .allowMainThreadQueries().build();

        eventDao=appDatabase.getEventDao();
        editText = view.findViewById(R.id.edit_text);
        btAdd = view.findViewById(R.id.bt_add);
        btReset = view.findViewById(R.id.bt_reset);
        recyclerView = view.findViewById(R.id.recycler_view);
        eventList = eventDao.getAllEvent();

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //maybe problem
//        adapter = new MainAdapter((Activity) view.getContext(),eventList);
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sTitle = editText.getText().toString().trim();

                if(!sTitle.equals("")){
                    Event data = new Event(sTitle,"today","",1,1,2000);
                    eventDao.addEvent(data);
                    editText.setText("");
                    eventList.clear();
                    eventList.addAll(eventDao.getAllEvent());
                    adapter.notifyDataSetChanged();
                }
            }
        });

//        btReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                eventDao.deleteAll();
//                eventList.clear();
//                eventList.addAll(eventDao.getAllEvent());
//                adapter.notifyDataSetChanged();
//            }
//        });





    }
}