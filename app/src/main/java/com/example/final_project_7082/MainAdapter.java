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
import com.example.final_project_7082.Model.Journal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    List<Journal> journalList;
    private Activity context;
    private AppDatabase appDatabase;

    public MainAdapter(Activity context, List<Journal> journalList){
        this.context = context;
        this.journalList = journalList;
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
            Journal data = journalList.get(position);

        appDatabase = Room.databaseBuilder(context, AppDatabase.class,"word database")
                .allowMainThreadQueries().build();
        holder.textView.setText(data.getTitle());
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Journal j = journalList.get(holder.getAdapterPosition());
                 final int sID = j.getId();
                 String title = j.getTitle();
                 String content = j.getContent();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width,height);
                dialog.show();

                final EditText editTitle = dialog.findViewById(R.id.edit_title);
                final EditText editContent = dialog.findViewById(R.id.edit_content);
                Button save = dialog.findViewById(R.id.bt_update);

                editTitle.setText(title);
                editContent.setText(content);

                save.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       dialog.dismiss();
                        String timestamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
                        String tmp1 = editTitle.getText().toString().trim();
                        String tmp2 = editContent.getText().toString().trim();
                        appDatabase.getJournalDao().update(sID,tmp1,tmp2);
                        journalList.clear();
                        journalList.addAll(appDatabase.getJournalDao().getAllJournal());
                        notifyDataSetChanged();
                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Journal j = journalList.get(holder.getAdapterPosition());
                int sID = j.getId();
                appDatabase.getJournalDao().delete(sID);
                journalList.clear();
                journalList.addAll(appDatabase.getJournalDao().getAllJournal());
                notifyDataSetChanged();

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Journal j = journalList.get(holder.getAdapterPosition());
                final int sID = j.getId();
                final String title = j.getTitle();
                final String content = j.getContent();
                final String time = j.getTime();
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
                        share.putExtra(Intent.EXTRA_TEXT, title + content + time);
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
        return journalList.size();
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
