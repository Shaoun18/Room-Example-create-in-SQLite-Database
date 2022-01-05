package com.seip.roomexmb1.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.seip.roomexmb1.daos.EmployeeDao;
import com.seip.roomexmb1.entities.Employee;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Employee.class}, version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {
    private static EmployeeDatabase db;
    public static ExecutorService backgroundService =
            Executors.newFixedThreadPool(4);
    public abstract EmployeeDao getEmployeeDao();
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table 'tbl_employee' add column 'designation' text");
        }
    };

    public static EmployeeDatabase getDb(Context context){
        if (db == null){
            db = Room.databaseBuilder(context, EmployeeDatabase.class, "employee_db").addMigrations(MIGRATION_1_2)
                    .build();
            return db;
        }
        return db;
    }

}
