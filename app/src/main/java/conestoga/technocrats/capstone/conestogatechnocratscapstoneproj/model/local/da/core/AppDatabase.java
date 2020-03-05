package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.core;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.AppointmentDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.MessageDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.PropertyDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.UserDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

@Database(entities = {UserTO.class, PropertyDA.class, MessageDA.class, AppointmentDA.class}
        , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private AppDatabase() {
    }

    private static AppDatabase appDatabase;

    public abstract UserDA userDA();

    public abstract PropertyDA propertyDA();

    public abstract MessageDA messageDA();

    public abstract AppointmentDA appointmentDA();

    public static synchronized AppDatabase getInstance(Context ctx) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(ctx, AppDatabase.class,"app_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}
