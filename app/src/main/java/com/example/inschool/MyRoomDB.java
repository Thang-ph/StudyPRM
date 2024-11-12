package com.example.inschool;

import androidx.room.RoomDatabase;

public abstract class MyRoomDB extends RoomDatabase {
public abstract UserDao userDAO();
}
