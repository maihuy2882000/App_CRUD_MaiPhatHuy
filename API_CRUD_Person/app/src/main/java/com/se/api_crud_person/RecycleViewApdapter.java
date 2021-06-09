package com.se.api_crud_person;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;

public class RecycleViewApdapter extends RecyclerView.Adapter<RecycleViewApdapter.RecycleViewHolder> {

    private LinkedList<com.se.api_crud_person.Student> linkedList;
    private LayoutInflater inflater;
    private Context context;
    private AlertDialog alertDialog;
    public RecycleViewApdapter(LinkedList<com.se.api_crud_person.Student> linkedList, Context context) {
        inflater = LayoutInflater.from(context);
        this.linkedList = linkedList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycleview_student, parent, false);
        return new RecycleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        com.se.api_crud_person.Student student = linkedList.get(position);
        holder.tvStudentId.setText(String.valueOf(student.getId()));
        holder.tvName.setText(student.getName());
        holder.tvClass.setText(student.getClassroom());
        holder.tvStatus.setText(student.getStatus());
        holder.tvWorking.setText(student.getWorking());

    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder{

        private RecycleViewApdapter adapter;

        TextView tvStudentId, tvName, tvClass, tvStatus, tvWorking;

        public RecycleViewHolder(View view, RecycleViewApdapter adapter) {
            super(view);

            tvWorking = view.findViewById(R.id.tvWorking);
            tvStatus = view.findViewById(R.id.tvStatus);
            tvClass = view.findViewById(R.id.tvClass);
            tvStudentId = view.findViewById(R.id.tvStudentId);
            tvName = view.findViewById(R.id.tvName);
            Button btnDelete = view.findViewById(R.id.btnDelete);
            Button btnUpdate = view.findViewById(R.id.btnUpdate);

            String url = "https://60adf1fe80a61f001733205f.mockapi.io/student";

            this.adapter = adapter;

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    com.se.api_crud_person.Student element = linkedList.get(position);


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are You Sure To Delete Student");
                    builder.setCancelable(true);

                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DeleteDataToJsonAPI(url, element.getId());
                                }
                            });

                    builder.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });




            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    com.se.api_crud_person.Student element = linkedList.get(position);
                    Intent intent = new Intent(context, com.se.api_crud_person.UpdateStudentActivity.class);

                    intent.putExtra("id", element.getId());
                    intent.putExtra("name", element.getName());
                    intent.putExtra("classroom", element.getClassroom());
                    intent.putExtra("status", element.getStatus());
                    intent.putExtra("working", element.getWorking());
                    context.startActivity(intent);
                }
            });

            ///
        }

        private void DeleteDataToJsonAPI(String url, int id) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    linkedList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }

    }


}
