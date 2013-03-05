package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

/**
 * 合买状态
 */
public enum CaseLotState {

	// state=1：新发起，2：已投注，3：完成，4：取消，5：截止，6：预投
	processing(1), alreadyBet(2), finished(3), canceled(4), end(5), ready(6),
	// sortState(4以下包括4，属于普通合买，4以上属于置顶合买)=0：普通合买，3：申请置顶合买大厅，4:申请置顶合买中心，8：置顶合买大厅，9：置顶合买中心
	normal(0), applyTopSaloon(3), applyTopCenter(4), topSaloon(8), topCenter(9),
	// visibility=0:对所有人立即公开，1：保密，2：对所有人截止后公开，3：对跟单者立即公开，4，对跟单者截止后公开
	open(0), secrecy(1), endOpen(2), open2Follower(3), endOpern2Follower(4);

	BigDecimal state;

	public int intValue() {
		return state.intValue();
	}

	public BigDecimal value() {
		return state;
	}

	CaseLotState(int val) {
		this.state = new BigDecimal(val);
	}
}
