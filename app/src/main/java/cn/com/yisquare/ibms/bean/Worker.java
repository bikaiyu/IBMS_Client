package cn.com.yisquare.ibms.bean;

import java.io.Serializable;

/**
 * Created by bikai on 2016/1/12.
 */
public class Worker implements Serializable {
    String name;//名字
    String project;//所在项目
    String department;//所在部门
    String group;//所在小组
    String post;//职位
    String id,phone;//ID 此ID 应对应服务器中的人员ID  , 手机号码
    String Boss;//上司的ID编号

    public String getBoss() {
        return Boss;
    }

    public void setBoss(String boss) {
        Boss = boss;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }


}
