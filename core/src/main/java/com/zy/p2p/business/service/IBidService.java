package com.zy.p2p.business.service;

import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.business.query.BidQueryObject;

public interface IBidService {
    public PageResult query(BidQueryObject qo);
}
