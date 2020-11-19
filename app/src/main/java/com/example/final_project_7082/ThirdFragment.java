package com.example.final_project_7082;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;

import com.example.final_project_7082.Model.*;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements com.andexert.calendarlistview.library.DatePickerController {

    EditText editText;
    Button btAdd,btReset;
    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;
    AppDatabase appDatabase;
    JournalDao journalDao;
    EventDao eventDao;
    DayPickerView dayPickerView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        dayPickerView = (DayPickerView) view.findViewById(R.id.pickerView);
        dayPickerView.setController(this);
        return view;
    }

    @Override
    public int getMaxYear()
    {
        return 2020;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {
        Log.e("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = Room.databaseBuilder(view.getContext(), AppDatabase.class,"word database")
                .allowMainThreadQueries().build();
        eventDao=appDatabase.getEventDao();
        eventList = eventDao.getAllEvent();
        Log.d("Event", eventList.toString());
    }



}