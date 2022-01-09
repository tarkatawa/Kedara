package umn.ac.keadaanudara.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import umn.ac.keadaanudara.R;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>{

    Context context;
    int[] activityIconList;
    String[] activityConditionList;
    String[] activityNameList;
    String[] activityLocationList;
    String[] activityDateList;
    String[] activityTimeList;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView rowWeatherImage;
        TextView rowCondition;
        TextView rowName;
        TextView rowLocation;
        TextView rowDate;
        TextView rowTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowWeatherImage = itemView.findViewById(R.id.iconReminder);
            rowCondition = itemView.findViewById(R.id.conditionReminder);
            rowName = itemView.findViewById(R.id.activityReminder);
            rowLocation = itemView.findViewById(R.id.locationReminder);
            rowDate = itemView.findViewById(R.id.dateReminder);
            rowTime = itemView.findViewById(R.id.timeReminder);
        }
    }

    public ReminderAdapter(Context context, int[] activityIconList, String[] activityConditionList, String[] activityNameList, String[] activityLocationList, String[] activityDateList, String[] activityTimeList){
        this.context = context;
        this.activityIconList = activityIconList;
        this.activityConditionList = activityConditionList;
        this.activityNameList = activityNameList;
        this.activityLocationList = activityLocationList;
        this.activityDateList = activityDateList;
        this.activityTimeList = activityTimeList;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.reminder_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder holder, int position) {
        holder.rowWeatherImage.setImageResource(activityIconList[0]);
        holder.rowCondition.setText(activityConditionList[position]);
        holder.rowName.setText(activityNameList[position]);
        holder.rowLocation.setText(activityLocationList[position]);
        holder.rowDate.setText(activityDateList[position]);
        holder.rowTime.setText(activityTimeList[position]);


    }

    @Override
    public int getItemCount() {
        return activityNameList.length;
    }



}
