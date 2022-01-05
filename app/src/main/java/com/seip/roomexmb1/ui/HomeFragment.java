package com.seip.roomexmb1.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Notification;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seip.roomexmb1.R;
import com.seip.roomexmb1.adapters.EmployeeAdapter;
import com.seip.roomexmb1.callback.EmployeeDeleteListener;
import com.seip.roomexmb1.callback.EmployeeEditListener;
import com.seip.roomexmb1.callback.OnEmployeeItemClickListener;
import com.seip.roomexmb1.databinding.HomeFragmentBinding;
import com.seip.roomexmb1.entities.Employee;
import com.seip.roomexmb1.viewmodels.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements EmployeeEditListener, EmployeeDeleteListener, OnEmployeeItemClickListener {

    private HomeViewModel mViewModel;
    private HomeFragmentBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = HomeFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.newEmpFab.setOnClickListener(v->{
            Navigation.findNavController(v)
                    .navigate(R.id.action_homeFragment_to_newEmployeeFragment);
        });

        final EmployeeAdapter adapter = new EmployeeAdapter(this);
        binding.employeeRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.employeeRV.setAdapter(adapter);
        mViewModel.getAllEmployee().observe(getViewLifecycleOwner(),
        new Observer<List<Employee>> (){
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.submitNewList(employees);
            }


        });
    }

    @Override
    public void onDeleteEmployee(Employee employee) {
        //Toast.makeText(getActivity(),employee.getName(), Toast.LENGTH_SHORT);
        showAlertDialogue(employee);



    }
    private  void showAlertDialogue(Employee employee){
        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete" +employee.getName()+"?");
        builder.setMessage("Are you sure to dlete this employee?");
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setPositiveButton("Yes", (dialog, which) -> {
            mViewModel.deleteEmployee(employee);
        });
        builder.setNegativeButton("No", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onEditEmployee(Employee employee) {
        Toast.makeText(getActivity(),employee.getName(), Toast.LENGTH_SHORT);
        final HomeFragmentDirections.ActionHomeFragmentToNewEmployeeFragment action = HomeFragmentDirections
                .actionHomeFragmentToNewEmployeeFragment();
        action.setEmployee(employee);
        Navigation.findNavController(getActivity(),R.id.fragmentContainerView).navigate(action);
    }


    @Override
    public void onEmployeeItemClicked(Long id) {
        final HomeFragmentDirections.DetailsEmpAction2 action2 = HomeFragmentDirections.detailsEmpAction2();
        action2.setId(id);
        Navigation.findNavController(getActivity(),R.id.fragmentContainerView).navigate(action2);



    }
}