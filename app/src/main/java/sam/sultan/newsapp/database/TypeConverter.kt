package sam.sultan.newsapp.database

import sam.sultan.newsapp.models.Source


class TypeConverter {

    @androidx.room.TypeConverter
    fun fromSource(source: Source): String{
        return source.name
    }

    @androidx.room.TypeConverter
    fun toSource(name: String): Source{
        return Source(name, name)
    }

}