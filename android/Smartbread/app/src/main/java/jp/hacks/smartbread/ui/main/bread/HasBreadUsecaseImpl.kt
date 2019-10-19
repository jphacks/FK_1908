package jp.hacks.smartbread.ui.main.bread

import jp.hacks.smartbread.ui.main.bread.model.HasBreadImageModel

internal class HasBreadUsecaseImpl : HasBreadUsecase {
    override suspend fun predict(breadImage: HasBreadImageModel): Boolean {
        return true
    }
}