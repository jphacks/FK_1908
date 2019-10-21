package jp.hacks.smartbread.ui.main.pointcard

import android.content.Context
import com.google.gson.Gson
import jp.hacks.smartbread.ui.main.pointcard.model.Point
import jp.hacks.smartbread.ui.main.pointcard.model.PointCardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

internal class PointCardRepositoryImpl(
    private val context: Context
) : PointCardRepository, PointCardReadonlyRepository {

    private val preferenceKey = "pointcard"
    private val gson = Gson()
    private val preference = context.getSharedPreferences(preferenceKey, Context.MODE_PRIVATE)

    override suspend fun loadPointCard(): PointCardModel {
        val json = preference.getString(preferenceKey, "")
        return gson.fromJson(json, PointCardModel::class.java)
    }

    override suspend fun addPoint(point: Point): Unit = withContext(Dispatchers.Main) {
        val pointCard = loadPointCard()
        pointCard.pointList.toMutableList().apply { this.add(point) }
        preference.edit().putString(preferenceKey, gson.toJson(pointCard))
        return@withContext
    }

    override suspend fun removeAllPoint() = runBlocking {
        val pointCard = loadPointCard()
        pointCard.pointList.toMutableList().clear()
        preference.edit().putString(preferenceKey, gson.toJson(pointCard))
        return@runBlocking
    }
}