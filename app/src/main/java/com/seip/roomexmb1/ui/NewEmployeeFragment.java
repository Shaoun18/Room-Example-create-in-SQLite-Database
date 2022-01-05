package com.seip.roomexmb1.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seip.roomexmb1.R;
import com.seip.roomexmb1.databinding.FragmentNewEmployeeBinding;
import com.seip.roomexmb1.db.EmployeeDatabase;
import com.seip.roomexmb1.entities.Employee;
import com.seip.roomexmb1.viewmodels.HomeViewModel;

public class NewEmployeeFragment extends Fragment {
    private FragmentNewEmployeeBinding binding;
    private HomeViewModel viewModel;
    private int id = 0;
    public NewEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Employee employee = NewEmployeeFragmentArgs
                .fromBundle(getArguments())
                .getEmployee();


        viewModel = new ViewModelProvider(requireActivity())
                .get(HomeViewModel.class);
        binding = FragmentNewEmployeeBinding.inflate(inflater,container,false);
        if(employee != null){
            id = employee.getId();
            binding.nameInputET.setText(employee.getName());
            binding.salaryInputET.setText(String.valueOf(employee.getSalary()));
            binding.saveBtn.setText("Update");

        }
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveBtn.setOnClickListener(v ->{
            final String name = binding.nameInputET.getText().toString();
            final double salary = Double.parseDouble(binding.salaryInputET.getText().toString());


            if(id>0){
                final Employee emp = new Employee(id, name, salary);
                viewModel.updateEmployee(emp);
            }
            else{
                final Employee employee = new Employee(name, salary);
                viewModel.addEmployee(employee);
            }


            final Employee employee = new Employee(name, salary);

            //viewModel.addEmployee(employee);
            Navigation.findNavController(v).popBackStack();
//            EmployeeDatabase.getDb(getActivity())
//                    .getEmployeeDao()
//                    .insertEmployee(employee);
        });
    }
}