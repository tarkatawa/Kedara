package umn.ac.keadaanudara.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import umn.ac.keadaanudara.R;

public class OneTimeAdapter extends RecyclerView.Adapter<OneTimeAdapter.ViewHolder>{

    Context context;
    String[] activityNameList;
    String[] activityLocationList;
    String[] activityDateList;
    String[] activityTimeList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowName;
        TextView rowLocation;
        TextView rowDate;
        TextView rowTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.leActivity);
            rowLocation = itemView.findViewById(R.id.leCity);
            rowDate = itemView.findViewById(R.id.leDate);
            rowTime = itemView.findViewById(R.id.leTime);

        }
    }

    public OneTimeAdapter(Context context, String[] activityNameList, String[] activityLocationList, String[] activityDateList, String[] activityTimeList){
        this.context = context;
        this.activityNameList = activityNameList;
        this.activityLocationList = activityLocationList;
        this.activityDateList = activityDateList;
        this.activityTimeList = activityTimeList;
    }

    @NonNull
    @Override
    public OneTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_onetime_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OneTimeAdapter.ViewHolder holder, int position) {
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
