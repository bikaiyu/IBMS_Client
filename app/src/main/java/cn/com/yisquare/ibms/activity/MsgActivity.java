package cn.com.yisquare.ibms.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.adapter.List_msg_adapter;
import cn.com.yisquare.ibms.bean.Msg;
import cn.com.yisquare.ibms.bean.WorkOrder;
import cn.com.yisquare.ibms.bean.Worker;

import static cn.com.yisquare.ibms.utils.Tools.MilisToDate;
import static cn.com.yisquare.ibms.utils.Tools.getCurrentTimeMilis;
import static cn.com.yisquare.ibms.utils.Tools.random;

public class MsgActivity extends Activity {
    private List_msg_adapter adapter;
    protected ListView listview;
    protected View blue_line_1;
    protected View blue_line_2;
    protected ArrayList<Msg> list_left,list_right;
    protected LinearLayout ll_detail;
    protected EditText et_accepted_number;
    protected EditText et_accepted_gdms;
    protected EditText et_accepted_jjcd;
    protected EditText et_accepted_gdly;
    protected EditText et_accepted_xgys;
    protected EditText et_accepted_scsj;
    protected EditText et_accepted_jdsj;
    protected LinearLayout ll_accept_no;
    protected LinearLayout ll_accept_ok;
    protected RelativeLayout rl_msg_help;
    protected EditText et_accepted_dzyy;
    protected EditText et_accepted_gzms;
    protected EditText et_accepted_xzrgw;
    protected EditText et_accepted_xzr;
    protected EditText et_accepted_zxrgw;
    protected EditText et_accepted_zxr;

    String level[] = {"一级","二级","三级","四级"};
    String malfunction[] = {"新风机不送风","新风机风速过大","新风机送风温度过高","新风机噪音过大"};
    protected WorkOrder wk;
    public int left_or_right = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        listview = ((ListView) findViewById(R.id.lv_activity_msg));
        initTabView();
        initDetailView();
        list_left = fillMsg(1);
        list_right = fillMsg(0);
        adapter = new List_msg_adapter(this,list_left);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(adapter.getList().get(position),left_or_right);
            }
        });

    }

    private void initDetailView() {
        //详情页 layout
        ll_detail = ((LinearLayout) findViewById(R.id.ll_msg_detail));
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
        et_accepted_jdsj = ((EditText) findViewById(R.id.et_accepted_jdsj));
        //执行人
        et_accepted_zxr = ((EditText) findViewById(R.id.et_accepted_zxr));
        //执行人岗位
        et_accepted_zxrgw = ((EditText) findViewById(R.id.et_accepted_zxrgw));
        //协助人
        et_accepted_xzr = ((EditText) findViewById(R.id.et_accepted_xzr));
        //协助人岗位
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
    }
    private void showDetail(Msg msg, int  who){

        //详情页 layout
        ll_detail.setVisibility(View.VISIBLE);
        wk = msg.getWorkorder();
        //工单编号
        et_accepted_number.setText(wk.getNumber());
        //工单描述
        et_accepted_gdms.setText(wk.getDescription());
        //紧急程度
        et_accepted_jjcd.setText(wk.getUrgency_level());
        //工单来源
        et_accepted_gdly.setText(wk.getMyfrom()==0?"BA子系统":"用户提交");
        //相关因素
        et_accepted_xgys.setText(wk.getAbout());
        //生成时间
        et_accepted_scsj.setText(wk.getCreate_time());
        //接单时间
        et_accepted_jdsj.setText(wk.getAccept_time());
        //执行人
        et_accepted_zxr.setText(wk.getWork_owner().getName());
        //执行人岗位
        et_accepted_zxrgw.setText(wk.getWork_owner().getPost());
        //协助人
//        et_accepted_xzr.setText(wk.getWork_helper().getName());
        et_accepted_xzr.setText("暂无");
        //协助人岗位
//        et_accepted_xzrgw.setText(wk.getWork_helper().getPost());
        et_accepted_xzrgw.setText("暂无");
        //故障描述
        et_accepted_gzms.setText(wk.getMalfunction());
        //导致原因
        et_accepted_dzyy.setText(wk.getReason());
        /** 接受选择 区域*/
        if(who ==1)
            rl_msg_help.setVisibility(View.VISIBLE);
        else
            rl_msg_help.setVisibility(View.GONE);
        //拒绝求助
        ll_accept_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_detail.setVisibility(View.GONE);

            }
        });
        //同意求助
        ll_accept_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MsgActivity.this);
                builder.setTitle("确认接受求助么?");
                builder.setPositiveButton("接受", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //跳转到已接单界面,并在协助人那栏填充 名字 职位
                        //这个是在msg图标点击进来的,所以只需要跳转到Main2Activity中的 rb[1] 已接单中
                        //这里只做演示,所以不传递工单
//                        Intent intent = new Intent(MsgActivity.this,Main2Activity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.putExtra("from",MyApplication.MSG_ACTIVITY_ACCEPT);
//                        intent.putExtra("workOrder",wk);
//
//                        startActivity(intent);
//                        MsgActivity.this.finish();

                et_accepted_xzr.setText("毕开宇");
                et_accepted_xzrgw.setText("修理工");
                    }
                });
                builder.setNegativeButton("拒绝",null);
                builder.show();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(ll_detail.getVisibility()== View.VISIBLE){
            ll_detail.setVisibility(View.GONE);
            return false;
        }else{
            return super.onKeyDown(keyCode,event);
        }

    }

    private void initTabView() {
        blue_line_1 = findViewById(R.id.view_menu_wjd);
        blue_line_2 = findViewById(R.id.view_menu_yjd);

    }
    public void onClickChangeAdapter(View v){
        switch (v.getId()){
            case R.id.btn_tab_wait:
                blue_line_1.setVisibility(View.VISIBLE);
                blue_line_2.setVisibility(View.INVISIBLE);
                adapter.resetList(list_left);
                left_or_right = 1;
                break;
            case R.id.btn_tab_my:
                blue_line_1.setVisibility(View.INVISIBLE);
                blue_line_2.setVisibility(View.VISIBLE);
                adapter.resetList(list_right);
                left_or_right = 0;
                break;
        }
        adapter.notifyDataSetChanged();
    }
    public void goback(View v){
        if(ll_detail.getVisibility()== View.VISIBLE)
            ll_detail.setVisibility(View.GONE);
        else
            this.finish();
    }
    public ArrayList<Msg> fillMsg(int type){
        ArrayList<Msg> list = new ArrayList<Msg>();
        Worker bizhuguan = new Worker();
        Worker lishifu = new Worker();
        bizhuguan.setName("毕开宇");
        bizhuguan.setDepartment("运营维修部");
        bizhuguan.setGroup("设备维修组");
        bizhuguan.setPost("部门主管");
        lishifu.setName("李师傅");
        lishifu.setDepartment("运营维修部");
        lishifu.setGroup("设备维修组");
        lishifu.setPost("空调工 弱电工");
        for (int i = 0; i < 5; i++) {
            Msg msg = new Msg();
            msg.setCreateTime(getCurrentTimeMilis()-random(10000,1000*60*24));
            WorkOrder workOrder =new WorkOrder();
            //工单编号
            workOrder.setNumber("20160101zyzxc"+random(100,999));
            //工单描述
            workOrder.setDescription("卓越中心城"+random(1,25)+"栋"+random(1,36)+"层新风机故障");
            //紧急程度
            workOrder.setUrgency_level(level[random(0,3)]);
            //相关因素
            workOrder.setAbout("空调");
            //生成时间
            long temp_time = getCurrentTimeMilis() - random(20000, 1000 * 60);
            workOrder.setCreate_time(MilisToDate(temp_time));
            long temp2 = temp_time + random(10000, 1000 * 60);

            //接单时间
            workOrder.setAccept_time(MilisToDate(getCurrentTimeMilis()+random(10000, (int)( (getCurrentTimeMilis() - temp_time)))));
            //故障描述
            workOrder.setMalfunction(malfunction[random(0,3)]);
            //导致原因
            workOrder.setReason("1.低压抽屉输出异常.不在380V~410V范围内.\n2.供电线路异常\n3.末端风柜(盘管)风速异常正常值:80%\n4.防尘网前后压差异常");
            //执行人
            workOrder.setWork_owner(lishifu);
            //协助人
            workOrder.setWork_helper(bizhuguan);
            //工单状态
            workOrder.setStatus(3);//3执行中
            //工单来源
            workOrder.setMyfrom(0);//0系统生成 1 用户提交
            //维修类别
            workOrder.setClasses(0);//0:公共维修 1:客户维修 2:维保服务单
            msg.setWorkorder(workOrder);
            //消息类型
            msg.setType(type); //类别 0:我求别人  1: 别人求我
            list.add(msg);
        }
    return list;
    }

}
