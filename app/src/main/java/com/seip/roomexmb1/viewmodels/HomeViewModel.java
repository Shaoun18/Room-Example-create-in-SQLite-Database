package com.seip.roomexmb1.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.seip.roomexmb1.daos.EmployeeDao;
import com.seip.roomexmb1.entities.Employee;
import com.seip.roomexmb1.repository.EmployeeLocalRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private EmployeeLocalRepository repository;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeLocalRepository(application);
    }
    public void updateEmployee(Employee employee){
        repository.updateEmployee(employee);
    }
    public void deleteEmployee(Employee employee){
        repository.deleteEmployee(employee);
    }
    public void addEmployee(Employee employee){
        repository.addEmployee(employee);
    }
    public LiveData<List<Employee>> getAllEmployee(){
        return repository.getEmployee();
    }
    public LiveData<Employee> getEmployeeById(long id){
        return repository.getEmployeeById(id);
    }
}