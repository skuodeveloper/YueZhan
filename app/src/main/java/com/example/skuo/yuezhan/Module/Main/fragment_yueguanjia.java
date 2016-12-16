package com.example.skuo.yuezhan.Module.Main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.skuo.yuezhan.Base.BaseFragment;
import com.example.skuo.yuezhan.R;

import butterknife.BindView;

/**
 * Created by apple on 2016/12/12.
 */

public class fragment_yueguanjia extends BaseFragment {

    @BindView(R.id.swipemenulistview)
    SwipeMenuListView swipeMenuListView;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_yueguanjia;
    }
    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem delete = new SwipeMenuItem(getActivity());
                delete.setBackground(new ColorDrawable(Color.RED));
                delete.setWidth(dp2px(90));
                delete.setTitle("打开");
                delete.setTitleSize(20);
                delete.setTitleColor(Color.WHITE);
                menu.addMenuItem(delete);

                SwipeMenuItem isRead = new SwipeMenuItem(getActivity());
                isRead.setBackground(new ColorDrawable(Color.GRAY));
                isRead.setWidth(dp2px(90));
                isRead.setTitle("已读");
                isRead.setTitleSize(20);
                isRead.setTitleColor(Color.BLACK);
                menu.addMenuItem(isRead);
            }
        };

        swipeMenuListView.setMenuCreator(creator);

        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        Toast.makeText(context, "删除:"+position,Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(context, "已读:"+position,Toast.LENGTH_SHORT).show();
                        break;
                }

                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                return false;
            }
        });

        swipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int pos) {
                Log.d("位置:" + pos, "开始侧滑...");
            }

            @Override
            public void onSwipeEnd(int pos) {
                Log.d("位置:" + pos, "侧滑结束.");
            }
        });

        String[] data = new String[30];
        for (int i = 0; i < data.length; i++) {
            data[i] = "测试数据:" + i;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, data);
        swipeMenuListView.setAdapter(adapter);
    }



    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
