package jp.hacks.smartbread.ui.main.bread

import jp.hacks.smartbread.ui.main.bread.model.HasBreadImageModel

internal interface HasBreadUsecase {
    suspend fun predict(breadImage: HasBreadImageModel): Boolean
}