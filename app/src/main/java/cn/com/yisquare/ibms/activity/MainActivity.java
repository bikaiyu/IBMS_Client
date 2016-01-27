package cn.com.yisquare.ibms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.yisquare.ibms.Fragment.Gongdan_Fragment;
import cn.com.yisquare.ibms.Fragment.Icenter_Fragment;
import cn.com.yisquare.ibms.Fragment.Msg_Fragment;
import cn.com.yisquare.ibms.Fragment.Schedule_Fragment;
import cn.com.yisquare.ibms.Fragment.Quanzi_Fragment;
import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.bean.User;
import cn.com.yisquare.ibms.bean.Worker;
import cn.com.yisquare.ibms.utils.DBHelper;


public class MainActivity extends FragmentActivity {
    private List<Fragment> fragmentList;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment lastFragment;
    public View ll_tool_1;
    public View ll_tool_2;
    public View ll_tool_3;
    public View ll_tool_4;
    public ArrayList<View> list;
    public ArrayList<ImageView> list_img;
    public ArrayList<TextView> list_tv;
    public ArrayList<Integer> list_background_a;
    public ArrayList<Integer> list_background_b;
    public boolean firstrun = true;
    protected DBHelper dbHelper;
    protected Worker worker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = new ArrayList<>();
        fragmentList = new ArrayList<>();
        list_img = new ArrayList<>();
        list_tv = new ArrayList<>();
        list_background_a =new ArrayList<>();
        list_background_b = new ArrayList<>();
        manager = getSupportFragmentManager();//获取布局管理器
        list_background_a.add(R.mipmap.msg_a);
        list_background_a.add(R.mipmap.my_order_a);
        list_background_a.add(R.mipmap.schedule_a);
        list_background_a.add(R.mipmap.icenter_a);

        list_background_b.add(R.mipmap.msg_b);
        list_background_b.add(R.mipmap.my_order_b);
        list_background_b.add(R.mipmap.schedule_b);
        list_background_b.add(R.mipmap.icenter_b);

        initview();
        initpage();
        initData();

    }

    private void initData() {
        dbHelper = MyApplication.getInstance().getDbHelper();
        worker = dbHelper.getWorker("1");
        User user = new User();
        user.setId(1);
        user.setUsernumber(worker.getId());
        user.setProject(worker.getProject());//所在项目
        user.setDepartment(worker.getDepartment());//所在部门
        user.setGroup(worker.getGroup());//所在工作组
        user.setPost(worker.getPost());//职位
        user.setStatus(0);
        user.setName(worker.getName());
        MyApplication.getInstance().setUser(user);
        MyApplication.getInstance().setWorker(worker);
    }

    private void initview() {
        ll_tool_1 = findViewById(R.id.ll_tool_1);
        ll_tool_2 = findViewById(R.id.ll_tool_2);
        ll_tool_3 = findViewById(R.id.ll_tool_3);
        ll_tool_4 = findViewById(R.id.ll_tool_4);
        list.add(ll_tool_1);
        list.add(ll_tool_2);
        list.add(ll_tool_3);
        list.add(ll_tool_4);
        list_img.add((ImageView) findViewById(R.id.iv_msg));
        list_img.add((ImageView) findViewById(R.id.iv_order));
        list_img.add((ImageView) findViewById(R.id.iv_schedule));
        list_img.add((ImageView) findViewById(R.id.iv_icenter));

        list_tv.add(((TextView) findViewById(R.id.tv_msg)));
        list_tv.add(((TextView) findViewById(R.id.tv_order)));
        list_tv.add(((TextView) findViewById(R.id.tv_schedule)));
        list_tv.add(((TextView) findViewById(R.id.tv_icenter)));
        list.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChengeFragment(0);
            }
        });
        list.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChengeFragment(1);
            }
        });
        list.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChengeFragment(2);
            }
        });
        list.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doChengeFragment(3);
            }
        });



    }

    private void initpage() {
        Msg_Fragment msg_fragment = new Msg_Fragment();
        //接单Fragment
         Gongdan_Fragment gongdan_fragment = new Gongdan_Fragment();
        //排班
        Schedule_Fragment schedule_fragment = new Schedule_Fragment();
        //个人中心
        Icenter_Fragment icenter_fragment = new Icenter_Fragment();
        fragmentList.add(msg_fragment);
        fragmentList.add(gongdan_fragment);
        fragmentList.add(schedule_fragment);
        fragmentList.add(icenter_fragment);
    }

    private void doChengeFragment(int position) {
        Log.w("MainActivity 位置", "===>" + position);
//        if(position==1){
//            Intent intent = new Intent(this,Main2Activity.class);
//            startActivityForResult(intent,0);
//            return;
//        }
        list_img.get(position).performClick();
        transaction = manager.beginTransaction();//启动事务

        if(lastFragment!=null&&lastFragment == fragmentList.get(position))
            return;
        if(lastFragment!=null)
            transaction.remove(lastFragment);
        lastFragment = fragmentList.get(position);
        transaction.replace(R.id.ll_replace, lastFragment);
        transaction.commit();//提交事务
        chengeBackground(position);

    }
    public void chengeBackground(int position){
        for (int j = 0; j < 4; j++) {
            list_img.get(j).setBackgroundResource(list_background_a.get(j));
            list_tv.get(j).setTextColor(getResources().getColor(R.color.gray_text5));
        }
        list_img.get(position).setBackgroundResource(list_background_b.get(position));
        list_tv.get(position).setTextColor(getResources().getColor(R.color.color_blue_start));
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firstrun){
            ll_tool_4.performClick();
            firstrun = false;
        }
    }
    public void createOrder(View v){
        Intent intent = new Intent(this,CreateOrderActivity.class);
        startActivity(intent);
    }
    public void go_appraise(View v){
        Intent intent = new Intent(this,AppraiseActivity.class);
        startActivity(intent);
    }
    public void showDetail(View v){
        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != 1){
            doChengeFragment(resultCode);
        }
    }

    /** 消息按钮 */
    public void onClickMsg(View v){
        Intent intent  =  new Intent(this,MsgActivity.class);
        startActivity(intent);
    }
}
