package cn.com.yisquare.ibms.Fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.adapter.NotYetAdapter;
import cn.com.yisquare.ibms.bean.WorkOrder;


public class Not_Fragment extends Fragment {
    protected ListView listview;
    private MyOnItemClickedListener mylistener;
    public interface MyOnItemClickedListener {//定义一个内部接口
         void onNotYetListviewItemClick(WorkOrder infoworkorder);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("DetailFragment", "==onAttach()");
        if (getActivity() instanceof MyOnItemClickedListener) {//如果宿主Activity实现了该接口
            mylistener = (MyOnItemClickedListener) getActivity();//就将mylistener 指向该Activity
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notyet, null);
        listview = ((ListView) view.findViewById(R.id.listview_fragment_notyet));


//        Toast.makeText(getActivity(),getArguments().getString("tabIndex")+"",Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int classes = ((Main2Activity) getActivity()).classes;
        Log.w("Not_Fragment", "classes========>" + classes);
        final NotYetAdapter adapter = new NotYetAdapter(getContext(), MyApplication.getInstance().getDbHelper().getWorkOrder(classes,1));//公共维修 or 客户维修 参数2: 工单池
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Not_Fragment.this.getContext(), adapter.getList().get(position).getDescription(), Toast.LENGTH_SHORT).show();
                mylistener.onNotYetListviewItemClick(adapter.getList().get(position));
            }
        });
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
