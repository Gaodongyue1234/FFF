package com.example.gaodongyue.xsx_zy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaodongyue.xsx_zy.R;
import com.example.gaodongyue.xsx_zy.bean.Data;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by gaodongyue on 2018/1/17.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<Data.DataBean.ObjectsBean> mList;
    private Context mContext;
    private final static int ITEM_ONE = 1;
    private final static int ITEM_TWO = 2;

    public MyAdapter(List<Data.DataBean.ObjectsBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view;
        RecyclerView.ViewHolder holder;
        if (viewType == ITEM_ONE) {
            view = inflater.inflate(R.layout.recy_item_one, parent, false);
            holder = new HolderOne(view);
        } else {
            view = inflater.inflate(R.layout.recy_item_two, parent, false);
            holder = new HolderTwo(view);
        }
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Data.DataBean.ObjectsBean objectsBean = mList.get(position);
        if (holder instanceof HolderOne) {
            ((HolderOne) holder).mTextOne.setText(objectsBean.getGmall_product().getTitle());
            Picasso.with(mContext).load(objectsBean.getGmall_product().getPic_url()).into(((HolderOne) holder).mImageOne);
            ((HolderOne) holder).itemView.setTag(position);
        } else {
            ((HolderTwo) holder).mTextTwo.setText(objectsBean.getGmall_product().getTitle());
            Picasso.with(mContext).load(objectsBean.getGmall_product().getPic_url()).into(((HolderTwo) holder).mImageTwo);
            ((HolderTwo) holder).itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mList.isEmpty() ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_ONE;
        } else {
            return ITEM_TWO;
        }
    }


    class HolderOne extends RecyclerView.ViewHolder {
        private TextView mTextOne;
        private ImageView mImageOne;

        public HolderOne(View itemView) {
            super(itemView);
            mTextOne = (TextView) itemView.findViewById(R.id.Recy_One_Text);
            mImageOne = (ImageView) itemView.findViewById(R.id.Recy_One_Image);
        }
    }

    class HolderTwo extends RecyclerView.ViewHolder {
        private TextView mTextTwo;
        private ImageView mImageTwo;

        public HolderTwo(View itemView) {
            super(itemView);
            mTextTwo = (TextView) itemView.findViewById(R.id.Recy_Two_Text);
            mImageTwo = (ImageView) itemView.findViewById(R.id.Recy_Two_Image);
        }
    }

    public interface OnItemClick {
        void setOnItem(View v, int position);
    }

    private OnItemClick onitem;

    @Override
    public void onClick(View v) {
        if (onitem != null) {
            onitem.setOnItem(v, (int) v.getTag());
        }
    }

    public void setOnItemListener(OnItemClick item) {
        this.onitem = item;
    }
}