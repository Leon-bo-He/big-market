package cn.bobo.domain.activity.service.armory;

import java.util.Date;

public interface IActivityDispatch {

    boolean subtractionActivitySkuStock(Long sku, Date endDateTime);

}
