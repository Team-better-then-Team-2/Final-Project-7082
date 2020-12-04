package com.example.final_project_7082;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.final_project_7082.Model.AppDatabase;
import com.example.final_project_7082.Model.Event;
import com.example.final_project_7082.Model.Journal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    List<Event> EventlList;
    private Activity context;
    private AppDatabase appDatabase;

    public EventAdapter(Activity context, List<Event> EventlList){
        this.context = context;
        this.EventlList = EventlList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Event data = EventlList.get(position);

        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"word database")
                .allowMainThreadQueries().build();
        holder.textView.setText(data.getTitle());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event e = EventlList.get(holder.getAdapterPosition());

                final int sID = e.getId();
                String title = e.getTitle();
                String content = e.getContent();
                int day = e.getDay();
                int month = e.getMonth();
                int year = e.getYear();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_new_event);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                final EditText editYear = dialog.findViewById(R.id.event_year);
                final EditText editMonth = dialog.findViewById(R.id.event_month);
                final EditText editDay = dialog.findViewById(R.id.event_day);
                final EditText editTitle = dialog.findViewById(R.id.edit_event_title);
                final EditText editContent = dialog.findViewById(R.id.edit_event_content);

                Button save = dialog.findViewById(R.id.bt_save_event);

                editTitle.setText(title);
                editContent.setText(content);
                editYear.setText(String.valueOf(year));
                editMonth.setText(String.valueOf(month));
                editDay.setText(String.valueOf(day));

                save.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                        String tmp1 = editYear.getText().toString().trim();
                        String tmp2 = editMonth.getText().toString().trim();
                        String tmp3 = editDay.getText().toString().trim();
                        String tmp4 = editTitle.getText().toString().trim();
                        String tmp5 = editContent.getText().toString().trim();
                        appDatabase.getEventDao().update(sID,tmp4,tmp5,Integer.parseInt(tmp3),Integer.parseInt(tmp2),Integer.parseInt(tmp1));
                        EventlList.clear();
                        EventlList.addAll(appDatabase.getEventDao().getAllEvent());
                        notifyDataSetChanged();
                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Event e = EventlList.get(holder.getAdapterPosition());
                int sID = e.getId();
                appDatabase.getEventDao().delete(sID);
                EventlList.clear();
                EventlList.addAll(appDatabase.getEventDao().getAllEvent());
                notifyDataSetChanged();

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Event e = EventlList.get(holder.getAdapterPosition());
                final int sID = e.getId();
                final String title = e.getTitle();
                final String content = e.getContent();
                final String time = e.getTime();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_display_journal);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();
                final TextView myTitle = dialog.findViewById(R.id.text_title);
                final TextView myContent = dialog.findViewById(R.id.text_content);
                final TextView myTime = dialog.findViewById(R.id.text_time);
                Button close = dialog.findViewById(R.id.bt_close);
                Button share = dialog.findViewById(R.id.bt_share);
                myTitle.setText(title);
                myContent.setText(content);
                myTime.setText(time);
                share.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.putExtra(Intent.EXTRA_TEXT, "Title: " + title + " Content: " + content + " Time: " + time);
                        share.setType("text/plain");
                        context.startActivity(Intent.createChooser(share, "Share Event"));
                    }
                });
                close.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return EventlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btEdit,btDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}