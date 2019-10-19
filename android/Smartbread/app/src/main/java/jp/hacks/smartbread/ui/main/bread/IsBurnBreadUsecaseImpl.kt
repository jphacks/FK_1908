package jp.hacks.smartbread.ui.main.bread

import jp.hacks.smartbread.ui.main.bread.model.IsBurnBreadImageModel

internal class IsBurnBreadUsecaseImpl : IsBurnBreadUsecase {
    override suspend fun execute(isBurnBreadImageModel: IsBurnBreadImageModel): Boolean {
        return true
    }
}