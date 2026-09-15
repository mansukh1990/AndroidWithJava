package com.example.androidwithjava.recyclerview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidwithjava.R;
import com.example.androidwithjava.databinding.DialogAddUpdateContactBinding;
import com.example.androidwithjava.databinding.RowContactBinding;

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

        RowContactBinding binding = RowContactBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ContactModel contactModel = arrContacts.get(position);

        holder.binding.imgProfile.setImageResource(contactModel.img);
        holder.binding.tvName.setText(contactModel.name);
        holder.binding.tvContactNumber.setText(contactModel.number);

        holder.binding.imgEdit.setOnClickListener(view ->
                showUpdateDialog(contactModel, holder.getBindingAdapterPosition()));

        holder.binding.imgDelete.setOnClickListener(view -> {
            holder.binding.imgDelete.setOnClickListener(v ->
                    showDeleteDialog(holder.getBindingAdapterPosition()));
        });

    }

    private void showDeleteDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Delete Contact")
                .setMessage("Are Your Sure Want To Delete Contact?")
                .setIcon(R.drawable.ic_baseline_delete_forever_24)
                .setPositiveButton("YES", (dialogInterface, i) -> {
                    arrContacts.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, arrContacts.size());
                    Toast.makeText(context, "Delete Contact Successfully", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("NO", (dialogInterface, i) -> dialogInterface.dismiss());

        builder.show();
    }

    private void showUpdateDialog(ContactModel contactModel, int position) {
        Dialog dialog = new Dialog(context);
        DialogAddUpdateContactBinding dialogAddUpdateContactBinding = DialogAddUpdateContactBinding.inflate(LayoutInflater.from(context));
        dialog.setContentView(dialogAddUpdateContactBinding.getRoot());

        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }


        dialogAddUpdateContactBinding.edtName.setText(contactModel.name);
        dialogAddUpdateContactBinding.edtNumber.setText(contactModel.number);

        dialogAddUpdateContactBinding.tvAddContact.setText("Update Contact");
        dialogAddUpdateContactBinding.btnAdd.setText("Update");

        dialogAddUpdateContactBinding.btnAdd.setOnClickListener(view1 -> {

            String name = dialogAddUpdateContactBinding.edtName.getText().toString().trim(), number = dialogAddUpdateContactBinding.edtNumber.getText().toString().trim();

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
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final RowContactBinding binding;

        public ViewHolder(@NonNull RowContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
