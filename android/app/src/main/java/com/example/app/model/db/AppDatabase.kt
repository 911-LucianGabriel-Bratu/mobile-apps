package com.example.app.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app.model.InstrumentBrands
import com.example.app.model.InstrumentCategories
import com.example.app.model.MusicalInstruments
import com.example.app.model.Orders
import com.example.app.model.Users
import com.example.app.model.dao.InstrumentBrandsDAO
import com.example.app.model.dao.InstrumentCategoriesDAO
import com.example.app.model.dao.MusicalInstrumentsDAO
import com.example.app.model.dao.OrdersDAO
import com.example.app.model.dao.UsersDAO
import com.example.app.model.utils.Converters

@Database(
    entities = [InstrumentBrands::class, InstrumentCategories::class, MusicalInstruments::class,
        Orders::class, Users::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun instrumentBrandsDao(): InstrumentBrandsDAO
    abstract fun instrumentCategoriesDao(): InstrumentCategoriesDAO
    abstract fun musicalInstrumentsDao(): MusicalInstrumentsDAO
    abstract fun ordersDao(): OrdersDAO
    abstract fun usersDao(): UsersDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null;

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}