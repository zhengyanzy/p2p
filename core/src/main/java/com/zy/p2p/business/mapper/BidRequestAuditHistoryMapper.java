package com.zy.p2p.business.mapper;

import com.zy.p2p.business.domain.BidRequestAuditHistory;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface BidRequestAuditHistoryMapper {

	int insert(BidRequestAuditHistory record);

	BidRequestAuditHistory selectByPrimaryKey(Long id);

	/**
	 * 根据一个标查询该标的审核历史
	 * 
	 * @param id
	 * @return
	 */
	List<BidRequestAuditHistory> listByBidRequest(Long id);

}