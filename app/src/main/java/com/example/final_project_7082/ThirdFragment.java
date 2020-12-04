package com.example.final_project_7082;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;

import com.example.final_project_7082.Model.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment implements com.andexert.calendarlistview.library.DatePickerController {

    EditText editText;
    Button addEvent;
    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;
    AppDatabase appDatabase;
    EventDao eventDao;
    DayPickerView dayPickerView;
    View view;
  
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_third, container, false);
        dayPickerView = (DayPickerView) view.findViewById(R.id.pickerView);
        dayPickerView.setController(this);
        FloatingActionButton bbutton = (FloatingActionButton) ((MainActivity) getActivity()).findViewById(R.id.HomeButton);
        bbutton.setVisibility(View.VISIBLE);
        addEvent = view.findViewById(R.id.button2);
        appDatabase = Room.databaseBuilder(view.getContext(), AppDatabase.class, "word database")
                .allowMainThreadQueries().build();
        eventDao = appDatabase.getEventDao();
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_new_event);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                final EditText editYear = dialog.findViewById(R.id.event_year);
                final EditText editMonth = dialog.findViewById(R.id.event_month);
                final EditText editDay = dialog.findViewById(R.id.event_day);
                final EditText editTitle = dialog.findViewById(R.id.edit_event_title);
                final EditText editContent = dialog.findViewById(R.id.edit_event_content);
                Button save = dialog.findViewById(R.id.bt_save_event);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                        String tmp1 = editYear.getText().toString().trim();
                        String tmp2 = editMonth.getText().toString().trim();
                        String tmp3 = editDay.getText().toString().trim();
                        String tmp4 = editTitle.getText().toString().trim();
                        String tmp5 = editContent.getText().toString().trim();

                        Event data = new Event(tmp4, timestamp, tmp5, Integer.parseInt(tmp3), Integer.parseInt(tmp2), Integer.parseInt(tmp1));
                        eventDao.addEvent(data);
                        List<Event> list = eventDao.getAllEvent();
                        String text = "";
                        for (int i = 0; i < list.size(); i++) {
                            Event myevent = list.get(i);
                            text += myevent.getId() + ": " + myevent.getTitle() + "\n" + myevent.getDay() + "\n"
                                    + myevent.getMonth() + "\n" + myevent.getTime() + "\n\n\n";

                        }
                        Log.d("ALL my event: \n", text);
                    }
                });
            }
        });
        return view;
    }

 /*
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_thirdFragment_to_fourthFragment);
            }
        });
*/


    @Override
    public int getMaxYear()
    {
        return 2020;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {

        Log.e("Day Selected", day + " / " + month + " / " + year);
        Bundle bundle = new Bundle();
        bundle.putInt("day", day);
        bundle.putInt("month", month);
        bundle.putInt("year", year);
        Navigation.findNavController(view).navigate(R.id.action_thirdFragment_to_fourthFragment, bundle);

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