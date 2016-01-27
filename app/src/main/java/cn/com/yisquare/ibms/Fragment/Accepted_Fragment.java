package cn.com.yisquare.ibms.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Close_Activity;
import cn.com.yisquare.ibms.activity.FindPeopleActivity;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.activity.MainActivity;
import cn.com.yisquare.ibms.bean.WorkOrder;
import cn.com.yisquare.ibms.bean.Worker;

public class Accepted_Fragment extends Fragment {
    /**详情页部分*/
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

    protected WorkOrder workOrder;
    private Mywoyaojiedan mylistener;
    protected View ll_fpd_woyaojiedan;
    protected View rl_detail;
    protected View tv_accept_nodata;

    public interface Mywoyaojiedan {//定义一个内部接口
        void onwoyaojiedan(WorkOrder infoworkorder);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("DetailFragment", "==onAttach()");
        if (getActivity() instanceof Mywoyaojiedan) {//如果宿主Activity实现了该接口
            mylistener = (Mywoyaojiedan) getActivity();//就将mylistener 指向该Activity
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publicfix_detail, null);
        //详情页 layout
        ll_detail = ((LinearLayout) view.findViewById(R.id.ll_msg_detail));
        //工单编号
        et_accepted_number = ((EditText) view.findViewById(R.id.et_accepted_number));
        //工单描述
        et_accepted_gdms = ((EditText) view.findViewById(R.id.et_accepted_gdms));
        //紧急程度
        et_accepted_jjcd = ((EditText) view.findViewById(R.id.et_accepted_jjcd));
        //工单来源
        et_accepted_gdly = ((EditText) view.findViewById(R.id.et_accepted_gdly));
        //相关因素
        et_accepted_xgys = ((EditText) view.findViewById(R.id.et_accepted_xgys));
        //生成时间
        et_accepted_scsj = ((EditText) view.findViewById(R.id.et_accepted_scsj));
        //接单时间
        et_accepted_jdsj = ((EditText) view.findViewById(R.id.et_accepted_jdsj));
        //执行人
        et_accepted_zxr = ((EditText) view.findViewById(R.id.et_accepted_zxr));
        //执行人岗位
        et_accepted_zxrgw = ((EditText) view.findViewById(R.id.et_accepted_zxrgw));
        //协助人
        et_accepted_xzr = ((EditText) view.findViewById(R.id.et_accepted_xzr));
        //协助人岗位
        et_accepted_xzrgw = ((EditText) view.findViewById(R.id.et_accepted_xzrgw));
        //故障描述
        et_accepted_gzms = ((EditText) view.findViewById(R.id.et_accepted_gzms));
        //导致原因
        et_accepted_dzyy = ((EditText) view.findViewById(R.id.et_accepted_dzyy));
        ll_fpd_woyaojiedan = view.findViewById(R.id.ll_fpd_woyaojiedan);
        rl_detail = view.findViewById(R.id.rl_detail);
        tv_accept_nodata = view.findViewById(R.id.tv_accept_nodata);
        //最底下 开始计时,关闭工单 发起求助   设置为显示
        view.findViewById(R.id.ll_accepted_toolbar).setVisibility(View.VISIBLE);
        view.findViewById(R.id.ll_accepted_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始计时
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("维修前是否要拍照?")
                        .setPositiveButton("去拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Accepted_Fragment.this.getActivity(), "开始计时", Toast.LENGTH_SHORT).show();
                                workOrder.setStatus(4);
                                MyApplication.getInstance().getDbHelper().updateWorkOrder(workOrder.getNumber(), "status", "4");
                                ((Main2Activity) getActivity()).rb[2].performClick();//切换到第三页
                            }
                        })
                        .setNegativeButton("不需要拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Accepted_Fragment.this.getActivity(), "开始计时", Toast.LENGTH_SHORT).show();
                                workOrder.setStatus(4);
                                MyApplication.getInstance().getDbHelper().updateWorkOrder(workOrder.getNumber(), "status", "4");
                                ((Main2Activity) getActivity()).rb[2].performClick();//切换到第三页
                            }
                        }).show();


            }
        });
        view.findViewById(R.id.ll_accepted_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭工单
                Toast.makeText(Accepted_Fragment.this.getActivity(), "关闭工单", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("维修完毕是否要拍照?")
                        .setPositiveButton("去拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               Intent intent = new Intent(getActivity(), Close_Activity.class);
                                intent.putExtra("from", MyApplication.from_accept_fragment_close);
                                getActivity().startActivityForResult(intent, MyApplication.from_accept_fragment_close);
                            }
                        })
                        .setNegativeButton("不需要拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), Close_Activity.class);
                                intent.putExtra("from", MyApplication.from_accept_fragment_close);
                                getActivity().startActivityForResult(intent, MyApplication.from_accept_fragment_close);
                            }
                        }).show();


            }
        });
        view.findViewById(R.id.ll_accepted_material).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Toast.makeText(getContext(), "《领取物料》功能会在下版本推出。", Toast.LENGTH_LONG).show();
            }
        });
        view.findViewById(R.id.ll_accepted_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发起求助
                Toast.makeText(Accepted_Fragment.this.getActivity(), "发起求助", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), FindPeopleActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        workOrder = ((Main2Activity) getActivity()).getWorkOrder();
        if(workOrder==null){
//            Toast.makeText(getContext(),"传递工单失败,从数据库中读取",Toast.LENGTH_SHORT).show();
            ArrayList<WorkOrder> workOrder = MyApplication.getInstance().getDbHelper().getWorkOrder(MyApplication.getInstance().getWorker().getId(),//当前用户ID
                    ((Main2Activity) getActivity()).classes,//公共维修 or客户维修
                    3);//已接单
            if(workOrder==null || workOrder.isEmpty()){
                rl_detail.setVisibility(View.GONE);
                tv_accept_nodata.setVisibility(View.VISIBLE);
            }else{
                rl_detail.setVisibility(View.VISIBLE);
                tv_accept_nodata.setVisibility(View.GONE);
                this.workOrder = workOrder.get(0);
                ((Main2Activity) getActivity()).workOrder = this.workOrder;
            }
        }else{
            if(workOrder.getWork_owner()==null){
                rl_detail.setVisibility(View.GONE);
                tv_accept_nodata.setVisibility(View.VISIBLE);
            }else
                 fillDetail_All(workOrder);
        }
    }
    public void fillDetail_All(WorkOrder wk){
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
        Worker work_helper = wk.getWork_helper();
        if(work_helper==null || work_helper.getName()==null|| work_helper.getName().isEmpty())
            et_accepted_xzr.setText("暂无");
        else
            et_accepted_xzr.setText(wk.getWork_helper().getName());
        //协助人岗位
        if(work_helper==null || work_helper.getPost()==null|| work_helper.getPost().isEmpty())
            et_accepted_xzrgw.setText("暂无");
        else
            et_accepted_xzrgw.setText(wk.getWork_helper().getPost());
        //故障描述
        et_accepted_gzms.setText(wk.getMalfunction());
        //导致原因
        if(wk.getReason()==null)
            et_accepted_dzyy.setText("");
        else
            et_accepted_dzyy.setText(wk.getReason());

    }
}
