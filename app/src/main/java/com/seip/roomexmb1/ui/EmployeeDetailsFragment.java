package com.seip.roomexmb1.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.seip.roomexmb1.R;
import com.seip.roomexmb1.viewmodels.HomeViewModel;

public class EmployeeDetailsFragment extends Fragment {
    private long id = 0;
    private HomeViewModel homeViewModel;

    public EmployeeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(requireActivity())
                .get(HomeViewModel.class);
        id = EmployeeDetailsFragmentArgs.fromBundle(getArguments()).getId();
        if (id > 0){
            homeViewModel.getEmployeeById(id)
                    .observe(getViewLifecycleOwner(), employee -> {
                        Toast.makeText(getActivity(), employee.getName(),
                                Toast.LENGTH_SHORT).show();
                    });
        }

        return inflater.inflate(R.layout.fragment_employee_details, container, false);
    }
}