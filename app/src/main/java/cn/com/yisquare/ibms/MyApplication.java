package cn.com.yisquare.ibms;

import android.app.Application;

import cn.com.yisquare.ibms.bean.User;
import cn.com.yisquare.ibms.bean.Worker;
import cn.com.yisquare.ibms.utils.DBHelper;


/**
 * Created by bikai on 2016/1/5.
 */


public class MyApplication extends Application {
    private static MyApplication instance;

    private User user;
    private Worker worker;
    public static final int PAUSE_ACTIVITY = 2;
    public static final int CLOSE_ACTIVITY = 4;
    public static final int FIND_PEOPLE_ACTIVITY = 5;
    public static final int MSG_ACTIVITY = 6;
    public static final int MSG_ACTIVITY_ACCEPT = 7;
    public static final int OK = 5;
    public static final int from_accept_fragment_close = 8;
    public static final int from_accept_fragment_help = 9;
    public static final int from_doing_fragment_pause = 10;
    public static final int from_doing_fragment_close = 11;
    public static final int from_doing_fragment_help = 12;
    public static final int from_pause_fragment_restart = 13;
    public static final int from_pause_fragment_close = 14;
    public static final int from_pause_fragment_help = 15;

    private DBHelper dbHelper;
    private String DB_NAME = "IBMS";
    private int DB_VERSION = 1;
    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dbHelper = new DBHelper(getApplicationContext());
        dbHelper.db_Excute("delete from tb_WorkOrder");
        dbHelper.db_Excute("delete from tb_Worker");
        if(dbHelper.getWorkOrder("201601121233001")==null){
            String[] array = {
                    "INSERT INTO tb_WorkOrder VALUES (201601121233001, '卓越中心城5001照明故障', '一般', '照明', '2016/1/12 12:33',null,'换灯管有材料', '1.灯管寿命到了\n2.供电开关复位异常', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233002, '卓越中心城43F防水故障', '紧急', '防水', '2016/1/13 12:33',null,'洗手盆漏水', '1.瓷盆破裂\n2.防水胶脱落', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233003, '卓越中心城4402单元门故障', '紧急', '单元门', '2016/1/14 12:33',null,'维修单元门', '1.锁芯进入异物\n2.门禁系统出错', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233004, '卓越中心城3406网络故障', '紧急', '网络', '2016/1/15 12:33',null,'维修网络', '1.水晶头损坏\n2.交换机死机', 1, 2,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233005, '卓越中心城806空调故障', '紧急', '空调', '2016/1/16 12:33',null,'空调开关有问题', '1.空调开关复位异常', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233006, '卓越中心城1楼 大堂公共设施', '一般', '公共设施', '2016/1/17 12:33',null,'量广告位尺寸', '1.联系相关人员', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233007, '卓越中心城802电脑故障', '一般', '客户维修', '2016/1/18 12:33',null,'维修电脑', '1.电脑软件故障\n2.电脑硬件故障', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233008, '卓越中心城49楼天花板脱落', '紧急', '公共设施', '2016/1/19 12:33',null,'公共区域天花板脱落', '1.固定胶水脱落', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233009, '卓越中心城2301照明故障', '一般', '照明', '2016/1/20 12:33',null,'换灯有材料', '1.更换灯管', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233010, '卓越中心城51A01防水', '紧急', '卫生间', '2016/1/21 12:33',null,'女卫洗手盆水管漏水', '1.防水胶脱落', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233011, '卓越中心城3楼清理', '一般', '客户维修', '2016/1/22 12:33',null,'胜记清理积水盆', '1.异物堵塞', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233012, '卓越中心城3305门锁故障', '紧急', '门锁', '2016/1/23 12:33',null,'开锁', '1.钥匙遗失\n2.提供相关证明', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233013, '卓越中心城2203设备故障', '紧急', '设备故障', '2016/1/24 12:33',null,'流量计异常', '1.参数输入错误\n2.设备线路串扰', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233014, '卓越中心城2202设备故障', '紧急', '设备故障', '2016/1/25 12:33',null,'流量计异常', '1.参数输入错误\n2.设备线路串扰', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233015, '卓越中心城2602施工', '一般', '公共设施', '2016/1/26 12:33',null,'布线 装插座', '1.工作计划中..', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233016, '卓越中心城33楼卫生间', '一般', '卫生间', '2016/1/27 12:33',null,'男卫第二个小便池不冲水', '1.红外感应器故障\n2.供水开关故障', 1, 2,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233017, '卓越中心城12楼卫生间供水故障', '紧急', '供水', '2016/1/28 12:33',null,'女卫没水', '1.供水故障\n2.磁控阀没电', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233018, '卓越中心城1203茶水间供水故障', '紧急', '供水', '2016/1/29 12:33',null,'茶水间没水', '1.供水故障\n2.磁控阀没电', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233019, '卓越中心城1332天花板故障', '一般', '设备故障', '2016/1/30 12:33',null,'走道天花移位，及消防楼梯灯不亮','1.修理后没有归位\n2.意外损坏', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233020, '卓越中心城1楼大厅工具需求', '一般', '公共设施', '2016/1/31 12:33',null,'挂装饰（需带梯子）找文娉', '1.通知工程部送梯子过来', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233021, '卓越中心城20楼卫生间故障', '一般', '卫生间', '2016/2/1 12:33',null,'女卫第一个蹲位没感应', '1.感应器损坏\n2.感应器被遮挡\n3.感应器线路损坏', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233022, '卓越中心城2509开窗', '一般', '公共设施', '2016/2/2 12:33',null,'开窗', '1.去现场查看窗户是否有损坏', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233023, '卓越中心城51楼卫生间故障', '紧急', '卫生间', '2016/2/3 12:33',null,'兆盈 女卫洗手盆堵塞', '1.清理过滤网\n2.疏通管道', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233024, '卓越中心城1401供电相关', '一般', '公共设施', '2016/2/4 12:33',null,'换插座', '1.插座跳线不能回跳\n2.供电故障', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233025, '卓越中心城15#消防门故障', '一般', '消防门', '2016/2/5 12:33',null,'消防通道消防门关不上', '1.关门器装反\n2.门扇合页不平', 1, 2,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233026, '卓越中心城1901修门锁', '紧急', '门锁', '2016/2/6 12:33',null,'维修大门锁', '1.门锁损坏', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233027, '卓越中心城4107电脑故障', '一般', '客户维修', '2016/2/7 12:33',null,'维修电脑', '1.电脑中病毒\n2.电脑硬件故障', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233028, '卓越中心城801照明故障', '一般', '照明', '2016/2/8 12:33',null,'换灯没材料', '1.物料不充足\n2.相关材料不充足', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233029, '卓越中心城47楼卫生间供水', '一般', '供水', '2016/2/9 12:33',null,'男女卫水太小', '1.磁控阀电压不足\n2.供水阀门未开到位', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233030, '卓越中心城1410供电相关', '一般', '公共设施', '2016/2/10 12:33',null,'地插座线坏', '1.插座损坏', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233031, '卓越中心城负二楼日常维护', '一般', '公共设施', '2016/2/11 12:33',null,'污水管涮漆', '1.计划工作内...', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233032, '卓越中心城20楼空调', '一般', '空调', '2016/2/12 12:33',null,'男卫没空调', '1.空调感应器失效\n2.空调供电故障\n3.温度感应器故障', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233033, '卓越中心城2602施工相关', '一般', '公共设施', '2016/2/13 12:33',null,'布线 装插座', '1.计划工作内..', 1, 1,1, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233034, '卓越中心城3楼清理', '一般', '客户维修', '2016/2/14 12:33',null,'胜记 清理积水盆', '1.清理过滤网\n2.疏通管道', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233035, '卓越中心城负二楼2091防水', '紧急', '公共设施', '2016/2/15 12:33',null,'车位漏水', '1.空调排水管漏水\n2.供水管冷凝水', 1, 2,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233036, '卓越中心城1楼 礼宾部网络故障', '紧急', '网络', '2016/2/16 12:33',null,' 电话线水晶头坏', '1.同时检查网口插座', 1, 1,2, null, null)",
                    "INSERT INTO tb_WorkOrder VALUES (201601121233037, '卓越中心城15号照明', '紧急', '照明', '2016/2/17 12:33',null,'楼道灯不亮', '1.光敏开关故障\n2.声敏开关故障\n3.供电故障', 1, 1,1, null, null)"}
                    ;
            for (int i = 0; i < array.length; i++) {
                dbHelper.db_Excute(array[i]);
            }
            String[] array_tb_Worker = {
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (1, '毕开宇', '时代广场一期', '机电CBD1', '维保班2', '维修员', 12345678912);",
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (2, '陈东', '世纪中心塔楼', '电梯部2', '世纪中心塔楼1', '维修员', 12345678912);",
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (3, '蔡滨', '世纪中心塔楼', '电梯部1', '世纪中心塔楼1', '维修员', 12345678912);",
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (4, '段小方', '时代广场二期', '机电CBD', '维保班', '维修员', 12345678912);",
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (5, '张万称', '时代广场一期', '机电CBD2', '维保班3', '维修员', 12345678912);",
                    "INSERT INTO tb_Worker (worker_id,name,project,department,mygroup,post,phone) VALUES (6, '刘合奎', '世纪中心塔楼', '电梯部', '世纪中心塔楼2', '工程师', 12345678912);"
            };
            if(dbHelper.getWorker("1")==null)
            for (int i = 0; i < array_tb_Worker.length; i++) {
                dbHelper.db_Excute(array_tb_Worker[i]);
            }
        }

    }
    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
