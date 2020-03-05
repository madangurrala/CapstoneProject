package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.PropertyTO;

@Dao
public interface PropertyDA
{
    @Insert
    public void insert(PropertyTO propertyTO);
    @Update
    public void update(PropertyTO propertyTO);
    @Delete
    public void delete(PropertyTO propertyTO);
    @Query(value = "SELECT * FROM TBL_PROPERTIES WHERE ID=:id limit 1")
    public void select(long id);
    @Query(value = "SELECT * FROM TBL_PROPERTIES ORDER BY registerDate DESC")
    public LiveData<List<PropertyTO>> selectAll();
    @Query(value = "SELECT * FROM TBL_PROPERTIES WHERE userId=:userId ORDER BY registerDate DESC")
    public LiveData<List<PropertyTO>> selectAllByOwner(long userId);
}
