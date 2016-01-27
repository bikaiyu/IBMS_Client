package cn.com.yisquare.ibms.bean;

import java.io.Serializable;

/**
 * Created by bikai on 2016/1/14.
 */
public class Msg implements Serializable {
    protected String help_id;
    protected WorkOrder workorder;//工单编号
    protected Long createTime;//消息生成时间
    protected int type;//类别 0:我求别人  1: 别人求我
    protected boolean accepted = false; //是否有人接受了.

    public String getHelp_id() {
        return help_id;
    }

    public void setHelp_id(String help_id) {
        this.help_id = help_id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public WorkOrder getWorkorder() {
        return workorder;
    }

    public void setWorkorder(WorkOrder wk) {
        this.workorder = wk;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
