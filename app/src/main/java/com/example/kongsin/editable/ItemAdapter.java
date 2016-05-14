package com.example.kongsin.editable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kongsin on 15/5/2559.
 */
public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Object> lists;
    View view;
    int viewMode = 0;
    int lastClick = 0;
    boolean onBind =false;
    private static final String TAG = "ItemAdapter";

    public ItemAdapter(Context context, ArrayList<Object> lists, int viewMode) {
        this.context = context;
        this.lists = lists;
        this.viewMode = viewMode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewMode == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
            return new ViewMode(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.list_layout_edit, parent, false);
            return new EditMode(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        onBind = true;
        if (holder instanceof ViewMode) {
            ((ViewMode) holder).textView.setText((String) lists.get(position));
        } else {
            ((EditMode) holder).textView.setText((String) lists.get(position));

            if (position == lastClick) {
                ((EditMode) holder).radioButton.setChecked(true);
            } else {
                ((EditMode) holder).radioButton.setChecked(false);
            }

            ((EditMode) holder).radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (!onBind) {
                        notifyItemChanged(lastClick);
                        lastClick = position;
                        Log.i(TAG, "onCheckedChanged: "+position);
                    }
                }
            });
        }

        onBind = false;

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewMode extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewMode(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_group);
        }
    }

    public class EditMode extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public RadioButton radioButton;

        public EditMode(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.layout_group);
            radioButton = (RadioButton) itemView.findViewById(R.id.radioButton);
        }
    }

}
