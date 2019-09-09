package com.jinyafu.thirdpart.common.data;

import lombok.Data;

/**
 * date 2018-06-13 18:17:51<br>
 * description 
 * @author XiaHui<br>
 * @since 
 */
@Data
public class PageInfos {
	private Integer pageSize = 10;// 页码大小
	private Integer startResult = 0;// 起始记录数
	private Integer endResult = 0;// 结束记录数
	private Integer totalCount = 0;
	private Integer pageNum = 1;
	private Integer totalPage;

//	public Integer getTotalCount() {
//		return totalCount;
//	}

	
//	public void setTotalCount(Integer totalCount) {
//		this.totalCount = totalCount;
//	}

	
	public Integer getPageSize() {
		return pageSize;
	}

	
	public void setPageSize(Integer pageSize) {
		if(pageSize == null ||pageSize <= 0){
			this.pageSize = 10;
		}else{
			this.pageSize = pageSize;
		}
	}

//	public void setStartResult(Integer startResult) {
//		this.startResult = startResult;
//	}

	
//	public Integer getTotalPage() {
//		if (this.getTotalCount() > 0) {
//			totalPage = this.getTotalCount() / this.getPageSize();
//			if (this.getTotalCount() % this.getPageSize() > 0) {
//				totalPage = totalPage + 1;
//			}
//		}
//		return totalPage;
//	}

	
	public Integer getStartResult() {
		if ( this.getPageSize() < 0 || this.getPageNum() < 0) {
			startResult = 0;
		} else if (startResult > 0) {
			
		} else {
			startResult = (this.getPageNum() - 1) * this.getPageSize();
			
			if (startResult < 0) {
				startResult = 0;
			}
		}
		return startResult;
	}

	
//	public void setEndResult(Integer endResult) {
//		if (endResult > this.getTotalCount()) {
//			this.endResult = this.getTotalCount();
//		} else {
//			this.endResult = endResult;
//		}
//	}

	
//	public Integer getEndResult() {
//		endResult = (this.getPageNum() * this.getPageSize());
//		if (endResult > this.getTotalCount()) {
//			endResult = this.getTotalCount();
//		}
//		return endResult;
//	}

	
	public void setPageNum(Integer pageNum) {
		if(pageNum==null||pageNum<=0){
			pageNum=1;
		}else{
			this.pageNum = pageNum;
		}

	}

	
	public Integer getPageNum() {
		return pageNum;
	}
}
