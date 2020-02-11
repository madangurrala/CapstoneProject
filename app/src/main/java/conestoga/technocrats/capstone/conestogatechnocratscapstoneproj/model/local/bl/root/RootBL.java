package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.bl.root;

import android.content.Context;

import androidx.room.Room;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da.core.AppDatabase;

public class RootBL {
    private final String DATABASE_NAME="CONESTOGA_CAPSTONE_DB";

    protected Context ctx;
    private AppDatabase db;
    public RootBL(Context ctx)
    {
        this.ctx=ctx;
        db = Room.databaseBuilder(this.ctx,AppDatabase.class, DATABASE_NAME).build();
    }
}
