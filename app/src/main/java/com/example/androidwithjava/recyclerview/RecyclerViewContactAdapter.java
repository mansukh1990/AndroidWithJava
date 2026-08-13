package com.example.androidwithjava.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidwithjava.R;

import java.util.ArrayList;

public class RecyclerViewContactAdapter extends RecyclerView.Adapter<RecyclerViewContactAdapter.ViewHolder> {

    Context context;
    ArrayList<ContactModel> arrContacts;

    RecyclerViewContactAdapter(Context context, ArrayList<ContactModel> arrContacts) {
        this.context = context;
        this.arrContacts = arrContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_contact, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ContactModel contactModel = arrContacts.get(position);

        holder.imgContact.setImageResource(contactModel.img);
        holder.txtName.setText(contactModel.name);
        holder.txtNumber.setText(contactModel.number);

        holder.editContact.setOnClickListener(view -> {

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_add_update_contact);

            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }

            EditText editTextName = dialog.findViewById(R.id.edtName);
            EditText editTextNumber = dialog.findViewById(R.id.edtNumber);
            Button btnAdd = dialog.findViewById(R.id.btnAdd);
            TextView textViewContact = dialog.findViewById(R.id.tvAddContact);

            editTextName.setText(contactModel.name);
            editTextNumber.setText(contactModel.number);

            textViewContact.setText("Update Contact");
            btnAdd.setText("Update");

            btnAdd.setOnClickListener(view1 -> {

                String name = editTextName.getText().toString().trim(), number = editTextNumber.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(context, "Please Enter Contact Name!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (number.isEmpty()) {
                    Toast.makeText(context, "Please Enter Mobile Numer!", Toast.LENGTH_SHORT).show();

                }
                arrContacts.set(position, new ContactModel(contactModel.img, name, number));
                notifyItemChanged(position);

                Toast.makeText(context, "Update Contact Successfully", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            });
            dialog.setCancelable(false);
            dialog.show();
        });

        holder.deleteContact.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Delete Contact")
                    .setMessage("Are Your Sure Want To Delete Contact?")
                    .setIcon(R.drawable.ic_baseline_delete_forever_24)
                    .setPositiveButton("YES", (dialogInterface, i) -> {
                        arrContacts.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Delete Contact Successfully", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.dismiss());

            builder.show();
        });

    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtNumber;
        ImageView imgContact;
        ImageView deleteContact, editContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgContact = itemView.findViewById(R.id.imgProfile);
            txtName = itemView.findViewById(R.id.tvName);
            txtNumber = itemView.findViewById(R.id.tvContactNumber);
            deleteContact = itemView.findViewById(R.id.imgDelete);
            editContact = itemView.findViewById(R.id.imgEdit);
        }
    }
}
