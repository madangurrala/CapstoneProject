package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.AppointmentTO;

@Dao
public interface AppointmentDA
{
    @Insert
    public void insert(AppointmentTO appointmentTO);
    @Update
    public void update(AppointmentTO appointmentTO);
    @Delete
    public void delete(AppointmentTO appointmentTO);
    @Query(value = "SELECT * FROM TBL_APPOINTMENTS WHERE ID=:id ORDER BY appointmentDate DESC")
    public LiveData<List<AppointmentTO>> select(long id);
    @Query(value = "SELECT * FROM TBL_APPOINTMENTS ORDER BY appointmentDate DESC")
    public LiveData<List<AppointmentTO>> selectAll(AppointmentTO appointmentTO);
}
