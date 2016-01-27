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
import cn.com.yisquare.ibms.bean.Msg;

import static cn.com.yisquare.ibms.utils.Tools.cenvertMilisToTime;

/**
 * Created by bikai on 2016/1/8.
 */
public class List_msg_adapter extends BaseAdapter {
    ArrayList<Msg> list ;
    Context context;
    String from[];
    String classes[];
    public List_msg_adapter(Context context, ArrayList<Msg> list){
        this.context = context;
        this.list = new ArrayList<Msg>();
        this.list.addAll(list);
        from = context.getResources().getStringArray(R.array.from_array);
        classes = context.getResources().getStringArray(R.array.classes_array);
    }

    public ArrayList<Msg> getList() {
        return list;
    }

    public void setList(ArrayList<Msg> list) {
        this.list.addAll(list);
    }
    public void resetList(ArrayList<Msg> list){
        this.list.clear();
        this.list.addAll(list);
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
            convertView = inflater.inflate(R.layout.lv_item_msg, null, true);
            mHolder.tv_item_gz = (TextView) convertView.findViewById(R.id.tv_item_gz);
            mHolder.tv_item_time = (TextView) convertView.findViewById(R.id.tv_item_time);
            mHolder.tv_item_msg_type = (TextView) convertView.findViewById(R.id.tv_item_msg_type);
            mHolder.tv_help_item_title = (TextView) convertView.findViewById(R.id.tv_help_item_title);
            mHolder.iv_msg_item_userhead_icon = (ImageView) convertView.findViewById(R.id.iv_msg_item_userhead_icon);
            mHolder.im_item_msg_from = (ImageView) convertView.findViewById(R.id.im_item_msg_from);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        String myname = list.get(position).getWorkorder().getWork_owner().getName();
        String helpername = list.get(position).getWorkorder().getWork_helper().getName();
        String msg = list.get(position).getWorkorder().getDescription();
        //x秒以前  x分钟以前 x 小时以前 x天以前
        String time = cenvertMilisToTime(list.get(position).getCreateTime());
        String type = from[list.get(position).getWorkorder().getMyfrom()];
        String from_classes = classes[list.get(position).getWorkorder().getClasses()];
        String title="";
        if(list.get(position).getType() == 0)
            title = "您向"+helpername+"发起了求助";
        else if(list.get(position).getType() == 1)
            title = myname+"向您发起求助";
        mHolder.tv_help_item_title.setText(title);
        mHolder.tv_item_gz.setText(msg);
        mHolder.tv_item_time.setText(time);
        mHolder.tv_item_msg_type.setText(from_classes);
//        mHolder.iv_msg_item_userhead_icon.setImageResource();
//        求助发起人的头像
            //下一步,要存储用户头像到本地,并自动裁剪成圆形

        //工单来源类型
        switch (list.get(position).getWorkorder().getMyfrom()){
            case 0://默认就是系统指派 小齿轮 不用改
                break;
            case 1://1为用户提交. 改成小人头
                mHolder.im_item_msg_from.setImageResource(R.mipmap.rentou);
                break;
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv_help_item_title;
        private TextView tv_item_gz;
        private TextView tv_item_time;
        private TextView tv_item_msg_type;
        private ImageView iv_msg_item_userhead_icon,im_item_msg_from;
    }

}
