package cn.com.yisquare.ibms.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.bean.WorkOrder;

public class Datil_publc_Fragment extends Fragment {
    private EditText et_fpd_gdbh,et_fpd_gdms,et_fpd_jjcd,et_fpd_gdly,et_fpd_xgys,et_fpd_scsj,et_fpd_gzms,et_fpd_dzyy;
    protected WorkOrder workOrder;
    private Mywoyaojiedan mylistener;
    public interface Mywoyaojiedan {//定义一个内部接口
        void onwoyaojiedan(WorkOrder infoworkorder);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("DetailFragment", "==onAttach()");
        if (getActivity() instanceof Mywoyaojiedan) {//如果宿主Activity实现了该接口
            mylistener = (Mywoyaojiedan) getActivity();//就将mylistener 指向该Activity
        }else{
            Log.w("接口未实现", "Mywoyaojiedan.onwoyaojiedan");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publicfix_detail, null);
        et_fpd_gdbh = ((EditText) view.findViewById(R.id.et_fpd_gdbh));
        et_fpd_gdms = ((EditText) view.findViewById(R.id.et_fpd_gdms));
        et_fpd_jjcd = ((EditText) view.findViewById(R.id.et_fpd_jjcd));
        et_fpd_gdly = ((EditText) view.findViewById(R.id.et_fpd_gdly));
        et_fpd_xgys = ((EditText) view.findViewById(R.id.et_fpd_xgys));
        et_fpd_scsj = ((EditText) view.findViewById(R.id.et_fpd_scsj));
        et_fpd_gzms = ((EditText) view.findViewById(R.id.et_fpd_gzms));
        et_fpd_dzyy = ((EditText) view.findViewById(R.id.et_fpd_dzyy));
        view.findViewById(R.id.ll_fpd_woyaojiedan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylistener.onwoyaojiedan(workOrder);
            }
        });
        Toast.makeText(getActivity(), getArguments().getString("tabIndex") + "", Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        workOrder = ((Main2Activity) getActivity()).getWorkOrder();
        if(workOrder==null){
            Toast.makeText(getContext(), "传递工单失败", Toast.LENGTH_SHORT).show();
            return;
        }
        et_fpd_gdbh.setText(workOrder.getNumber());//工单编号
        et_fpd_gdms.setText(workOrder.getDescription());//工单描述
        et_fpd_jjcd.setText(workOrder.getUrgency_level());//紧急程度
        et_fpd_gdly.setText(workOrder.getMyfrom()==0?"BA子系统":"用户报修");//工单来源
        et_fpd_xgys.setText(workOrder.getAbout());//相关因素
        et_fpd_scsj.setText(workOrder.getCreate_time());//生成时间
        et_fpd_gzms.setText(workOrder.getMalfunction());//故障描述
        et_fpd_dzyy.setText(workOrder.getReason());
    }

}
