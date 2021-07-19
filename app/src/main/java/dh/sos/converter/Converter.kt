package dh.sos.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import dh.sos.entities.Address

class Converter {

    @TypeConverter
    fun fromString(value: String?): Address? {
        return if (value == null) null else Gson().fromJson(value, Address::class.java)
    }

    @TypeConverter
    fun toString(input: Address?): String? {
        return Gson().toJson(input)
    }
}