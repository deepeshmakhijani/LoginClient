package tk.deepesh.loginclient.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import tk.deepesh.loginclient.R;
import tk.deepesh.loginclient.RecyclerItemClickListener;

public class AdvSetActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(AdvSetActivity.this);

        if (sharedPref.getBoolean("light_theme_switch", false)) {
            setTheme(R.style.AppThemeLight);
        }

        setContentView(R.layout.activity_adv_set);
        myDataset = new ArrayList<>();
        myDataset.add("BPGC-HOSTEL");
        myDataset.add("BPGC-WIFI");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyler_view_adv_set);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new AdvSetAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AdvSetDialog alert = new AdvSetDialog();
                alert.showDialog(AdvSetActivity.this, "SAVE");
                alert.setClickListener(new AdvSetDialog.ClickListener() {
                    @Override
                    public void onPosButtonClick() {

                    }
                });
            }
        });
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(AdvSetActivity.this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(AdvSetActivity.this, IndivSsidActivity.class);
                        intent.putExtra("position_adv_set", position);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

}
