package com.zy.p2p.base.query;

import java.util.ArrayList;
import java.util.List;

import com.zy.p2p.base.domain.Iplog;
import lombok.Getter;

/**
 *	返回给前端的所有的数据,用于分页
 */
@SuppressWarnings("all")
@Getter
public class PageResult {
	private List listData;            //当前页的结果集数据:查询
	private Integer currentPage = 1;  //当前查询页
	private Integer totalPage;        //总页数
	private Integer pageSize = 10;    //用来计算总页数

	//前端不需要的数据
	private Integer totalCount;       //总数据条数
	//private Integer prevPage;// 上一页
	//private Integer nextPage;// 下一页

	public PageResult(List listData, Integer totalCount, Integer currentPage, Integer pageSize) {
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;

		this.pageSize = pageSize;
		// ----------------------------------------
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
		//this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		//this.nextPage=this.currentPage+1<=this.totalPage?this.currentPage+1:this.totalPage;
	}

	//如果总数据条数为0,返回一个空集
	public static PageResult empty(Integer pageSize) {
		return new PageResult(new ArrayList<>(), 1, 1, pageSize);
	}
}