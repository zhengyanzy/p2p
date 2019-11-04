package com.zy.p2p.business.mapper;


import com.zy.p2p.base.query.base.QueryObject;
import com.zy.p2p.business.domain.Bid;
import com.zy.p2p.business.domain.PaymentSchedule;
import com.zy.p2p.business.query.BidQueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BidMapper {

	int insert(Bid record);

	Bid selectByPrimaryKey(Long id);

	List<Bid> selectAll();

	/**
	 * 分页
	 * @return
	 */
	int queryForCount(BidQueryObject qo);
	List<Bid> query(BidQueryObject qo);
}