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
import cn.com.yisquare.ibms.bean.WorkOrder;

/**
 * Created by bikai on 2016/1/12.
 */
public class NotYetAdapter extends BaseAdapter {
    ArrayList<WorkOrder> list ;
    Context context;
    public NotYetAdapter(Context context, ArrayList<WorkOrder> list){
        this.context = context;
        if(list==null)
            this.list = new ArrayList<WorkOrder>();
        this.list = list;
    }

    public ArrayList<WorkOrder> getList() {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.fm_notyet_item, null, true);
            mHolder.iv_fmlv_item_left = ((ImageView) convertView.findViewById(R.id.iv_fmlv_item_left));
            mHolder.tv_fmlv_item_title = ((TextView) convertView.findViewById(R.id.tv_fmlv_item_title));
            mHolder.tv_fmlv_item_msg = ((TextView) convertView.findViewById(R.id.tv_fmlv_item_msg));
            mHolder.tv_fmlv_item_time = ((TextView) convertView.findViewById(R.id.tv_fmlv_item_time));
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.tv_fmlv_item_title.setText(list.get(position).getDescription());//工单描述
        mHolder.tv_fmlv_item_msg.setText(list.get(position).getMalfunction());//故障描述
        mHolder.tv_fmlv_item_time.setText("1小时前");
        int from = list.get(position).getMyfrom();
//        Log.w("from","====>"+from);
        if(from==2){
            mHolder.iv_fmlv_item_left.setImageResource(R.mipmap.touxiang);
        }else{
            if(from ==1)
                 mHolder.iv_fmlv_item_left.setImageResource(R.mipmap.smlv_item_system_left);
        }
        return convertView;
    }

    class ViewHolder {
        private TextView tv_fmlv_item_title;
        private TextView tv_fmlv_item_msg;
        private TextView tv_fmlv_item_time;
        private ImageView iv_fmlv_item_left;
    }
}
