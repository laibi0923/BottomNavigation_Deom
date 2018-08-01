package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

import leaningcode.leo.com.bottomnavigation_deom.R;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
	private List<String> mData;
	private ViewHolder mViewHolder;
	public RecycleViewItemClickListener mRecycleViewItemClickListener;

	public RecycleView_Adapter(List<String> mList){
		mData = mList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		// TODO: Implement this method
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.editor_dialog_recycleview_item, parent, false);
		mViewHolder = new ViewHolder(v);
		return mViewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
	{
		// TODO: Implement this method
		mViewHolder.position = position;
		mViewHolder.mTextView.setText(mData.get(position));
	}

	//Set method of OnItemClickListener object
	public void setOnItemClickListener(RecycleViewItemClickListener mRecycleViewItemClickListener){
		this.mRecycleViewItemClickListener = mRecycleViewItemClickListener;
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return mData.size();
	}


	// View Holder =================================================================================================================
	public class ViewHolder extends RecyclerView.ViewHolder{

		public int position = 0;
		public TextView mTextView;
		public LinearLayout mLinearLayout;

		public ViewHolder(View v){
			super(v);

			mTextView = v.findViewById(R.id.recycleitem_editor_TextView);
			mLinearLayout = v.findViewById(R.id.recycleitem_editor_LinearLayout);

			// ===============================
			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mRecycleViewItemClickListener.onItemClick(view, position);
				}
			});
			// ===============================
		}
	}
	
}
