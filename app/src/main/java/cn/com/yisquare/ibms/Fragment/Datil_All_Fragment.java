package cn.com.yisquare.ibms.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.bean.WorkOrder;

public class Datil_All_Fragment extends Fragment {
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
    protected WorkOrder wk;
    String level[] = {"一级","二级","三级","四级"};
    String malfunction[] = {"新风机不送风","新风机风速过大","新风机送风温度过高","新风机噪音过大"};



    public interface Mywoyaojiedan {//定义一个内部接口
        void onwoyaojiedan(WorkOrder infoworkorder);
    }

      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_msg, null);


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
        /** 接受选择 区域*/
        rl_msg_help = ((RelativeLayout) view.findViewById(R.id.rl_msg_help));
        //拒绝求助
        ll_accept_no = ((LinearLayout) view.findViewById(R.id.ll_accept_no));
        //同意求助
        ll_accept_ok = ((LinearLayout) view.findViewById(R.id.ll_accept_ok));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        wk = ((Main2Activity) getActivity()).getWorkOrder();
        if(wk ==null){
            Toast.makeText(getContext(), "传递工单失败", Toast.LENGTH_SHORT).show();
            return;
        }

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
        et_accepted_xzr.setText(wk.getWork_helper().getName());
        //协助人岗位
        et_accepted_xzrgw.setText(wk.getWork_helper().getPost());
        //故障描述
        et_accepted_gzms.setText(wk.getMalfunction());
        //导致原因
        et_accepted_dzyy.setText(wk.getReason());
        /** 接受选择 区域*/
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
                ll_detail.setVisibility(View.GONE);
            }
        });

    }

}
