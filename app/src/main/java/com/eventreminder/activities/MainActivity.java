package com.eventreminder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.eventreminder.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnAddOccasion;
    private Button mBtnShowOccasion;
    private TextView mTvTitle;
   // int[] imageArray = {R.drawable.lets_celebrate, R.drawable.celebration_two, R.drawable.celebration_three, R.drawable.celebration_four, R.drawable.celebration_five};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }


    private void initUI() {
        mBtnAddOccasion = (Button) findViewById(R.id.btn_add_occasion);
        mBtnShowOccasion = (Button) findViewById(R.id.btn_show_occasion);
        mTvTitle=(TextView)findViewById(R.id.txt_header_tittle);
        mTvTitle.setText("Let's Celebrate");
        mBtnAddOccasion.setOnClickListener(this);
        mBtnShowOccasion.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_occasion:
                startActivity(new Intent(this, AddOccasionActivity.class));
                finish();

                break;
            case R.id.btn_show_occasion:
                startActivity(new Intent(this, ShowOccasionsActivity.class));
                finish();

                break;

        }

    }

}
