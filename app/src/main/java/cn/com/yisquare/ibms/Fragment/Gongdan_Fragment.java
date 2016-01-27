package cn.com.yisquare.ibms.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.activity.Main2Activity;
import cn.com.yisquare.ibms.adapter.List_menu_adapter;
import cn.com.yisquare.ibms.view.cehualistview.SwipeMenuListView;

public class Gongdan_Fragment extends Fragment {
    protected ListView lv_close_fragment;
    public Intent intent;
    private View.OnClickListener onclickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xiabanbufen, null);
        intent = new Intent(getActivity(),Main2Activity.class);


        onclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int classes = 0;
                switch (v.getId()){
                    case R.id.ll_home_B_publicfix:
                        classes = 1;
                        break;
                    case R.id.ll_home_B_clientfix:
                        classes = 2;
                        break;
                    case R.id.ll_home_B_routine:
                        classes = 1;
                        break;
                }
                intent.putExtra("classes",classes);
                startActivity(intent);
            }
        };
        view.findViewById(R.id.ll_home_B_publicfix).setOnClickListener(onclickListener);
        view.findViewById(R.id.ll_home_B_clientfix).setOnClickListener(onclickListener);
        view.findViewById(R.id.ll_home_B_routine).setOnClickListener(onclickListener);



        return view;
    }


}
