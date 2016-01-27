package cn.com.yisquare.ibms.bean;

import java.io.Serializable;

/**
 * Created by bikai on 2016/1/5.
 */

public class User implements Serializable {
//    private String session;
    private int id;//数据库中的ID
    private String usernumber;//用户编号 服务器中的同步
    private String project;//所在项目
    private String department;//所在部门
    private String group;//所在工作组
    private String post;//职位
    private int status;//状态 0:无任务 1:执行中
    private int level;//权限 0:无登录权限 1:维修工 2:组长 3:领导 4:管理员
    private String username;//登录名
    private String password;//登录密码
    private String pictureyzm;
    private String yzm;
    private String name;//用户名字
    private String phonenumber;//手机号
    private Long lastlogin;//上次登录时间

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }
    public Long getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Long lastlogin) {
        this.lastlogin = lastlogin;
    }

//    public String getSession() {
//        return session;
//    }
//
//    public void setSession(String session) {
//        this.session = session;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureyzm() {
        return pictureyzm;
    }

    public User setPictureyzm(String pictureyzm) {
        this.pictureyzm = pictureyzm;
        return this;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
