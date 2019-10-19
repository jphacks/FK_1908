package jp.hacks.smartbread.ui.main.bread

import jp.hacks.smartbread.ui.main.bread.model.IsBurnBreadImageModel

internal interface IsBurnBreadUsecase {
    suspend fun execute(isBurnBreadImageModel: IsBurnBreadImageModel): Boolean
}