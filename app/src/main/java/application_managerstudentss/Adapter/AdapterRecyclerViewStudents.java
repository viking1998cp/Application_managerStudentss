package application_managerstudentss.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import application_managerstudentss.Object.Students;

import lac.hong.application_managerstudentss.R;
import lac.hong.application_managerstudentss.databinding.ItemStudentsBinding;

public class AdapterRecyclerViewStudents extends RecyclerView.Adapter<AdapterRecyclerViewStudents.ItemRowHolder> {
    List<Students> listStudents ;
    private Context context;
    private OnItemClickedListener onItemClickedListener;

    public AdapterRecyclerViewStudents(List<Students> students) {
        this.context = context;
        this.listStudents = students;
    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_students, parent, false);
        return new AdapterRecyclerViewStudents.ItemRowHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
        Students students = listStudents.get(position);
        holder.binding.studentsName.setText("Họ và tên: "+students.getName());
        holder.binding.studentsClass.setText("Lớp: "+students.getStudentClass());
        holder.binding.studentsBirthDate.setText("Ngày sinh: "+students.getBirthDate());
        holder.binding.studentsPoint.setText("Điểm trung bình: "+students.getPointAverage());

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemStudentsBinding binding;

        private ItemRowHolder(ItemStudentsBinding view) {
            super(view.getRoot());
            binding = view;
            view.getRoot().setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onItemClickedListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(int postion, View v);
    }


    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
