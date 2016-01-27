package cn.com.yisquare.ibms.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Close_Activity;
import cn.com.yisquare.ibms.activity.FindPeopleActivity;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.activity.Pause_Activity;
import cn.com.yisquare.ibms.bean.WorkOrder;

public class Doing_Fragment extends Fragment {
    protected WorkOrder workOrder;
    protected ImageView iv_doing_item_from_icon;
    protected TextView tv_fdonging_item_title;
    protected TextView tv_fdonging_item_msg;
    protected TextView tv_doing_date;
    protected TextView tv_doing_time;
    protected TextView tv_accept_date;
    protected TextView tv_accept_time;
    protected TextView tv_start_date;
    protected TextView tv_start_time;
    protected TextView tv_valid_time;
    protected TextView tv_valid_classes;
    protected LinearLayout ll_doing_pause;
    protected LinearLayout ll_doing_close;
    protected LinearLayout ll_help_help;
    protected View rl_fdonging_item;
    public Context context;
    private LinearLayout ll_detail_material;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doing, null);
        /**正在执行的工单信息 */
        rl_fdonging_item = view.findViewById(R.id.rl_fdonging_item);
        //前面的图标 齿轮:系统生成 人头:用户提交
        iv_doing_item_from_icon = ((ImageView) view.findViewById(R.id.iv_msg_item_userhead_icon));
        //工单标题
        tv_fdonging_item_title = ((TextView) view.findViewById(R.id.tv_fdonging_item_title));
        //故障摘要
        tv_fdonging_item_msg = ((TextView) view.findViewById(R.id.tv_fdonging_item_msg));
        /**时间相关 */
        tv_doing_date = ((TextView) view.findViewById(R.id.tv_create_date));
        tv_doing_time = ((TextView) view.findViewById(R.id.tv_create_time));
        tv_accept_date = ((TextView) view.findViewById(R.id.tv_accept_date));
        tv_accept_time = ((TextView) view.findViewById(R.id.tv_accept_time));
        tv_start_date = ((TextView) view.findViewById(R.id.tv_start_date));
        tv_start_time = ((TextView) view.findViewById(R.id.tv_start_time));
        // 中间蓝色的 有效工时
        tv_valid_time = ((TextView) view.findViewById(R.id.tv_valid_time));
        // 中间蓝色 有效工时下面的 工单类别
        tv_valid_classes = ((TextView) view.findViewById(R.id.tv_valid_classes));
        /**底部按钮*/
        //暂停工单
        ll_doing_pause = ((LinearLayout) view.findViewById(R.id.ll_doing_pause));
        //关闭工单
        ll_doing_close = ((LinearLayout) view.findViewById(R.id.ll_detail_close));
        //领取物料
        ll_detail_material = ((LinearLayout) view.findViewById(R.id.ll_detail_material));
        //发起求助
        ll_help_help = ((LinearLayout) view.findViewById(R.id.ll_help_help));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        workOrder = ((Main2Activity) getActivity()).getWorkOrder();
        if(workOrder==null){
            Toast.makeText(getContext(),"请重新接单",Toast.LENGTH_SHORT).show();
            return;
        }
        tv_fdonging_item_title.setText(workOrder.getDescription());
        tv_fdonging_item_msg.setText(workOrder.getMalfunction());

        context = ((Main2Activity) getActivity());
        rl_fdonging_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity activity = (Main2Activity) Doing_Fragment.this.getActivity();
                //详情页 layout
                activity.ll_detail.setVisibility(View.VISIBLE);
                activity.setOtherVisibility(View.VISIBLE);
                activity.ll_doing_doing.setVisibility(View.VISIBLE);
            }
        });
        ll_doing_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Pause_Activity.class);
                getActivity().startActivityForResult(intent, MyApplication.from_doing_fragment_pause);
            }
        });
        ll_doing_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context,Close_Activity.class);
//                getActivity().startActivityForResult(intent, MyApplication.from_doing_fragment_close);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("维修完毕是否要拍照?")
                        .setPositiveButton("去拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                workOrder.setStatus(4);
//                                MyApplication.getInstance().getDbHelper().updateWorkOrder(workOrder.getNumber(),"status","4");
//                                ((Main2Activity) getActivity()).rb[2].performClick();//切换到第三页
                                Intent intent = new Intent(context,Close_Activity.class);
                                getActivity().startActivityForResult(intent, MyApplication.from_doing_fragment_close);
                            }
                        })
                        .setNegativeButton("不需要拍照", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                workOrder.setStatus(4);
//                                MyApplication.getInstance().getDbHelper().updateWorkOrder(workOrder.getNumber(), "status", "4");
//                                ((Main2Activity) getActivity()).rb[2].performClick();//切换到第三页
                                Intent intent = new Intent(context,Close_Activity.class);
                                getActivity().startActivityForResult(intent, MyApplication.from_doing_fragment_close);
                            }
                        }).show();


            }
        });
        ll_detail_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "《领取物料》功能会在下版本推出。", Toast.LENGTH_LONG).show();
            }
        });
        ll_help_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FindPeopleActivity.class);
                getActivity().startActivityForResult(intent, MyApplication.from_doing_fragment_help);
            }
        });
    }

}
