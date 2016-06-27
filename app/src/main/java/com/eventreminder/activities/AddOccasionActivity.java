package com.eventreminder.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eventreminder.R;
import com.eventreminder.database.DatabaseHandler;
import com.eventreminder.utils.AppUtils;

import java.util.Calendar;

public class AddOccasionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static int mYear;
    private static int mDay;
    private static int mMonth;
    private EditText mEtSelectDate;
    private Button mBtnSave;
    private EditText mEtName;
    private EditText mEtNote;
    private Spinner mSpnOccasion;
    private ArrayAdapter<String> mAdapter;
    private String[] mOccasions;
    private String mSelectedEvent;
    private ImageView mIvBackButton;
    private TextView mTvHeader;

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_occasion);
        mOccasions = getResources().getStringArray(R.array.select_occasion);
        initUI();
        db = new DatabaseHandler(this);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mOccasions);
        mSpnOccasion.setAdapter(mAdapter);

    }

    private void initUI() {
        mSpnOccasion = (Spinner) findViewById(R.id.spn_occasion);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtName.requestFocus();
        mEtNote = (EditText) findViewById(R.id.et_note);
        mTvHeader = (TextView) findViewById(R.id.txt_header_tittle);
        mTvHeader.setText("Add Ocassion");
        mIvBackButton = (ImageView) findViewById(R.id.img_header_back);
        mIvBackButton.setVisibility(View.VISIBLE);
        mEtSelectDate = (EditText) findViewById(R.id.et_select_date);
        mBtnSave = (Button) findViewById(R.id.btn_save);
        mSpnOccasion.setOnItemSelectedListener(this);
        mEtSelectDate.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mIvBackButton.setOnClickListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSelectedEvent = parent.getItemAtPosition(position)
                .toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_select_date:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mEtSelectDate.setText(dayOfMonth + "-" + (monthOfYear + 1)
                                        + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btn_save:
                String name = mEtName.getText().toString();
                String note = mEtNote.getText().toString();
                String date = mEtSelectDate.getText().toString();

                if (name.equals("") || date.equals("") || note.equals("")) {
                    AppUtils.showToast(this, "Fill All the fields");

                } else {
                    db.addOccasion(name, note, date, mSelectedEvent);
                    mEtName.setText("");
                    mEtSelectDate.setText("");
                    mEtNote.setText("");
                    AppUtils.showToast(this, " Congrates Event Saved...");
                }

                break;
            case R.id.img_header_back:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }


    }


}
