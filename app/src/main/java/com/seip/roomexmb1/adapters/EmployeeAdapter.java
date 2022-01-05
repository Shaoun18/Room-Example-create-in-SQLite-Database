package com.seip.roomexmb1.adapters;

import android.inputmethodservice.Keyboard;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.seip.roomexmb1.R;
import com.seip.roomexmb1.callback.EmployeeDeleteListener;
import com.seip.roomexmb1.callback.EmployeeEditListener;
import com.seip.roomexmb1.callback.OnEmployeeItemClickListener;
import com.seip.roomexmb1.databinding.EmployeerowBinding;
import com.seip.roomexmb1.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>{
    
        private List<Employee> employeeList;
        private EmployeeEditListener editListener;
        private EmployeeDeleteListener deleteListener;
        private OnEmployeeItemClickListener employeeItemClickListener;

        public  EmployeeAdapter(Fragment fragment){
            employeeList = new ArrayList<>();
            editListener = (EmployeeEditListener) fragment;
            deleteListener = (EmployeeDeleteListener)  fragment;
            employeeItemClickListener = (OnEmployeeItemClickListener) fragment;
        }

        
        public EmployeeAdapter(){
            employeeList = new ArrayList<>();
        }
        
        
    

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final EmployeerowBinding binding = EmployeerowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
            final Employee employee = employeeList.get(position);
            holder.bind(employee);
            holder.itemView.setOnClickListener(v -> {
                final long id = employeeList.get(position).getId();
                employeeItemClickListener.onEmployeeItemClicked(id);

            });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }
    public void setEmployeeList(List<Employee> employees){
            this.employeeList = employees;
            notifyDataSetChanged();
    }

    public void submitNewList(List<Employee> employees) {
            this.employeeList = employees;
            notifyDataSetChanged();
    }


    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private EmployeerowBinding binding;
        public EmployeeViewHolder(EmployeerowBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            this.binding.rowMenuBtn.setOnClickListener(v->{
                final int position = getAdapterPosition();
                final Employee employee = employeeList.get(position);
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                MenuInflater menuInflater = popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.row_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()){
                        case R.id.item_edit:
                            editListener.onEditEmployee(employee);

                            return  true;
                    }
                    switch (menuItem.getItemId()){
                        case R.id.item_delete:
                            deleteListener.onDeleteEmployee(employee);
                            return true;
                    }
                    return  false;
                });
            });
        }
        public void bind(Employee employee){
            binding.setEmployee(employee);
        }
    }


}
