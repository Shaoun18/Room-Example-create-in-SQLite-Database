package com.seip.roomexmb1.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.seip.roomexmb1.daos.EmployeeDao;
import com.seip.roomexmb1.db.EmployeeDatabase;
import com.seip.roomexmb1.entities.Employee;

import java.util.List;

public class EmployeeLocalRepository {
    private EmployeeDao employeeDao;

    public EmployeeLocalRepository(Context context){
        employeeDao = EmployeeDatabase.getDb(context)
                .getEmployeeDao();
    }
    public void addEmployee(Employee employee){
        EmployeeDatabase.backgroundService.execute(() ->{
            //codes within this block will run on background thread
            employeeDao.insertEmployee(employee);
        });
    }

    public LiveData<List<Employee>> getEmployee(){
        return employeeDao.getAllEmployees();
    }

    public LiveData<Employee> getEmployeeById(long id){
        return employeeDao.getEmployeeById(id);
    }
    public void updateEmployee(Employee employee){
        EmployeeDatabase.backgroundService.execute(() ->{
            //codes within this block will run on background thread
            employeeDao.updateEmployee(employee);
        });
        }


    public void deleteEmployee(Employee employee){
        EmployeeDatabase.backgroundService.execute(() ->{
            //codes within this block will run on background thread
            employeeDao.deleteEmployee(employee);
        });
    }
}
