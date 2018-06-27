package com.unistrong.employmentservice.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unistrong.baselibs.utils.NumberUtils;
import com.unistrong.employmentservice.R;
import com.unistrong.framwork.resp.ChangeListResp;
import com.unistrong.framwork.utils.VerifyGlide;

import java.util.List;

/**
 * 管辖人员列表适配
 */
public class ManagePersonAdapter extends BaseAdapter {
    private final Context context;
    private OnItemClickListener listener;
    private List<ChangeListResp.ResultBean> datas;

    interface OnItemClickListener {
        void onItemClick(ChangeListResp.ResultBean itemBean);
    }

    public ManagePersonAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    /**
     * 设置适配器数据
     */
    public void setDatas(List<ChangeListResp.ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_change_list_layout, null);
            holder.ivIcon = convertView.findViewById(R.id.iv_icon);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvJob = convertView.findViewById(R.id.tv_job);
            holder.tvUpdateTime = convertView.findViewById(R.id.tv_update_time);
            holder.tvAddress = convertView.findViewById(R.id.tv_address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChangeListResp.ResultBean itemBean = datas.get(position);
        convertView.setOnClickListener(v -> listener.onItemClick(itemBean));
        setItemData(holder, itemBean);
        return convertView;
    }

    private void setItemData(ManagePersonAdapter.ViewHolder holder,
                             ChangeListResp.ResultBean resultBean) {
        holder.tvName.setText(resultBean.getName());
        holder.tvUpdateTime.setText(resultBean.getEmploymentChangeTime());
        holder.tvJob.setText(resultBean.getEmploymentPosition() +
                getIncomeText(resultBean.getEmploymentMonthlyIncome()));
        holder.tvAddress.setText(resultBean.getHouseholdAddress());
        VerifyGlide.getInstance().load(resultBean.getImageUrl(), holder.ivIcon);
    }

    private String getIncomeText(String income) {
        try {
            Integer income_ = Integer.valueOf(income);
            return NumberUtils.keepPrecision(income_ * .001f, 2) + "k";
        } catch (Exception e) {
        }
        return "";
    }

    class ViewHolder {
        public ImageView ivIcon;
        public TextView tvName;
        public TextView tvJob;
        public TextView tvUpdateTime;
        public TextView tvAddress;
    }
}