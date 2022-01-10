package umn.ac.keadaanudara.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.airbnb.lottie.LottieAnimationView;
import umn.ac.keadaanudara.Model.ReminderModel;
import umn.ac.keadaanudara.R;

public class ReminderAdapter extends RecyclerView.Adapter <ReminderAdapter.ViewHolder>{
    private final ArrayList<ReminderModel> reminderModels;

    public ReminderAdapter(ArrayList<ReminderModel> reminderModels) {
        this.reminderModels = reminderModels;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ViewHolder holder, int position) {
        ReminderModel model = reminderModels.get(position);

        holder.conditionReminder.setText(model.getDescription());

        switch (model.getIcon()) {
            case "01d":
                holder.iconReminder.setAnimation(R.raw.clear_01d);
                break;
            case "01n":
                holder.iconReminder.setAnimation(R.raw.clear_01n);
                break;
            case "02d":
                holder.iconReminder.setAnimation(R.raw.partly_cloudy_02d);
                break;
            case "02n":
                holder.iconReminder.setAnimation(R.raw.partly_cloudy_02n);
                break;
            case "03d":
            case "03n":
                holder.iconReminder.setAnimation(R.raw.cloudy_03);
                break;
            case "04d":
                holder.iconReminder.setAnimation(R.raw.overcast_04d);
                break;
            case "04n":
                holder.iconReminder.setAnimation(R.raw.overcast_04n);
                break;
            case "09d":
            case "09n":
                holder.iconReminder.setAnimation(R.raw.drizzle_09);
                break;
            case "10d":
                holder.iconReminder.setAnimation(R.raw.overcast_10d_rain);
                break;
            case "10n":
                holder.iconReminder.setAnimation(R.raw.overcast_10n_rain);
                break;
            case "11d":
                holder.iconReminder.setAnimation(R.raw.thunderstorms_11d);
                break;
            case "11n":
                holder.iconReminder.setAnimation(R.raw.thunderstorms_11n);
                break;
            case "13d":
            case "13n":
                holder.iconReminder.setAnimation(R.raw.snow_13);
                break;
            case "50d":
            case "50n":
                holder.iconReminder.setAnimation(R.raw.mist_50);
                break;
            default:
                holder.iconReminder.setAnimation(R.raw.not_available);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return reminderModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        LottieAnimationView iconReminder;
        TextView conditionReminder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconReminder = itemView.findViewById(R.id.iconReminder);
            conditionReminder = itemView.findViewById(R.id.conditionReminder);
        }
    }
}
