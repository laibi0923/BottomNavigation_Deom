package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.widget.*;
import android.view.*;
import java.util.*;
import android.widget.*;

import leaningcode.leo.com.bottomnavigation_deom.R;

public class Dialog_For_RecycleView extends DialogFragment
{
	private TextView TextInput_Title;
	private ImageView TextInput_BackBtn;
	private RecyclerView mRecyclerView;
	private RecycleView_Adapter mAdapter;

	private ArrayList<String> mList;

	public static final String RESPONSE = "response";
	private String Title, Temp_Text, FRAGMENT_TAG;

	
	@SuppressLint("ValidFragment")
	public Dialog_For_RecycleView(String Title, String Temp_Text , String FRAGMENT_TAG){
		this.Title = Title;
		this.Temp_Text = Temp_Text;
		this.FRAGMENT_TAG = FRAGMENT_TAG;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogStyle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		View v = inflater.inflate(R.layout.editor_dialog_recyclerview, container, false);
		init_findview(v);
		init_RecycleView(v);
		return v;
	}
	
	private void init_findview(View v){
		
		TextInput_Title = v.findViewById(R.id.TextInput_Title);
		TextInput_Title.setText(Title);
		
		TextInput_BackBtn = v.findViewById(R.id.TextInput_BackBtn);
		TextInput_BackBtn.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					dismiss();
				}
			});
	}
	
	private void init_RecycleView(View v){
		
		mRecyclerView = v.findViewById(R.id.editor_recyclerView);

		switch_List_Data();
		
		mAdapter = new RecycleView_Adapter(mList);
		mAdapter.setOnItemClickListener(new RecycleViewItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				
				Intent mIntent = new Intent();
				mIntent.putExtra(RESPONSE, mList.get(position).toString());
				getTargetFragment().onActivityResult(Fragement_Editor.DIALOG_REQUEST_CODE, Activity.RESULT_OK, mIntent);
				dismiss();
			}

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

		LinearLayoutManager mLayout = new LinearLayoutManager(this.getActivity());
		mLayout.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(mLayout);
		// 設置下劃線 android 5.0
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), DividerItemDecoration.VERTICAL));
		mRecyclerView.setAdapter(mAdapter);
		
	}

	public void switch_List_Data(){

		if (FRAGMENT_TAG == "Account_Layout"){
			
			mList = new ArrayList<String>();
			mList.add("現金");
			mList.add("信用卡");

		}else if (FRAGMENT_TAG == "PayMethod_Layout"){
			
			mList = new ArrayList<String>();
			mList.add("一次性");
			mList.add("分期付款");
			
		}else if(FRAGMENT_TAG == "DollarSide_Layout"){
			
			mList = new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.dollarside)));
			
		}

	}
	
}
