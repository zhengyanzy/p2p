package com.zy.p2p.base.query.base;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 2;
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}
}
