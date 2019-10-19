package jp.hacks.smartbread.ui.aiface.viewentity

internal data class WeatherNewsViewEntity(
    override val id: Int,
    val title: String,
    val place: String,
    val weather: String,
    val temperature: String
) : NewsViewEntity