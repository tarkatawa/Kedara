package umn.ac.keadaanudara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyFridayAdapter extends RecyclerView.Adapter<umn.ac.keadaanudara.WeeklyFridayAdapter.ViewHolder>{

    Context context;
    String[] activityNameList;
    String[] activityLocationList;
    String[] activityTimeList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        TextView rowLocation;
        TextView rowTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.lesActivity);
            rowLocation = itemView.findViewById(R.id.lesCity);
            rowTime = itemView.findViewById(R.id.lesTime);
        }
    }

    public WeeklyFridayAdapter(Context context, String[] activityNameList, String[] activityLocationList, String[] activityTimeList){
        this.context = context;
        this.activityNameList = activityNameList;
        this.activityLocationList = activityLocationList;
        this.activityTimeList = activityTimeList;
    }

    @NonNull
    @Override
    public umn.ac.keadaanudara.WeeklyFridayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_weekly_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull umn.ac.keadaanudara.WeeklyFridayAdapter.ViewHolder holder, int position) {
        holder.rowName.setText(activityNameList[position]);
        holder.rowLocation.setText(activityLocationList[position]);
        holder.rowTime.setText(activityTimeList[position]);

    }

    @Override
    public int getItemCount() {
        return activityNameList.length;
    }



}
