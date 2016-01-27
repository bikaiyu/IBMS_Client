package cn.com.yisquare.ibms.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Close_Activity;
import cn.com.yisquare.ibms.activity.FindPeopleActivity;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.activity.Pause_Activity;
import cn.com.yisquare.ibms.adapter.List_menu_adapter;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenu;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenuCreator;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenuItem;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenuListView;

import static cn.com.yisquare.ibms.utils.Tools.dp2px;

public class Pause_Fragment extends Fragment {
    protected SwipeMenuListView smlv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pause, null);
        smlv = ((SwipeMenuListView) view.findViewById(R.id.smlv_activity));
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem restartItem = new SwipeMenuItem(getContext());
                restartItem.setWidth(dp2px(getContext(),60));
                restartItem.setTitle("重启");
                restartItem.setTitleSize(18);
                restartItem.setTitleColor(Color.WHITE);
                restartItem.setBackground(new ColorDrawable(Color.RED));

                SwipeMenuItem closeItem = new SwipeMenuItem(getContext());
                closeItem.setWidth(dp2px(getContext(),60));
                closeItem.setBackground(new ColorDrawable(Color.rgb(0xe3, 0xe3, 0xe3)));
                closeItem.setTitle("关闭");
                closeItem.setTitleColor(Color.BLACK);
                closeItem.setTitleSize(18);

                SwipeMenuItem helpItem = new SwipeMenuItem(getContext());
                helpItem.setWidth(dp2px(getContext(),60));
                helpItem.setBackground(new ColorDrawable(Color.rgb(38, 180, 226)));
                helpItem.setTitle("求助");
                helpItem.setTitleColor(Color.WHITE);
                helpItem.setTitleSize(18);

                //倒序放入
                menu.addMenuItem(restartItem);//0
                menu.addMenuItem(closeItem);//1
                menu.addMenuItem(helpItem); //2


            }
        };
        smlv.setMenuCreator(creator);
        smlv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                Intent intent =null;
                int request_code = MyApplication.from_pause_fragment_restart;
                switch (index){
                    case 0://重启
                        Toast.makeText(getContext(),"重启",Toast.LENGTH_LONG).show();
                        ((Main2Activity) getActivity()).rb[2].performClick();
//                        request_code = MyApplication.from_pause_fragment_restart;
//                        intent = new Intent(getActivity(), Pause_Activity.class);
                        break;
                    case 1://关闭
                        Toast.makeText(getContext(), "关闭", Toast.LENGTH_LONG).show();
                        request_code = MyApplication.CLOSE_ACTIVITY;
                        intent = new Intent(getActivity(), Close_Activity.class);
                        getActivity().startActivityForResult(intent,request_code);
                        break;
                    case 2://求助
                        Toast.makeText(getContext(), "求助", Toast.LENGTH_LONG).show();
                        intent = new Intent(getActivity(), FindPeopleActivity.class);
                        getActivity().startActivityForResult(intent,request_code);
                        break;
                }

            }
        });

        List_menu_adapter adapter = new List_menu_adapter(getContext(),fillList());
        smlv.setAdapter(adapter);

        return view;
    }

    private ArrayList<HashMap<String, String>> fillList() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title","卓越中心城"+(1+i)+"栋"+(34-i)+"层新风机故障");
            map.put("msg","空调故障,电路故障");
            map.put("time",i+1+"小时前暂停");
            map.put("time_right",""+(15+i));
            list.add(map);
        }
        return list;

    }

}
