package conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.local.da;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import conestoga.technocrats.capstone.conestogatechnocratscapstoneproj.model.to.MessageTO;

@Dao
public interface MessageDA
{
    @Insert
    public void insert(MessageTO messageTO);
    @Update
    public void update(MessageTO messageTO);
    @Delete
    public void delete(MessageTO messageTO);
    @Query(value = "SELECT * FROM TBL_MESSAGES ORDER BY registerDate DESC")
    public LiveData<List<MessageTO>> selectAll();
    @Query(value = "SELECT * FROM TBL_MESSAGES WHERE senderId=:userId or receiverId=:userId ORDER BY registerDate DESC")
    public LiveData<List<MessageTO>> selectByOwner(long userId);
}
