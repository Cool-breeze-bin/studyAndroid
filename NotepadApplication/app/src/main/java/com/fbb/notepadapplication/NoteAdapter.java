package com.fbb.notepadapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NoteAdapter extends ArrayAdapter<Note> {
    private final int resourceId;

    public NoteAdapter(@NonNull Context context, int resourceId, @NonNull List<Note> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Note note = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.noteTitle = view.findViewById(R.id.note_title);
            viewHolder.noteDate = view.findViewById(R.id.note_date);
            viewHolder.noteContent = view.findViewById(R.id.note_content);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.noteTitle.setText(note.getTitle());
        viewHolder.noteDate.setText(note.getDate());
        viewHolder.noteContent.setText(note.getContent());
        return view;
    }

    class ViewHolder{
        TextView noteTitle;
        TextView noteDate;
        TextView noteContent;
    }
}
