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
public class ManageEmployeeAdapter extends BaseAdapter {
    private final Context context;
    private OnItemClickListener listener;
    private List<ChangeListResp.ResultBean> datas;

    public interface OnItemClickListener {
        void onItemClick(ChangeListResp.ResultBean itemBean);

        void onHistoryClick(ChangeListResp.ResultBean itemBean);

        void onEditClick(ChangeListResp.ResultBean itemBean);

        void onChangeClick(ChangeListResp.ResultBean itemBean);
    }

    /**
     * 设置适配器数据
     */
    public void setDatas(List<ChangeListResp.ResultBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public ManageEmployeeAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
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
            holder.tvChangeTime = convertView.findViewById(R.id.tv_change_time);
            holder.tvAddress = convertView.findViewById(R.id.tv_address);
            holder.tvHistory = convertView.findViewById(R.id.tv_history);
            holder.tvEdit = convertView.findViewById(R.id.tv_edit);
            holder.tvChange = convertView.findViewById(R.id.tv_change);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChangeListResp.ResultBean itemBean = datas.get(position);
        convertView.setOnClickListener(v -> listener.onItemClick(itemBean));
        holder.tvEdit.setOnClickListener(v -> listener.onEditClick(itemBean));
        holder.tvChange.setOnClickListener(v -> listener.onChangeClick(itemBean));
        holder.tvHistory.setOnClickListener(v -> listener.onHistoryClick(itemBean));
        setItemData(holder, itemBean);
        return convertView;
    }

    private void setItemData(ManageEmployeeAdapter.ViewHolder holder,
                             ChangeListResp.ResultBean resultBean) {
        holder.tvName.setText(resultBean.getName());
        holder.tvChangeTime.setText(resultBean.getEmploymentChangeTime());
        holder.tvJob.setText(resultBean.getEmploymentPosition() + " | " +
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
        public TextView tvChangeTime;
        public TextView tvAddress;
        public TextView tvHistory;
        public TextView tvEdit;
        public TextView tvChange;
    }
}