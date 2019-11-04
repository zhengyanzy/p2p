package com.zy.p2p.business.query;

import com.zy.p2p.base.domain.Logininfo;
import com.zy.p2p.base.query.base.QueryObject;
import lombok.Data;


@Data
public class BidQueryObject extends QueryObject {
    private Long bidUser_id;
}
