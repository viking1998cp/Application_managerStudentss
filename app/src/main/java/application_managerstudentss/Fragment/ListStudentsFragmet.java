package application_managerstudentss.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import application_managerstudentss.Activity.DetailStudentsActivity;
import application_managerstudentss.Adapter.AdapterRecyclerViewStudents;
import application_managerstudentss.Activity.MainActivity;
import lac.hong.application_managerstudentss.R;
import lac.hong.application_managerstudentss.databinding.ListStudentsFragmetsBinding;

public class ListStudentsFragmet extends Fragment {

    ListStudentsFragmetsBinding binding;
    AdapterRecyclerViewStudents adapter;

    public ListStudentsFragmet() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_students_fragmets, container, false);
        binding.rvListStudents.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new AdapterRecyclerViewStudents(MainActivity.listStudents);
        binding.rvListStudents.setAdapter(adapter);

        adapter.setOnItemClickedListener(new AdapterRecyclerViewStudents.OnItemClickedListener() {
            @Override
            public void onItemClick(int postion, View v) {
                Intent intent = new Intent(getActivity(), DetailStudentsActivity.class);
                intent.putExtra("id", postion);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }


}
