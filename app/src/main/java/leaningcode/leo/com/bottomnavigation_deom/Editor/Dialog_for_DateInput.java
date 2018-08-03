package leaningcode.leo.com.bottomnavigation_deom.Editor;
import android.support.v4.app.*;
import android.os.*;
import android.view.*;
import leaningcode.leo.com.bottomnavigation_deom.*;

public class Dialog_for_DateInput extends DialogFragment
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// TODO: Implement this method
		{
			View v = inflater.inflate(R.layout.editor_dialog_date, container, false);
			return v;
		}
	}
	
	
}
