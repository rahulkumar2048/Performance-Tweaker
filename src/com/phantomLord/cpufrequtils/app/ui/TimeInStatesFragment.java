package com.phantomLord.cpufrequtils.app.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.phantomLord.cpufrequtils.app.R;
import com.phantomLord.cpufrequtils.app.adapters.TimeInStatesListAdapter;
import com.phantomLord.cpufrequtils.app.utils.CpuState;
import com.phantomLord.cpufrequtils.app.utils.CpuUtils;
import com.phantomLord.cpufrequtils.app.utils.MiscUtils;

public class TimeInStatesFragment extends SherlockFragment {

	View view;
	ListView listView;
	ArrayList<CpuState> states;
	TimeInStatesListAdapter timeInStateAdapter;
	TextView kernelVersion;
	TextView totalTimeInState;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.time_in_states, container, false);
		listView = (ListView) view.findViewById(R.id.time_in_state_listView);
		totalTimeInState = (TextView) view.findViewById(R.id.total_time);
		kernelVersion = (TextView) view.findViewById(R.id.kernelInfo);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		kernelVersion.setText(CpuUtils.getKernelInfo());
		timeInStateAdapter = new TimeInStatesListAdapter(view.getContext());
		listView.setAdapter(timeInStateAdapter);
		totalTimeInState.setText("Total Time :"
				+ MiscUtils.secToString(timeInStateAdapter.totaltime));
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.add("Show Pie Graph").setIcon(R.drawable.ic_menu_reset)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			Intent intent = new Intent(getSherlockActivity(),
					TimeInStatePieGraph.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}