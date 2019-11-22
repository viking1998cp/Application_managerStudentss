package application_managerstudentss.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import application_managerstudentss.Activity.MainActivity;
import application_managerstudentss.Object.Students;
import application_managerstudentss.Until;
import lac.hong.application_managerstudentss.R;
import lac.hong.application_managerstudentss.databinding.AddStudentsFragmentBinding;

public class AddStudentsFragment extends Fragment {
    AddStudentsFragmentBinding binding;
    private String name, address, dateBirth, studentsClass;
    private float pointLiterature, pointMath, pointEngLish, pointAverage;

    public AddStudentsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_students_fragment, container, false);
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add Data
                try {
                    Students students = new Students();
                    String name = binding.etName.getText().toString();
                    String address = binding.etAddress.getText().toString().trim();
                    pointLiterature = Float.parseFloat(binding.etLiterature.getText().toString());
                    pointMath = Float.parseFloat(binding.ettMath.getText().toString());
                    pointEngLish = Float.parseFloat(binding.etEngLish.getText().toString());
                    pointAverage = Until.pointAverage(pointLiterature, pointMath, pointEngLish);
                    studentsClass = binding.etClass.getText().toString().trim();
                    students.setName(name);
                    students.setAddress(address);
                    students.setBirthDate(dateBirth);
                    students.setPointLiterature(pointLiterature);
                    students.setPointMath(pointMath);
                    students.setPointAverage(pointAverage);
                    students.setPointEngLish(pointEngLish);
                    students.setStudentClass(studentsClass);
                    MainActivity.listStudents.add(students);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                }catch (Exception e){
                    Until.ShowToastLong(getString(R.string.emptyData), getContext());
                }

            }
        });

        binding.etBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return binding.getRoot();
    }


}
