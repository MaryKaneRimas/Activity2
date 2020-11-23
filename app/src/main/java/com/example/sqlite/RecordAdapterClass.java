package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordAdapterClass extends RecyclerView.Adapter<RecordAdapterClass.ViewHolder> {

    List<RecordModelClass> record;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public RecordAdapterClass(List<RecordModelClass> record, Context context) {
        this.record = record;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.record_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final RecordModelClass recordModelClass = record.get(position);

        holder.textViewID.setText(Integer.toString(recordModelClass.getId()));
        holder.editText_studentid.setText(recordModelClass.getStudentid());
        holder.editText_fullname.setText(recordModelClass.getFullname());
        holder.editText_address.setText(recordModelClass.getAddress());

        holder.button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringStudentid = holder.editText_studentid.getText().toString();
                String stringFullname = holder.editText_fullname.getText().toString();
                String stringAddress = holder.editText_address.getText().toString();

                databaseHelperClass.updateRecord(new RecordModelClass(recordModelClass.getId(),stringStudentid,stringFullname,stringAddress));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteRecord(recordModelClass.getId());
                record.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return record.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_studentid;
        EditText editText_fullname;
        EditText editText_address;
        Button button_update;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_studentid = itemView.findViewById(R.id.edittext_studentid);
            editText_fullname = itemView.findViewById(R.id.edittext_fullname);
            editText_address = itemView.findViewById(R.id.edittext_address);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_update = itemView.findViewById(R.id.button_update);

        }
    }
}
