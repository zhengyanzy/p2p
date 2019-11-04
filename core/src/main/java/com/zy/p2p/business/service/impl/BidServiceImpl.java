package com.zy.p2p.business.service.impl;

import com.zy.p2p.base.query.PageResult;
import com.zy.p2p.base.query.base.QueryObject;
import com.zy.p2p.business.domain.Bid;
import com.zy.p2p.business.domain.PaymentSchedule;
import com.zy.p2p.business.mapper.BidMapper;
import com.zy.p2p.business.query.BidQueryObject;
import com.zy.p2p.business.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 前台投标服务
 */
@Service
public class BidServiceImpl implements IBidService {
    @Autowired
    private BidMapper bidMapper;

    @Override
    public PageResult query(BidQueryObject qo) {
        int totalCount = this.bidMapper.queryForCount(qo);
        if (totalCount > 0) {
            List<Bid> list = this.bidMapper.query(qo);
            return new PageResult(list,totalCount , qo.getCurrentPage(), qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }
}