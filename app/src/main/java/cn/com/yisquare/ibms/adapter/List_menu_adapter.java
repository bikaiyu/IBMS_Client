package cn.com.yisquare.ibms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.yisquare.ibms.R;

/**
 * Created by bikai on 2016/1/8.
 */
public class List_menu_adapter extends BaseAdapter {
    ArrayList<HashMap<String,String>> list ;
    Context context;
    public List_menu_adapter(Context context, ArrayList<HashMap<String, String>> list){
        this.context = context;
        this.list = list;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.slmv_item, null, true);
            mHolder.tv_slmv_item_title = (TextView) convertView.findViewById(R.id.tv_help_item_title);
            mHolder.tv_slmv_item_msg = (TextView) convertView.findViewById(R.id.tv_item_msg_type_lable);
            mHolder.tv_slmv_item_time = (TextView) convertView.findViewById(R.id.tv_item_msg_from_lable);
            mHolder.tv_slmv_item_time_right = (TextView) convertView.findViewById(R.id.tv_slmv_item_time_right);
            mHolder.tv_slmv_item_unit_right = (TextView) convertView.findViewById(R.id.tv_slmv_item_unit_right);
            mHolder.iv_slmv_item_left = (ImageView) convertView.findViewById(R.id.iv_msg_item_userhead_icon);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        String title = list.get(position).get("title").toString();
        String msg = list.get(position).get("msg").toString();
        String time = list.get(position).get("time").toString();
        String time_right = list.get(position).get("time_right").toString();
        String time_unit = "min";
        mHolder.tv_slmv_item_title.setText(title);
        mHolder.tv_slmv_item_msg.setText(msg);
        mHolder.tv_slmv_item_time.setText(time);
        mHolder.tv_slmv_item_time_right.setText(time_right);
        mHolder.tv_slmv_item_unit_right.setText(time_unit);
//        mHolder.iv_slmv_item_left.setImageResource();
        return convertView;
    }

    class ViewHolder {
        private TextView tv_slmv_item_title;
        private TextView tv_slmv_item_msg;
        private TextView tv_slmv_item_time;
        private TextView tv_slmv_item_time_right;
        private TextView tv_slmv_item_unit_right;
        private ImageView iv_slmv_item_left;
    }
}
