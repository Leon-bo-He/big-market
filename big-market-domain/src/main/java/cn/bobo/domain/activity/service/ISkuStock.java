package cn.bobo.domain.activity.service;

import cn.bobo.domain.activity.model.vo.ActivitySkuStockKeyVO;

/**
 * @author BO HE
 */
public interface ISkuStock {

    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;
    void clearQueueValue();
    void updateActivitySkuStock(Long sku);
    void clearActivitySkuStock(Long sku);

}
