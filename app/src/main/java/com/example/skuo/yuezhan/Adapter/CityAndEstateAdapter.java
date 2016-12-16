package com.example.skuo.yuezhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skuo.yuezhan.Entity.Register.CityAndEstate;
import com.example.skuo.yuezhan.R;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class CityAndEstateAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private Context mContext;

    private LayoutInflater inflater;

    private List<CityAndEstate.EstateInfosBean> mCELists;

    public CityAndEstateAdapter(Context context, List<CityAndEstate.EstateInfosBean> mCELists) {
        mContext = context;

        inflater = LayoutInflater.from(context);
        this.mCELists = mCELists;
    }

    @Override
    public int getCount() {
        return mCELists.size();
    }

    @Override
    public Object getItem(int position) {
        return mCELists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cityandestate_listview, parent, false);

            holder.tv_estate_name = (TextView) convertView.findViewById(R.id.tv_estate_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CityAndEstate.EstateInfosBean estateInfo = this.mCELists.get(position);

        if (estateInfo != null) {
            holder.tv_estate_name.setText(estateInfo.getEstateName());
        }

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.item_city_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.CityName);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set proj_plans_header text as first char in name
        String headerText = this.mCELists.get(position).getCityName();
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return this.mCELists.get(position).getCityID();
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView tv_estate_name;
    }
}