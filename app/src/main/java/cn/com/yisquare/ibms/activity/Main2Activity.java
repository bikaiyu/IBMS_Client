package cn.com.yisquare.ibms.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.yisquare.ibms.Fragment.Accepted_Fragment;
import cn.com.yisquare.ibms.Fragment.Close_Fragment;
import cn.com.yisquare.ibms.Fragment.Datil_publc_Fragment;
import cn.com.yisquare.ibms.Fragment.Doing_Fragment;
import cn.com.yisquare.ibms.Fragment.Not_Fragment;
import cn.com.yisquare.ibms.Fragment.Pause_Fragment;
import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.adapter.MyFpAdapter;
import cn.com.yisquare.ibms.bean.WorkOrder;
import cn.com.yisquare.ibms.utils.DBHelper;
import cn.com.yisquare.ibms.view.CustomViewPager;

import static cn.com.yisquare.ibms.utils.Tools.getStringTime;


public class Main2Activity extends FragmentActivity implements Not_Fragment.MyOnItemClickedListener{
    private FragmentTabHost tabHost;
    private Bundle bundle1, bundle2, bundle3,bundle4,bundle5;
    private View[] line;
    private RadioGroup radioGroup;
    public RadioButton[] rb;//各种 接单 未接单 已暂停
    private CompoundButton.OnCheckedChangeListener cblistener;
    protected LinearLayout ll_line;//蓝线
    protected int line_count;
    protected CustomViewPager cviewpager;
    private ArrayList<Fragment> fragmentsList;
    private long exitTime = 0;
    public Handler myhandler;
    public WorkOrder workOrder;
    private List<Fragment> fragmentList;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment lastFragment;
    private List<String> tagList;

    /**详情页部分*/
    public LinearLayout ll_detail;
    public EditText et_accepted_number;
    public EditText et_accepted_gdms;
    public EditText et_accepted_jjcd;
    public EditText et_accepted_gdly;
    public EditText et_accepted_xgys;
    public EditText et_accepted_scsj;
    public EditText et_accepted_jdsj;
    public LinearLayout ll_accept_no;
    public LinearLayout ll_accept_ok;
    public RelativeLayout rl_msg_help;
    public EditText et_accepted_dzyy;
    public EditText et_accepted_gzms;
    public EditText et_accepted_xzrgw;
    public EditText et_accepted_xzr;
    public EditText et_accepted_zxrgw;
    public EditText et_accepted_zxr;


    private boolean DetailIsShowing=false;
    public View rl_accept_time;
    public View rl_accept_people;
    public View rl_doing_post;
    public View rl_helper;
    public View rl_helper_post;
    public View ll_woyaojiedan;
    public View ll_doing_doing;
    protected View ll_accepted_toolbar;
    protected RelativeLayout rl_accept_daozhiyuanyin;
    public int classes = 1;
    protected DBHelper dbHelper;
    protected DialogInterface.OnClickListener onClickListener;
    public View ll_detail_pause;
    public View ll_detail_close;
    public View ll_detail_help;
    private View tab_msg;
    private View tab_schedule;
    private View tab_icenter;
    private View.OnClickListener bottom_tab_click;
    public int resultID = 0;
    public WorkOrder getWorkOrder() {
        return workOrder;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        classes=getIntent().getIntExtra("classes",1);
        dbHelper = MyApplication.getInstance().getDbHelper();
        initView();
        initTabView();
        myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                }
            }
        };

    }

    private void initView() {
        //返回按钮
        findViewById(R.id.iv_title_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_detail.getVisibility()== View.VISIBLE)
                    ll_detail.setVisibility(View.GONE);
                else
                    Main2Activity.this.finish();
            }
        });
        //加号按钮
        findViewById(R.id.iv_title_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "生成工单按钮\n此功能下版本会推出.", Toast.LENGTH_LONG).show();
            }
        });
        //我要接单
        ll_woyaojiedan = findViewById(R.id.ll_woyaojiedan);
        onClickListener = new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (MyApplication.getInstance().getUser().getStatus() == 1) {
                    Toast.makeText(Main2Activity.this, "已经有工单在执行,请先完成当前工单.", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.updateWorkOrder(workOrder.getNumber(), "status", "3");
//                String usernumber = MyApplication.getInstance().getUser().getUsernumber();
                String work_id = MyApplication.getInstance().getWorker().getId();
                //存DB 执行人ID
                boolean isOK = dbHelper.updateWorkOrder(workOrder.getNumber(), "owner_id", work_id);
                if (isOK) {
                    //详情页 layout
                    ll_detail.setVisibility(View.GONE);
                    rb[1].performClick();
                    //设置执行人
                    workOrder.setWork_owner(MyApplication.getInstance().getWorker());
                    dbHelper.updateWorkOrder(workOrder.getNumber(), "accept_time", getStringTime());
                    workOrder.setAccept_time(getStringTime());
                    workOrder.setStatus(3);//工单状态  1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
                    dbHelper.updateWorkOrder(workOrder.getNumber(), "status", "3");//工单状态 0:被指派给维修工 1:存在于工单池 2:已接单 3:执行中 4:已暂停 5:已关闭
                    MyApplication.getInstance().getUser().setStatus(1);//设置当前用户状态为执行中
                }
            }
        };
        ll_woyaojiedan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstance().getUser().getStatus() == 1) {
                    Toast.makeText(Main2Activity.this, "已经有工单在执行,请先完成当前工单.", Toast.LENGTH_SHORT).show();
                    return;
                }
//                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
//                builder.setTitle("维修前是否要拍照?")
//                        .setPositiveButton("去拍照", onClickListener)
//                        .setNegativeButton("不需要拍照", onClickListener).show();

                dbHelper.updateWorkOrder(workOrder.getNumber(), "status", "3");
//                String usernumber = MyApplication.getInstance().getUser().getUsernumber();
                //执行人
                et_accepted_zxr.setText(MyApplication.getInstance().getWorker().getName());
                //执行人岗位
                et_accepted_zxrgw.setText(MyApplication.getInstance().getWorker().getPost());
                //接单时间
                et_accepted_jdsj.setText(getStringTime());
                String work_id = MyApplication.getInstance().getWorker().getId();
                //存DB 执行人ID
                boolean isOK = dbHelper.updateWorkOrder(workOrder.getNumber(), "owner_id", work_id);
                if (isOK) {
                    //详情页 layout
                    ll_detail.setVisibility(View.GONE);
                    rb[1].performClick();
                    //设置执行人
                    workOrder.setWork_owner(MyApplication.getInstance().getWorker());
                    dbHelper.updateWorkOrder(workOrder.getNumber(), "accept_time", getStringTime());
                    workOrder.setAccept_time(getStringTime());
                    workOrder.setStatus(3);//工单状态  1:存在于工单池 2:被指派给维修工 3:已接单 4:执行中 5:已暂停 6:已关闭
                    dbHelper.updateWorkOrder(workOrder.getNumber(), "status", "3");//工单状态 0:被指派给维修工 1:存在于工单池 2:已接单 3:执行中 4:已暂停 5:已关闭
                    MyApplication.getInstance().getUser().setStatus(1);//设置当前用户状态为执行中
                }
            }

        });
        //底部 已接单 工具条
        ll_accepted_toolbar = findViewById(R.id.ll_accepted_toolbar);
        //底部 执行中 工具条
        ll_doing_doing = findViewById(R.id.ll_doing_doing);
        //导致原因 这个布局
        rl_accept_daozhiyuanyin = ((RelativeLayout) findViewById(R.id.rl_accept_daozhiyuanyin));
        manager = getSupportFragmentManager();//获取布局管理器
        Not_Fragment not_fragment = new Not_Fragment();//未接单
        Accepted_Fragment accept_fragment = new Accepted_Fragment();//已接单
        Doing_Fragment doing_fragment = new Doing_Fragment();//执行中
        Pause_Fragment pause_fragment = new Pause_Fragment();//已暂停
        Close_Fragment close_fragment = new Close_Fragment();//已停止
        Datil_publc_Fragment datil_publc_fragment = new Datil_publc_Fragment();//公共维修 工单详情页
        fragmentList = new ArrayList<>();
        tagList = new ArrayList<>();
        fragmentList.add(not_fragment);
        tagList.add("not_fragment");
        fragmentList.add(accept_fragment);
        tagList.add("accept_fragment");
        fragmentList.add(doing_fragment);
        tagList.add("doing_fragment");
        fragmentList.add(pause_fragment);
        tagList.add("pause_fragment");
        fragmentList.add(close_fragment);
        tagList.add("close_fragment");
        fragmentList.add(datil_publc_fragment);
        tagList.add("datil_publc_fragment");

        /** 详情页部分 */
        //详情页 layout
        ll_detail = ((LinearLayout) findViewById(R.id.ll_msg_detail));
        ll_detail.setVisibility(View.GONE);
        //工单编号
        et_accepted_number = ((EditText) findViewById(R.id.et_accepted_number));
        //工单描述
        et_accepted_gdms = ((EditText) findViewById(R.id.et_accepted_gdms));
        //紧急程度
        et_accepted_jjcd = ((EditText) findViewById(R.id.et_accepted_jjcd));
        //工单来源
        et_accepted_gdly = ((EditText) findViewById(R.id.et_accepted_gdly));
        //相关因素
        et_accepted_xgys = ((EditText) findViewById(R.id.et_accepted_xgys));
        //生成时间
        et_accepted_scsj = ((EditText) findViewById(R.id.et_accepted_scsj));
        //接单时间
        rl_accept_time = findViewById(R.id.rl_accept_time);
        et_accepted_jdsj = ((EditText) findViewById(R.id.et_accepted_jdsj));
        //执行人
        rl_accept_people = findViewById(R.id.rl_accept_people);
        et_accepted_zxr = ((EditText) findViewById(R.id.et_accepted_zxr));
        //执行人岗位
        rl_doing_post = findViewById(R.id.rl_doing_post);
        et_accepted_zxrgw = ((EditText) findViewById(R.id.et_accepted_zxrgw));
        //协助人
        rl_helper = findViewById(R.id.rl_helper);
        et_accepted_xzr = ((EditText) findViewById(R.id.et_accepted_xzr));
        //协助人岗位
        rl_helper_post = findViewById(R.id.rl_helper_post);
        et_accepted_xzrgw = ((EditText) findViewById(R.id.et_accepted_xzrgw));
        //故障描述
        et_accepted_gzms = ((EditText) findViewById(R.id.et_accepted_gzms));
        //导致原因
        et_accepted_dzyy = ((EditText) findViewById(R.id.et_accepted_dzyy));
        /** 接受选择 区域*/
        rl_msg_help = ((RelativeLayout) findViewById(R.id.rl_msg_help));
        //拒绝求助
        ll_accept_no = ((LinearLayout) findViewById(R.id.ll_accept_no));
        //同意求助
        ll_accept_ok = ((LinearLayout) findViewById(R.id.ll_accept_ok));
        /** Detail 暂停工单 停止工单 发起求助*/
        ll_detail_pause = findViewById(R.id.ll_detail_pause);
        ll_detail_close = findViewById(R.id.ll_detail_close);
        ll_detail_help = findViewById(R.id.ll_detail_help);
        ll_detail_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Pause_Activity.class);
                Main2Activity.this.startActivityForResult(intent, MyApplication.PAUSE_ACTIVITY);
            }
        });
        ll_detail_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Close_Activity.class);
                Main2Activity.this.startActivityForResult(intent, MyApplication.CLOSE_ACTIVITY);
            }
        });
        ll_detail_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,FindPeopleActivity.class);
                Main2Activity.this.startActivityForResult(intent, MyApplication.FIND_PEOPLE_ACTIVITY);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        ll_detail.setVisibility(View.GONE);
        Intent intent = getIntent();
        int from = intent.getIntExtra("from",0);
        if(from== MyApplication.MSG_ACTIVITY_ACCEPT){
            WorkOrder workOrder = (WorkOrder) intent.getSerializableExtra("workOrder");
            this.workOrder = workOrder;
            rb[1].performClick();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        int from = intent.getIntExtra("from",0);
        if(from== MyApplication.MSG_ACTIVITY_ACCEPT){
            WorkOrder workOrder = (WorkOrder) intent.getSerializableExtra("workOrder");
            this.workOrder = workOrder;
            rb[1].performClick();
        }
    }

    private void initTabView() {
        ll_line = ((LinearLayout) findViewById(R.id.ll_listMenu_line));
        radioGroup = (RadioGroup) findViewById(R.id.rg_listMenu);
        line_count = ll_line.getChildCount();
        line = new View[line_count];
        rb =new RadioButton[line_count];
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < line_count; i++) {
                    line[i].setVisibility(View.INVISIBLE);
                }
                for (int i = 0; i < line_count; i++) {
                    if (rb[i].getId() == checkedId) {
                        line[i].setVisibility(View.VISIBLE);
                        doChengeFragment(i);
                        break;
                    }
                }
            }
        });
        for (int i = 0; i < line_count; i++) {
            line[i] = ll_line.getChildAt(i);
            rb[i] = ((RadioButton) radioGroup.getChildAt(i));
            line[i].setVisibility(View.INVISIBLE);
            rb[i].setChecked(false);
            rb[i].setOnCheckedChangeListener(cblistener);
        }
        rb[0].setChecked(true);
        line[0].setVisibility(View.VISIBLE);

        tab_msg = findViewById(R.id.ll_tool_1);
        findViewById(R.id.ll_tool_2).setClickable(false);
        tab_schedule = findViewById(R.id.ll_tool_3);
        tab_icenter = findViewById(R.id.ll_tool_4);

        bottom_tab_click = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ll_tool_1:
                        resultID = 0;
                        break;
                    case R.id.ll_tool_2:
                        resultID = 1;
                        break;
                    case R.id.ll_tool_3:
                        resultID = 2;
                        break;
                    case R.id.ll_tool_4:
                        resultID = 3;
                        break;
                }
                finishthis(resultID);
            }

        };
    }
    public void finishthis(int code){
        this.setResult(code);
        this.finish();
    }
    private void initViewPager() {
//        cviewpager = ((CustomViewPager) findViewById(R.id.vp_main));
        cviewpager.setCanScroll(false);
        fragmentsList =new ArrayList<Fragment>();
        cviewpager.setOffscreenPageLimit(0);//禁止预加载其他页面
        Fragment fragment0 = new Not_Fragment();
        Bundle bundle0 = new Bundle();
        bundle0.putInt("tabIndex", 0);
        bundle0.putSerializable("handler", (Serializable) myhandler);
        fragment0.setArguments(bundle0);

        Fragment fragment1 = new Accepted_Fragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("tabIndex", 1);
        fragment1.setArguments(bundle1);

        Fragment fragment2 = new Not_Fragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("tabIndex", 2);
        fragment2.setArguments(bundle2);


        Fragment fragment3 = new Pause_Fragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("tabIndex", 3);
        fragment3.setArguments(bundle3);

        Fragment fragment4 = new Pause_Fragment();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("tabIndex", 4);
        fragment4.setArguments(bundle4);

        //公共维修 工单详情页
        Fragment fragment5 = new Datil_publc_Fragment();
        Bundle bundle5 = new Bundle();
        bundle5.putInt("tabIndex", 5);
        fragment5.setArguments(bundle5);

        fragmentsList.add(fragment0);
        fragmentsList.add(fragment1);
        fragmentsList.add(fragment2);
        fragmentsList.add(fragment3);
        fragmentsList.add(fragment4);
        fragmentsList.add(fragment5);
        cviewpager.setAdapter(new MyFpAdapter(getSupportFragmentManager(), fragmentsList));
    }

    private void doChengeFragment(int position) {
        Log.w("位置", "===>" + position);

        transaction = manager.beginTransaction();//启动事务
        if(lastFragment!=null)
            transaction.remove(lastFragment);
        lastFragment = fragmentList.get(position);
        transaction.replace(R.id.layout_container_fragment,lastFragment);

        transaction.commit();//提交事务
        setToolbarGone();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if(ll_detail.getVisibility()== View.VISIBLE) {
                ll_detail.setVisibility(View.GONE);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onNotYetListviewItemClick(WorkOrder wk) {
        /** 未接单 listview 点击事件 */
        // 打包到bundle中 传入 订单详情Datil_publc_Fragment
        //得到wokerorder 填充列表
        //详情页 layout
        ll_detail.setVisibility(View.VISIBLE);
        //详情页最底部的 工具条-我要接单 设置为显示
        ll_woyaojiedan.setVisibility(View.VISIBLE);
        fillDetail(wk);
        // 接单时间,执行人,执行人岗位,协助人,协助人岗位 ==>GONE
        setOtherVisibility(View.GONE);
        this.workOrder = wk;
    }
    public void setOtherVisibility(int canshu){
        rl_accept_time.setVisibility(canshu);
        rl_accept_people.setVisibility(canshu);
        rl_doing_post.setVisibility(canshu);
        rl_helper.setVisibility(canshu);
        rl_helper_post.setVisibility(canshu);
    }

    public void fillDetail(WorkOrder wk){
        ll_detail.setVisibility(View.VISIBLE);
        //工单编号
        et_accepted_number.setText(wk.getNumber());
        //工单描述
        et_accepted_gdms.setText(wk.getDescription());
        //紧急程度
        et_accepted_jjcd.setText(wk.getUrgency_level());
        //工单来源
        et_accepted_gdly.setText(wk.getMyfrom()==1?"BA子系统":"用户提交");
        //相关因素
        et_accepted_xgys.setText(wk.getAbout());
        //生成时间
        et_accepted_scsj.setText(wk.getCreate_time());
        //故障描述
        et_accepted_gzms.setText(wk.getMalfunction());
        if(wk.getReason()==null || wk.getReason().isEmpty())
            rl_accept_daozhiyuanyin.setVisibility(View.GONE);
        else
            //导致原因
            et_accepted_dzyy.setText(wk.getReason());
    }
    /**设置详情页底部工具条不显示*/
    public void setToolbarGone(){
        rl_msg_help.setVisibility(View.GONE);
        ll_accepted_toolbar.setVisibility(View.GONE);
        ll_doing_doing.setVisibility(View.GONE);
        ll_woyaojiedan.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case MyApplication.PAUSE_ACTIVITY:
//                Toast.makeText(this,"PAUSE_ACTIVITY",Toast.LENGTH_SHORT).show();
                /** 未完成 : 更改执行中 的订单状态 status=5 已暂停*/
                rb[3].performClick();
                break;
            case MyApplication.CLOSE_ACTIVITY:
//                Toast.makeText(this,"CLOSE_ACTIVITY",Toast.LENGTH_SHORT).show();
                rb[4].performClick();
                /** 未完成 : 更改执行中 的订单状态 status=6 已关闭*/
                break;
            case MyApplication.FIND_PEOPLE_ACTIVITY:
//                Toast.makeText(this,"FIND_PEOPLE_ACTIVITY",Toast.LENGTH_SHORT).show();
                break;
            case MyApplication.from_accept_fragment_close:
                /** 未完成 : 更改已接单 的订单状态 status=6 已关闭*/
                rb[4].performClick();
                break;
            case MyApplication.from_accept_fragment_help:
                break;
            case MyApplication.from_doing_fragment_pause:
                rb[3].performClick();
                break;
            case MyApplication.from_doing_fragment_close:
                rb[4].performClick();
                break;
            case MyApplication.from_pause_fragment_restart:
                rb[2].performClick();
                break;
            case MyApplication.from_pause_fragment_close:
                rb[4].performClick();
                break;


        }
    }
}
