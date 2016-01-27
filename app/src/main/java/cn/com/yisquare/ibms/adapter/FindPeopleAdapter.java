package cn.com.yisquare.ibms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.yisquare.ibms.R;
import cn.com.yisquare.ibms.bean.Worker;

/**
 * Created by bikai on 2016/1/12.
 */
public class FindPeopleAdapter extends BaseAdapter {
    ArrayList<Worker> list ;
    Context context;
    public FindPeopleAdapter(Context context, ArrayList<Worker> list){
        this.context = context;
        this.list = list;
    }

    public ArrayList<Worker> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
//        return list.get(position).getNumber();
        return position;
    }

    public void setList(ArrayList<Worker> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.lv_item_people, null, true);
            mHolder.iv_people_item_userhead_icon = ((ImageView) convertView.findViewById(R.id.iv_fmlv_item_left));
            mHolder.tv_people_username = ((TextView) convertView.findViewById(R.id.tv_people_username));
            mHolder.tv_people_project = ((TextView) convertView.findViewById(R.id.tv_people_project));
            mHolder.tv_people_department = ((TextView) convertView.findViewById(R.id.tv_people_department));
            mHolder.tv_people_group = ((TextView) convertView.findViewById(R.id.tv_people_group));
            mHolder.tv_people_post = ((TextView) convertView.findViewById(R.id.tv_people_post));
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.tv_people_username.setText(list.get(position).getName());//姓名
        mHolder.tv_people_project.setText(list.get(position).getProject());//项目名称
        mHolder.tv_people_department.setText(list.get(position).getDepartment());//所在部门
        mHolder.tv_people_group.setText(list.get(position).getGroup());//所在小组
        mHolder.tv_people_post.setText(list.get(position).getPost());//职位
        return convertView;
    }

    class ViewHolder {
        private TextView tv_people_username;
        private TextView tv_people_project;
        private TextView tv_people_department;
        private TextView tv_people_group;
        private TextView tv_people_post;
        private ImageView iv_people_item_userhead_icon;
    }
}
