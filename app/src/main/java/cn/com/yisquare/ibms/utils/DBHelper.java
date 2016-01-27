package cn.com.yisquare.ibms.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cn.com.yisquare.ibms.bean.Msg;
import cn.com.yisquare.ibms.bean.User;
import cn.com.yisquare.ibms.bean.WorkOrder;
import cn.com.yisquare.ibms.bean.Worker;

/**
 * Created by bikai on 2016/1/19.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName = "IBMS";
    private static final int DBVersion = 1;
    public SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DBName, null, DBVersion);

        db_InitialDB();
        this.db = this.getWritableDatabase();
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void db_InitialDB() {
        String strSQL;
        Cursor cur;
        SQLiteDatabase db;

        /** 登录人员信息表 tb_UserInfo*/
        strSQL = "SELECT * FROM sqlite_master WHERE name ='tb_UserInfo' and type='table'";
        db = this.getWritableDatabase();
        cur = db.rawQuery(strSQL, null);
        //检测APP是否首次运行
        //如果不是第一次运行,则检查字段
        if (cur != null && cur.getCount() != 0) {
            // 检测字段是否存在
            // 不存在则创建
            db_CheckColumnExist("tb_UserInfo", "id", " TEXT");
            db_CheckColumnExist("tb_UserInfo", "usernumber", " TEXT");//用户编号
            db_CheckColumnExist("tb_UserInfo", "username", " TEXT");//登录名
            db_CheckColumnExist("tb_UserInfo", "password", " TEXT");//登录密码
            db_CheckColumnExist("tb_UserInfo", "name", " TEXT");//员工名字
            db_CheckColumnExist("tb_UserInfo", "department", " TEXT");//所在部门
            db_CheckColumnExist("tb_UserInfo", "mygroup", " TEXT");//所在工作组
            db_CheckColumnExist("tb_UserInfo", "post", " TEXT");//职位
            db_CheckColumnExist("tb_UserInfo", "phonenumber", " TEXT");//手机号
            db_CheckColumnExist("tb_UserInfo", "status", " INTEGER");//状态 0:无任务 1:执行中
            db_CheckColumnExist("tb_UserInfo", "level", " INTEGER");//权限 0:无登录权限 1:维修工 2:组长 3:领导 4:管理员
            db_CheckColumnExist("tb_UserInfo", "lastlogin", " TEXT");//上次登录时间
        } else {
            // CreateTable();
            // 是第一次运行,则创建表
            strSQL = "CREATE TABLE tb_UserInfo (" + "id TEXT,"
                    + "usernumber TEXT," + "username TEXT," + "password TEXT,"
                    + "name TEXT," + "department TEXT,"
                    + "mygroup TEXT," + "post TEXT,"
                    + "phonenumber TEXT," + "status INTEGER," + "level INTEGER,"+ "lastlogin TEXT"+ ")";
            db_Excute(strSQL);
        }
        cur.close();
        db.close();



        /** 工单表 tb_WorkOrder */
        strSQL = "SELECT * FROM sqlite_master WHERE name ='tb_WorkOrder' and type='table'";
        db = this.getWritableDatabase();
        cur = db.rawQuery(strSQL, null);
        if (cur != null && cur.getCount() != 0) {
            // Check Column Exist
            db_CheckColumnExist("tb_WorkOrder", "wo_id", " TEXT");//工单编号
            db_CheckColumnExist("tb_WorkOrder", "description", " TEXT");//工单描述
            db_CheckColumnExist("tb_WorkOrder", "urgency_level", " TEXT");//紧急程度
            db_CheckColumnExist("tb_WorkOrder", "about", " TEXT");//相关因素
            db_CheckColumnExist("tb_WorkOrder", "create_time", " TEXT");//生成时间
            db_CheckColumnExist("tb_WorkOrder", "accept_time", " TEXT");//接单时间
            db_CheckColumnExist("tb_WorkOrder", "malfunction", " TEXT");//故障描述
            db_CheckColumnExist("tb_WorkOrder", "reason", " TEXT");//原因
            db_CheckColumnExist("tb_WorkOrder", "status", " INTEGER");//1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
            db_CheckColumnExist("tb_WorkOrder", "myfrom", " INTEGER");//工单来源 0:系统生成 1:用户提交
            db_CheckColumnExist("tb_WorkOrder", "classes", " INTEGER");//维修类别 0:公共维修 1:客户维修 2:维保服务单
            db_CheckColumnExist("tb_WorkOrder", "owner_id", " TEXT");//工单执行人
            db_CheckColumnExist("tb_WorkOrder", "helper_id", " TEXT");//帮助人
        } else {
            // CreateTable();
            strSQL = "CREATE TABLE tb_WorkOrder (" + "wo_id TEXT primary key,"
                    + "description TEXT," + "urgency_level TEXT,"
                    + "about TEXT," + "create_time TEXT,"
                    + "accept_time TEXT," + "malfunction TEXT,"
                    + "reason TEXT," + "status TEXT,"
                    + "myfrom TEXT," + "classes TEXT,"
                    + "owner_id TEXT," + "helper_id TEXT"+ ")";
            db_Excute(strSQL);
        }
        cur.close();
        db.close();

        /** 员工表 tb_Worker */
        strSQL = "SELECT * FROM sqlite_master WHERE name ='tb_Worker' and type='table'";
        db = this.getWritableDatabase();
        cur = db.rawQuery(strSQL, null);
        if (cur != null && cur.getCount() != 0) {
            // Check Column Exist
            db_CheckColumnExist("tb_Worker", "worker_id", " TEXT");//员工编号
            db_CheckColumnExist("tb_Worker", "name", " TEXT");//员工姓名
            db_CheckColumnExist("tb_Worker", "project", " TEXT");//所在项目
            db_CheckColumnExist("tb_Worker", "department", " TEXT");//所在部门
            db_CheckColumnExist("tb_Worker", "mygroup", " TEXT");//所在小组
            db_CheckColumnExist("tb_Worker", "post", " TEXT");//职位
            db_CheckColumnExist("tb_Worker", "phone", " TEXT");//电话号码
        } else {
            // CreateTable();
            strSQL = "CREATE TABLE tb_Worker (" + "worker_id TEXT primary key,"
                    + "name TEXT," + "project TEXT,"+"department TEXT,"
                    + "mygroup TEXT," + "post TEXT,"
                    + "phone TEXT" + ")";
            db_Excute(strSQL);
        }
        cur.close();
        db.close();

        /** 求助消息表 tb_help_msg */
        strSQL = "SELECT * FROM sqlite_master WHERE name ='tb_help_msg' and type='table'";
        db = this.getWritableDatabase();
        cur = db.rawQuery(strSQL, null);
        if (cur != null && cur.getCount() != 0) {
            // Check Column Exist
            db_CheckColumnExist("tb_help_msg", "help_id", " TEXT");//消息编号
            db_CheckColumnExist("tb_help_msg", "workorder_id", " TEXT");//工单编号 去tb_WorkOrder表中查找 wk_id = wo_number
            db_CheckColumnExist("tb_help_msg", "createTime", " TEXT");//消息生成时间
            db_CheckColumnExist("tb_help_msg", "type", " INTEGER");//类别 0:我求别人  1: 别人求我
            db_CheckColumnExist("tb_help_msg", "accepted", " INTEGER");//是否有人接受了.
        } else {
            // CreateTable();
            strSQL = "CREATE TABLE tb_help_msg (" + "help_id TEXT primary key,"
                    + "workorder_id TEXT," + "createTime TEXT,"
                    + "type INTEGER," + "accepted INTEGER"+ ")";
            db_Excute(strSQL);
        }
        cur.close();
        db.close();

        /** 所有员工列表 */
        strSQL = "SELECT * FROM sqlite_master WHERE name ='tb_all_user' and type='table'";
        db = this.getWritableDatabase();
        cur = db.rawQuery(strSQL, null);
        if (cur != null && cur.getCount() != 0) {
            // Check Column Exist
            db_CheckColumnExist("tb_all_user", "user_id", " INTEGER");//用户ID
            db_CheckColumnExist("tb_all_user", "username", " TEXT");//用户名字
            db_CheckColumnExist("tb_all_user", "project", " TEXT");//所在项目
            db_CheckColumnExist("tb_all_user", "department", " Text");//所在部门
            db_CheckColumnExist("tb_all_user", "mygroup", " Text");//所在工作组
            db_CheckColumnExist("tb_all_user", "post", " Text");//职位
        } else {
            // CreateTable();
            strSQL = "CREATE TABLE tb_all_user (" + "user_id INTEGER int identity(0,1) primary key,"
                    + "username TEXT," + "project TEXT,"
                    + "department TEXT," + "mygroup TEXT,"
                    + "post TEXT"+ ")";
            db_Excute(strSQL);
        }
        cur.close();
        db.close();
    }

    /**
     * 查找user信息
     * @param username
     * @param password
     * @return
     */
    public User getuser(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_UserInfo where  username = '"+ username +"' and password = '"+ password+"'";
        Log.w("getuser", sql);
        Cursor cur = db.rawQuery(sql, null);
        User user = null;
        if(cur!=null&&cur.getCount()>0){
            if(cur.getCount()>1)
                Log.e("ERROR", "读取user数据库时发现有大于1个的匹配记录");
            user = new User();
            cur.moveToFirst();
            user.setId(cur.getInt(cur.getColumnIndex("id")));
            user.setUsernumber(cur.getString(cur.getColumnIndex("usernumber")));
            user.setUsername(username);
            user.setPassword(password);
            user.setName(cur.getString(cur.getColumnIndex("name")));
            user.setDepartment(cur.getString(cur.getColumnIndex("department")));
            user.setGroup(cur.getString(cur.getColumnIndex("mygroup")));
            user.setPost(cur.getString(cur.getColumnIndex("post")));
            user.setPhonenumber(cur.getString(cur.getColumnIndex("phonenumber")));
            user.setStatus(cur.getInt(cur.getColumnIndex("status")));
            user.setLevel(cur.getInt(cur.getColumnIndex("level")));
            user.setLastlogin(cur.getLong(cur.getColumnIndex("lastlogin")));
        }
        cur.close();
        db.close();
        return user;
    }

    /**
     * 保存user信息
     * @param user
     * @return
     */
    public boolean setuser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", user.getId());
        cv.put("usernumber",user.getUsernumber());
        cv.put("username",user.getUsername());
        cv.put("password",user.getPassword());
        cv.put("name",user.getName());
        cv.put("department",user.getDepartment());
        cv.put("mygroup",user.getGroup());
        cv.put("post",user.getPost());
        cv.put("phonenumber",user.getPhonenumber());
        cv.put("status",user.getStatus());
        cv.put("level",user.getLevel());
        cv.put("lastlogin",user.getLastlogin());
        Long access = db.insert("tb_UserInfo", null, cv);
        db.close();
        Log.w("set UserInfo", "保存User信息成功?==>" + (access > 0));
        return access>0;
    }

    /**
     * 更新user信息
     * @param field
     * @param value
     */
    public boolean updateUser(String field,String value){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(field,value);
        int accept = db.update("tb_UserInfo", cv, null, null);
        db.close();
        return accept>0;
    }

    /**
     * 删除user信息
     * @return
     */
    public boolean deleteUser(){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        int accept = db.delete("tb_UserInfo", null, null);
        db.close();
        return accept>0;
    }
    /**
     * 读取求助信息
     * @param type
     * @return
     */
    public ArrayList<Msg> gethelp_msg(int type){
        ArrayList<Msg> list = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_help_msg where  type = "+ type +" order by createTime desc";
        Log.w("gethelp_msg", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&&cur.getCount()>0){
            list = new ArrayList<Msg>();
            cur.moveToFirst();
            for(int i = 0 ; i<cur.getCount();i++){
                Msg msg = new Msg();
                msg.setHelp_id(cur.getString(cur.getColumnIndex("help_id")));
                msg.setWorkorder(getWorkOrder(cur.getString(cur.getColumnIndex("workorder_id"))));
                msg.setCreateTime(cur.getLong(cur.getColumnIndex("createTime")));
                msg.setType(cur.getInt(cur.getColumnIndex("type")));
                list.add(msg);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return list;
    }

    /**
     * 保存求助信息
     * @param msg
     * @return
     */
    public boolean sethelp_msg(Msg msg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("help_id", msg.getHelp_id());
        cv.put("workorder_id", msg.getWorkorder().getNumber());
        cv.put("createTime", msg.getCreateTime());
        cv.put("type", msg.getType());
        Long access = db.insert("tb_help_msg", null, cv);
        db.close();
        Log.w("setmsg", "保存Msg信息成功?==>" + (access > 0));
        return access>0;
    }
    /**
     * 更新求助信息
     * @param id
     * @param field
     * @param value
     * @return
     */
    public boolean updateMsg(String id,String field,String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(field,value);
        int accept = db.update("tb_help_msg", cv, "help_id = '" + id + "'", null);
        db.close();
        return accept>0;
    }
    /**
     * 获取工单
     * @param wo_id
     * @return
     */
    public WorkOrder getWorkOrder(String wo_id){
        WorkOrder wo =null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_WorkOrder where  wo_id = "+ wo_id ;
        Log.w("getWorkOrder", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&&cur.getCount()>0){
            cur.moveToFirst();
            wo= new WorkOrder();
            wo.setNumber(wo_id);
            wo.setDescription(cur.getString(cur.getColumnIndex("description")));
            wo.setUrgency_level(cur.getString(cur.getColumnIndex("urgency_level")));
            wo.setAbout(cur.getString(cur.getColumnIndex("about")));
            wo.setCreate_time(cur.getString(cur.getColumnIndex("create_time")));
            wo.setAccept_time(cur.getString(cur.getColumnIndex("accept_time")));
            wo.setMalfunction(cur.getString(cur.getColumnIndex("malfunction")));
            wo.setReason(cur.getString(cur.getColumnIndex("reason")));
            wo.setStatus(cur.getInt(cur.getColumnIndex("status")));
            wo.setMyfrom(cur.getInt(cur.getColumnIndex("myfrom")));
            wo.setClasses(cur.getInt(cur.getColumnIndex("classes")));
            //通过 Worker id 来查找 执行人 协助人
            wo.setWork_owner(getWorker(cur.getString(cur.getColumnIndex("owner_id"))));
            wo.setWork_helper(getWorker(cur.getString(cur.getColumnIndex("helper_id"))));
        }
        return wo;
    }
    /**
     * 获取工单 classes//维修类别 1:公共维修 2:客户维修 3:维保服务单
     *          status//工单状态 1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
     * @param classes
     * @return
     */
    public ArrayList<WorkOrder> getWorkOrder(int classes,int status){
        WorkOrder wo ;
        ArrayList<WorkOrder> list = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_WorkOrder where  classes = "+ classes +" and status = "+status;
//        String sql = "select * from tb_WorkOrder " ;
        Log.w("getWorkOrder", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&&cur.getCount()>0){
            list = new ArrayList<>();
            int count = cur.getCount();
            Log.w("getWorkOrder", "getWorkOrder.count ==>" + count);
            cur.moveToFirst();
            for (int i = 0; i < count; i++) {
                wo= new WorkOrder();
                wo.setNumber(cur.getString(cur.getColumnIndex("wo_id")));
                wo.setDescription(cur.getString(cur.getColumnIndex("description")));
                wo.setUrgency_level(cur.getString(cur.getColumnIndex("urgency_level")));
                wo.setAbout(cur.getString(cur.getColumnIndex("about")));
                wo.setCreate_time(cur.getString(cur.getColumnIndex("create_time")));
                wo.setAccept_time(cur.getString(cur.getColumnIndex("accept_time")));
                wo.setMalfunction(cur.getString(cur.getColumnIndex("malfunction")));
                wo.setReason(cur.getString(cur.getColumnIndex("reason")));
                wo.setStatus(cur.getInt(cur.getColumnIndex("status")));
                wo.setMyfrom(cur.getInt(cur.getColumnIndex("myfrom")));
                wo.setClasses(cur.getInt(cur.getColumnIndex("classes")));
                //通过 Worker id 来查找 执行人 协助人
                wo.setWork_owner(getWorker(cur.getString(cur.getColumnIndex("owner_id"))));
                wo.setWork_helper(getWorker(cur.getString(cur.getColumnIndex("helper_id"))));
                cur.moveToNext();
                list.add(wo);
            }
        }
        return list;
    }
    /**
     * 获取未完成工单 classes//维修类别 1:公共维修 2:客户维修 3:维保服务单
     *          status//工单状态 1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
     * @param classes
     * @return
     */
    public ArrayList<WorkOrder> getWorkOrder(String work_id,int classes,int status){
        WorkOrder wo ;
        ArrayList<WorkOrder> list = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_WorkOrder where owner_id = '"+work_id+"' and classes = "+ classes +" and status = "+status;
//        String sql = "select * from tb_WorkOrder " ;
        Log.w("getWorkOrder", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&&cur.getCount()>0){
            list = new ArrayList<>();
            int count = cur.getCount();
            Log.w("getWorkOrder", "getWorkOrder.count ==>" + count);
            cur.moveToFirst();
            for (int i = 0; i < count; i++) {
                wo= new WorkOrder();
                wo.setNumber(cur.getString(cur.getColumnIndex("wo_id")));
                wo.setDescription(cur.getString(cur.getColumnIndex("description")));
                wo.setUrgency_level(cur.getString(cur.getColumnIndex("urgency_level")));
                wo.setAbout(cur.getString(cur.getColumnIndex("about")));
                wo.setCreate_time(cur.getString(cur.getColumnIndex("create_time")));
                wo.setAccept_time(cur.getString(cur.getColumnIndex("accept_time")));
                wo.setMalfunction(cur.getString(cur.getColumnIndex("malfunction")));
                wo.setReason(cur.getString(cur.getColumnIndex("reason")));
                wo.setStatus(cur.getInt(cur.getColumnIndex("status")));
                wo.setMyfrom(cur.getInt(cur.getColumnIndex("myfrom")));
                wo.setClasses(cur.getInt(cur.getColumnIndex("classes")));
                //通过 Worker id 来查找 执行人 协助人
                wo.setWork_owner(getWorker(cur.getString(cur.getColumnIndex("owner_id"))));
                wo.setWork_helper(getWorker(cur.getString(cur.getColumnIndex("helper_id"))));
                cur.moveToNext();
                list.add(wo);
            }
        }
        return list;
    }


    /**
     * 保存工单
     * @param workOrder
     * @return
     */
    public boolean setWorkOrder(WorkOrder workOrder){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("wo_id", workOrder.getNumber());
        cv.put("description", workOrder.getDescription());
        cv.put("urgency_level", workOrder.getUrgency_level());
        cv.put("about", workOrder.getAbout());
        cv.put("create_time", workOrder.getCreate_time());
        cv.put("accept_time", workOrder.getAccept_time());
        cv.put("malfunction", workOrder.getMalfunction());
        cv.put("reason",workOrder.getReason());
        cv.put("status",workOrder.getStatus());
        cv.put("myfrom",workOrder.getMyfrom());
        cv.put("classes",workOrder.getClasses());
        String own_id = workOrder.getWork_owner().getId();
        if(own_id==null)
            own_id = "";
        cv.put("work_owner", own_id);
        String helper_id = workOrder.getWork_helper().getId();
        if(helper_id==null)
            helper_id="";
        cv.put("helper_id", helper_id);

        Long access = db.insert("tb_WorkOrder", null, cv);
        db.close();
        Log.w("setWorkOrder", "setWorkOrder?==>" + (access > 0));
        return access>0;
    }

    /**
     * 更新工单
     * @param id
     * @param field
     * @param value
     * @return
     */
    public boolean updateWorkOrder(String id,String field,String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(field,value);
        int accept = db.update("tb_WorkOrder", cv, "wo_id = '" + id + "'", null);
        db.close();
        return accept>0;
    }
    /**
     * 获取员工信息
     * @param worker_id
     * @return
     */
    public Worker getWorker(String worker_id){
        Worker worker =null;
        if(worker_id==null)
            return null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_Worker where  worker_id = "+ worker_id ;
        Log.w("getWorker", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&&cur.getCount()>0){
            cur.moveToFirst();
            worker= new Worker();
            worker.setId(worker_id);
            worker.setName(cur.getString(cur.getColumnIndex("name")));
            worker.setPhone(cur.getString(cur.getColumnIndex("phone")));
            worker.setDepartment(cur.getString(cur.getColumnIndex("department")));
            worker.setGroup(cur.getString(cur.getColumnIndex("mygroup")));
            worker.setPost(cur.getString(cur.getColumnIndex("post")));
        }
        return worker;
    }

    /**
     * 保存员工信息
     * @param worker
     * @return
     */
    public boolean setWorker(Worker worker){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", worker.getName());
        cv.put("phone", worker.getPhone());
        cv.put("department", worker.getDepartment());
        cv.put("mygroup", worker.getGroup());
        cv.put("post", worker.getPost());
        Long access = db.insert("tb_Worker", null, cv);
        db.close();
        Log.w("setWorker", "保存Worker信息成功?==>" + (access > 0));
        return access>0;
    }

    /**
     * 添加选人界面的人
     * @param username
     * @param project
     * @param department
     * @param group
     * @param post
     * @return
     */
    public boolean setAll_user(String username,String project,String department,String group,String post){
//    public boolean setAll_user(String username,String  project,String department,String group,String post,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put("user_id", worker.getName());/ 已经设置为自动增长,不需要设置
        cv.put("username", username);
        cv.put("project", project);
        cv.put("department", department);
        cv.put("mygroup", group);
        cv.put("post", post);
//        cv.put("phone", phone);电话号码
        Long access = db.insert("tb_all_user", null, cv);
        db.close();
        Log.w("setWorker", "保存Worker信息成功?==>" + (access > 0));
        return access>0;
    }

    /**
     * 选人的三个 spinner 获取数据
     * @param project
     * @param department
     * @param group
     * @return
     */
    public ArrayList<Worker> getAll_Worker(String project, String department, String group){
        ArrayList list =null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_Worker where 1=1  " ;
        if(project!=null&& !project.isEmpty()){ //如果项目(楼盘)不为空 则进行下一步
            sql += " and  project = '"+project+"'";
            if(department!=null && !department.isEmpty()){//如果部门不为空则进行下一步
                sql+=" and department = '"+department+"'";
                if(group!=null && !group.isEmpty()){
                    sql+=" and mygroup = '"+group+"'";
                }
            }
        }
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null & cur.getCount()>0){
            list =new ArrayList();
            int count = cur.getCount();
            cur.moveToFirst();
            for (int i = 0; i < count; i++) {
                Worker worker = new Worker();
                worker.setId(cur.getString(cur.getColumnIndex("worker_id")));
                worker.setName(cur.getString(cur.getColumnIndex("name")));
                worker.setProject(cur.getString(cur.getColumnIndex("project")));
                worker.setDepartment(cur.getString(cur.getColumnIndex("department")));
                worker.setGroup(cur.getString(cur.getColumnIndex("mygroup")));
                worker.setPost(cur.getString(cur.getColumnIndex("post")));
//                worker.setPhone(cur.getString(cur.getColumnIndex("phone")));
                list.add(worker);
                cur.moveToNext();
            }
            cur.close();
        }
        db.close();
        return  list;
    }


    public ArrayList<String> getProject(){

        ArrayList list =null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from tb_Worker group by project  " ;
        Log.w("getProject", sql);
        Cursor cur = db.rawQuery(sql, null);
        if(cur!=null&& cur.getCount()>0){
            int count = cur.getCount();
            Log.w("count", "count:" + count);
            list = new ArrayList<>();
            cur.moveToFirst();
            for (int i = 0; i < count; i++) {
                list.add(cur.getString(cur.getColumnIndex("project")));
                cur.moveToNext();
            }
            cur.close();
        }
        db.close();
        return list;
    }
    public void db_Excute(String sql) {
        // Initial db
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);

        db.close();
    }

    private void db_CheckColumnExist(String tb_name, String col_name,String col_type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "";
        try {
            sql = "SELECT " + col_name + " FROM " + tb_name + " Limit 1";
            Cursor cur = db.rawQuery(sql, null);
            cur.close();
        } catch (Exception e) {
            db.execSQL("ALTER TABLE " + tb_name + " ADD COLUMN " + col_name
                    + " " + col_type);
        }
        db.close();
    }
}
