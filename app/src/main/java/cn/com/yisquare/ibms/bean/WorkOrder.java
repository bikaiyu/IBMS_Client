package cn.com.yisquare.ibms.bean;

import java.io.Serializable;

/**
 * Created by bikai on 2016/1/12.
 */
public class WorkOrder implements Serializable {
    private String number,description,//工单描述
            urgency_level,//紧急程度
            about,//相关因素
            create_time,//生成时间
            accept_time,//接单时间
            malfunction,//故障描述
            reason;//原因

    private Worker work_owner,work_helper;//我自己,协助人
    private boolean visibility; //是否在工单池中显示
    private int status;//工单状态  1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
    private int myfrom;//工单来源  1:系统生成 2:用户提交
    private int classes;//维修类别 1:公共维修 2:客户维修 3:维保服务单



    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public Worker getWork_owner() {
        return work_owner;
    }

    public void setWork_owner(Worker work_owner) {
        this.work_owner = work_owner;
    }

    public Worker getWork_helper() {
        return work_helper;
    }

    public void setWork_helper(Worker work_helper) {
        this.work_helper = work_helper;
    }

    public String getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(String accept_time) {
        this.accept_time = accept_time;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMyfrom() {
        return myfrom;
    }

    public void setMyfrom(int myfrom) {
        this.myfrom = myfrom;
    }

    public String getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(String malfunction) {
        this.malfunction = malfunction;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrgency_level() {
        return urgency_level;
    }

    public void setUrgency_level(String urgency_level) {
        this.urgency_level = urgency_level;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
