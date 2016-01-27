package cn.com.yisquare.ibms.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.adapter.List_menu_adapter;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenuListView;

public class Close_Fragment extends Fragment {
    protected SwipeMenuListView smlv;
    protected ListView lv_close_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_close, null);
        lv_close_fragment = ((ListView) view.findViewById(R.id.lv_close_fragment));

        List_menu_adapter adapter = new List_menu_adapter(getContext(),fillList());
        lv_close_fragment.setAdapter(adapter);

        return view;
    }

    private ArrayList<HashMap<String, String>> fillList() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("title","卓越中心城"+(1+i)+"栋"+(25-i)+"层新风机故障");
            map.put("msg","空调故障,电路故障");
            map.put("time",i+1+"小时前关闭");
            map.put("time_right",""+(15+i));
            list.add(map);
        }
        return list;

    }

}
