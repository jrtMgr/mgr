// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.mgr.domain;

import java.lang.String;

privileged aspect Tsubscribe_Roo_ToString {
    
    public String Tsubscribe.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accountnomoneysms: ").append(getAccountnomoneysms()).append(", ");
        sb.append("Amount: ").append(getAmount()).append(", ");
        sb.append("Batchnum: ").append(getBatchnum()).append(", ");
        sb.append("Beginbatch: ").append(getBeginbatch()).append(", ");
        sb.append("Betcode: ").append(getBetcode()).append(", ");
        sb.append("Betnum: ").append(getBetnum()).append(", ");
        sb.append("Channel: ").append(getChannel()).append(", ");
        sb.append("Drawway: ").append(getDrawway()).append(", ");
        sb.append("Endtime: ").append(getEndtime()).append(", ");
        sb.append("Flowno: ").append(getFlowno()).append(", ");
        sb.append("Lastbatch: ").append(getLastbatch()).append(", ");
        sb.append("Lastnum: ").append(getLastnum()).append(", ");
        sb.append("Lotmulti: ").append(getLotmulti()).append(", ");
        sb.append("Lotno: ").append(getLotno()).append(", ");
        sb.append("Mac: ").append(getMac()).append(", ");
        sb.append("Memo: ").append(getMemo()).append(", ");
        sb.append("Ordertime: ").append(getOrdertime()).append(", ");
        sb.append("Prizeend: ").append(getPrizeend()).append(", ");
        sb.append("Sellway: ").append(getSellway()).append(", ");
        sb.append("State: ").append(getState()).append(", ");
        sb.append("Subaccount: ").append(getSubaccount()).append(", ");
        sb.append("SubaccountType: ").append(getSubaccountType()).append(", ");
        sb.append("Subchannel: ").append(getSubchannel()).append(", ");
        sb.append("Totalamt: ").append(getTotalamt()).append(", ");
        sb.append("Type: ").append(getType()).append(", ");
        sb.append("Userno: ").append(getUserno());
        return sb.toString();
    }
    
}
