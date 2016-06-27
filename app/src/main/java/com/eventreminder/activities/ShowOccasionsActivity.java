package com.eventreminder.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eventreminder.R;
import com.eventreminder.adapters.MyAdapter;
import com.eventreminder.database.DatabaseHandler;
import com.eventreminder.dtos.EventDto;
import com.eventreminder.utils.AppUtils;

import java.util.List;

/**
 * Created by User on 10/31/2015.
 */
public class ShowOccasionsActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListview;
    private ImageView mIvBackButton;
    private TextView mTvHeader;
    private DatabaseHandler db;

   MyAdapter mAdapter;
    List<EventDto> eventList;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_occasions);
        mListview = (ListView) findViewById(R.id.lv_occasion);
        mTvHeader = (TextView) findViewById(R.id.txt_header_tittle);
        mTvHeader.setText("Your Saved Ocassions");
        mIvBackButton = (ImageView) findViewById(R.id.img_header_back);
        mIvBackButton.setVisibility(View.VISIBLE);
        mIvBackButton.setOnClickListener(this);
        db = new DatabaseHandler(this);
        new remoteDataTask().execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_header_back:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    private class remoteDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(ShowOccasionsActivity.this);

            mProgressDialog.setTitle("DataBase");
            // Setting progressdialog message
            mProgressDialog.setMessage("Loading Please Wait...");
            mProgressDialog.setIndeterminate(false);
            // Showing progressdialog
            mProgressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            eventList = db.getAllEvent();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mAdapter = new MyAdapter(ShowOccasionsActivity.this, eventList);

            mListview.setAdapter(mAdapter);
            mProgressDialog.dismiss();
        }

    }
}
