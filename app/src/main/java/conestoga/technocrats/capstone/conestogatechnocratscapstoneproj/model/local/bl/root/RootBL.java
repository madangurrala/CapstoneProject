package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.root;

import android.content.Context;

import androidx.room.Room;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.core.AppDatabase;

public class RootBL {
    private final String DATABASE_NAME="CONESTOGA_CAPSTONE_DB";
    private AppDatabase db;
    public RootBL(Context ctx)
    {
        db = Room.databaseBuilder(ctx,AppDatabase.class, DATABASE_NAME).build();
    }
}
