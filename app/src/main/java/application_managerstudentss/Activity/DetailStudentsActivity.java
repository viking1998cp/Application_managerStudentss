package application_managerstudentss.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import application_managerstudentss.Object.Students;
import application_managerstudentss.Until;
import lac.hong.application_managerstudentss.R;
import lac.hong.application_managerstudentss.databinding.ActivityDetailStudentsBinding;

public class DetailStudentsActivity extends AppCompatActivity {
    private Students students;
    private int id;
    ActivityDetailStudentsBinding binding;
    private String name, address, dateBirth, studentsClass;
    private float pointLiterature, pointMath, pointEngLish, pointAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_students);
        id = getIntent().getIntExtra("id", 0);
        students = MainActivity.listStudents.get(id);
        ActionToolbar();
        setUpData();
        setUpClick();
        unEdit();

    }

    private void setUpData() {
        binding.etName.setText(students.getName());
        binding.etAddress.setText(students.getAddress());
        binding.etBirthdate.setText(students.getBirthDate());
        binding.etLiterature.setText(students.getPointLiterature() + "");
        binding.ettMath.setText(students.getPointMath() + "");
        binding.etEngLish.setText(students.getPointEngLish() + "");
        binding.etClass.setText(students.getStudentClass());
        binding.etAverage.setText(students.getPointAverage() + "");
        binding.lnEdit.setVisibility(View.GONE);
    }

    private void setUpClick() {
        //Changer edit point ===>  changer pointAverage
        binding.etEngLish.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //reload
                reloadAverage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.ettMath.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reloadAverage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.etLiterature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                reloadAverage();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edit data
                try {
                    String name = binding.etName.getText().toString();
                    String address = binding.etAddress.getText().toString().trim();
                    pointLiterature = Float.parseFloat(binding.etLiterature.getText().toString());
                    pointMath = Float.parseFloat(binding.ettMath.getText().toString());
                    pointEngLish = Float.parseFloat(binding.etEngLish.getText().toString());
                    pointAverage = Until.pointAverage(pointLiterature, pointMath, pointEngLish);
                    dateBirth = binding.etBirthdate.getText().toString();
                    studentsClass = binding.etClass.getText().toString().trim();
                    students.setName(name);
                    students.setAddress(address);
                    students.setBirthDate(dateBirth);
                    students.setPointLiterature(pointLiterature);
                    students.setPointMath(pointMath);
                    students.setPointAverage(pointAverage);
                    students.setPointEngLish(pointEngLish);
                    students.setStudentClass(studentsClass);
                    if (Until.checkNullObject(students)) {
                        Intent intent = new Intent(DetailStudentsActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Until.ShowToastLong(getString(R.string.emptyData), DetailStudentsActivity.this);
                    }

                } catch (Exception e) {
                    Log.d("BBB", "onClick: " + e.getMessage());
                    Until.ShowToastLong(getString(R.string.emptyData), DetailStudentsActivity.this);
                }

            }
        });

        binding.etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DetailStudentsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateBirth = dayOfMonth + "/" + monthOfYear + "/" + year;
                                binding.etBirthdate.setText(dateBirth);

                            }
                        }, 1990, 1, 1);
                datePickerDialog.show();
            }
        });

        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unEdit();

            }
        });
    }

    private void unEdit() {
        binding.etName.setEnabled(false);
        binding.etAddress.setEnabled(false);
        binding.etBirthdate.setEnabled(false);
        binding.etLiterature.setEnabled(false);
        binding.ettMath.setEnabled(false);
        binding.etEngLish.setEnabled(false);
        binding.etClass.setEnabled(false);
        binding.etAverage.setEnabled(false);
        binding.lnEdit.setVisibility(View.GONE);
    }

    private void visibility() {
        binding.etName.setEnabled(true);
        binding.etAddress.setEnabled(true);
        binding.etBirthdate.setEnabled(true);
        binding.etLiterature.setEnabled(true);
        binding.ettMath.setEnabled(true);
        binding.etEngLish.setEnabled(true);
        binding.etClass.setEnabled(true);
        binding.etAverage.setEnabled(true);
        binding.lnEdit.setVisibility(View.VISIBLE);
    }

    public void reloadAverage() {
        if (!binding.etLiterature.getText().toString().isEmpty() &&
                !binding.ettMath.getText().toString().isEmpty() &&
                !binding.etEngLish.getText().toString().isEmpty()) {
            pointLiterature = Float.parseFloat(binding.etLiterature.getText().toString());
            pointMath = Float.parseFloat(binding.ettMath.getText().toString());
            pointEngLish = Float.parseFloat(binding.etEngLish.getText().toString());
            pointAverage = Until.pointAverage(pointLiterature, pointMath, pointEngLish);
            binding.etAverage.setText(pointAverage + "");
        }

    }

    private void ActionToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_edit:
                visibility();
                break;
            case R.id.nav_delete:
                MainActivity.listStudents.remove(id);
                Intent intent = new Intent(DetailStudentsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menudetail, menu);
        return true;
    }
}
