package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.Subject;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private List<Subject> subjects;
    private Context context;
    private OnSubjectClickListener onSubjectClickListener;
    private int selectedItem = -1;

    public interface OnSubjectClickListener{
        void onSubjectClick(int position, String type, Subject subject);
    }

    public SubjectAdapter(List<Subject> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
        if (context instanceof OnSubjectClickListener){
            onSubjectClickListener = (OnSubjectClickListener) context;
        }
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subjects_list, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        if (position == selectedItem){
            holder.bindData(subjects.get(position), true);
        } else {
            holder.bindData(subjects.get(position), false);
        }
    }

    @Override
    public int getItemCount() {
        return subjects != null ? subjects.size() : 0;
    }

    public void setSelectedPosition(int position){
        selectedItem = position;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.subjectImage)
        ImageView subjectImage;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Subject subject, Boolean isSelected) {
            Picasso.get().load(subject.getImageUrl()).into(subjectImage);
            subjectImage.setOnClickListener(v -> {
                if (onSubjectClickListener != null){
                    onSubjectClickListener.onSubjectClick(getLayoutPosition(), subject.getType(), subject);
                }
            });
            if (isSelected){
                subjectImage.setBackgroundColor(ContextCompat.getColor(context, R.color.pickItem));
            } else {
                subjectImage.setBackgroundColor(ContextCompat.getColor(context, R.color.bgPrimary));
            }
        }
    }
}
