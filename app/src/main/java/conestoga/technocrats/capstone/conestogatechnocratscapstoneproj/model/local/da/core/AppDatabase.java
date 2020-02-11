package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.core;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.UserDA;
import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.UserTO;

@Database(entities = {UserTO.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDA userDA();
}
