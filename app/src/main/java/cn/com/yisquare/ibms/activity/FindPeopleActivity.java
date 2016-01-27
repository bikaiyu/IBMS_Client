package cn.com.yisquare.ibms.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import cn.com.yisquare.ibms.MyApplication;
import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.adapter.FindPeopleAdapter;
import cn.com.yisquare.ibms.bean.Worker;
import cn.com.yisquare.ibms.utils.DBHelper;

public class FindPeopleActivity extends AppCompatActivity {

    protected Spinner sp_project;
    protected Spinner sp_department;
    protected Spinner sp_group;
    protected DBHelper dbHelper;
    protected ArrayList<String> list_project,list_department,list_group;
    protected ArrayList<Worker> all_user;
    protected ListView listview;
    protected FindPeopleAdapter listadapter;
    public String str_project;
    public String str_department;
    public String str_group;
    boolean first1=true;
    boolean first2=true;
    boolean first3=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpeople);
        listview = ((ListView) findViewById(R.id.lv_findpeople));
        //第一级 所在项目
        sp_project = ((Spinner) findViewById(R.id.sp_project));
        list_project = new ArrayList<>();
        list_project.add("所在项目");
        sp_project.setPrompt("所在项目");
        //第二级 所在部门
        sp_department = ((Spinner) findViewById(R.id.sp_department));
        list_department = new ArrayList<>();
        list_department.add("所在部门");
        sp_department.setPrompt("所在部门");
        //第三级 所在工作组
        sp_group = ((Spinner) findViewById(R.id.sp_group));
        list_group = new ArrayList<>();
        list_group.add("所在工作组");
        sp_group.setPrompt("所在工作组");
        // 项目 定义数组适配器。
        ArrayAdapter<String> adapter_project = new ArrayAdapter<String>(
                this,  //上下文对象
                android.R.layout.simple_spinner_dropdown_item,//每项的布局的资源id
                list_project //要加载的数据源数组或集合.
        );
        final ArrayAdapter<String> adapter_department = new ArrayAdapter<String>(
                this,  //上下文对象
                android.R.layout.simple_spinner_dropdown_item,//每项的布局的资源id
                list_department
//                this.getResources().getStringArray(R.array.department)//要加载的数据源数组或集合.
        );
        ArrayAdapter<String> adapter_group = new ArrayAdapter<String>(
                this,  //上下文对象
                android.R.layout.simple_spinner_dropdown_item,//每项的布局的资源id
                list_group
//                this.getResources().getStringArray(R.array.group)//要加载的数据源数组或集合.
        );
        dbHelper = MyApplication.getInstance().getDbHelper();
        // 1. 先获取 所有用户的project ,填充到第一个 spinner中
        list_project = dbHelper.getProject();
        adapter_project.clear();
        adapter_project.addAll(list_project);
        // 2.获取所有的worker填充到 list中
        all_user = dbHelper.getAll_Worker(null,null,null);
        listadapter = new FindPeopleAdapter(this,all_user);
        listview.setAdapter(listadapter);
        sp_project.setAdapter(adapter_project);
//        sp_project.setSelection(0,true);
        sp_department.setAdapter(adapter_department);
//        sp_department.setSelection(0,true);
        sp_group.setAdapter(adapter_group);
//        sp_group.setSelection(0,true);

        // 3.绑定spinner的监听器
        // 项目
        sp_project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first1){
                    first1=false;
                    return;
                }

                str_project = list_project.get(position);
                Toast.makeText(FindPeopleActivity.this, str_project, Toast.LENGTH_SHORT).show();
                //设置 部门 spinner的数据源
                DBHelper dbHelper = MyApplication.getInstance().getDbHelper();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cur = db.rawQuery("select * from tb_Worker where project = '" + list_project.get(position) + "' group by department",null);
                int count = cur.getCount();
                if(cur!=null&& count >0){
                    cur.moveToFirst();
                    list_department.clear();
                    for (int i = 0; i < count; i++) {
                        list_department.add(cur.getString(cur.getColumnIndex("department")));
                        cur.moveToNext();
                    }
                }
                cur.close();
                db.close();
                ArrayAdapter<String> adapter = new ArrayAdapter<>(FindPeopleActivity.this, android.R.layout.simple_spinner_dropdown_item, list_department);
                sp_department.setAdapter(adapter);
                //设置 list数据源
                all_user = FindPeopleActivity.this.dbHelper.getAll_Worker(str_project,null,null);
                listadapter.setList(all_user);
                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //部门 department
        sp_department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first2){
                    first2=false;
                    return;
                }
                str_department = list_department.get(position);
                Toast.makeText(FindPeopleActivity.this, str_department, Toast.LENGTH_SHORT).show();
                DBHelper dbHelper = MyApplication.getInstance().getDbHelper();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cur = db.rawQuery("select * from tb_Worker where project = '" + str_project +
                        "' and department = '"+list_department.get(position)+"' group by department",null);
                int count = cur.getCount();
                if(cur!=null&& count >0){
                    cur.moveToFirst();
                    list_group.clear();
                    for (int i = 0; i < count; i++) {
                        list_group.add(cur.getString(cur.getColumnIndex("mygroup")));
                        cur.moveToNext();
                    }
                }
                cur.close();
                db.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<>(FindPeopleActivity.this, android.R.layout.simple_spinner_dropdown_item, list_group);
                sp_group.setAdapter(adapter);

                all_user = dbHelper.getAll_Worker(str_project,str_department,null);
                listadapter.setList(all_user);
                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //工作组 group
        sp_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first3){
                    first3=false;
                    return;
                }
                str_group = list_group.get(position);
                Toast.makeText(FindPeopleActivity.this, str_group, Toast.LENGTH_SHORT).show();
                all_user = dbHelper.getAll_Worker(str_project,str_department,str_group);
                listadapter.setList(all_user);
                listadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FindPeopleActivity.this, "已经向" + all_user.get(position).getName() + "发起了求助,请等待回应!", Toast.LENGTH_LONG).show();
                FindPeopleActivity.this.finish();
            }
        });
    }
    public void goback(View view){
        finish();
    }
}
