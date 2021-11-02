package umn.ac.keadaanudara;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import umn.ac.keadaanudara.R;
import umn.ac.keadaanudara.Modelmain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherAdapter extends RecyclerView.Adapter <WeatherAdapter.ViewHolder> {
    private final ArrayList<Modelmain> modelmains;

    public WeatherAdapter(ArrayList<Modelmain> modelmains) {
        this.modelmains = modelmains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Modelmain data = modelmains.get(position);

        holder.txtTime.setText(data.getTime());
        holder.txtTemp.setText(String.format(Locale.getDefault(), "%.0f°C", data.getCurrentTemp()));

        switch (data.getIcon()) {
            case "01d":
                holder.imgWeatherRecycler.setAnimation(R.raw.clear_01d);
                break;
            case "01n":
                holder.imgWeatherRecycler.setAnimation(R.raw.clear_01n);
                break;
            case "02d":
                holder.imgWeatherRecycler.setAnimation(R.raw.partly_cloudy_02d);
                break;
            case "02n":
                holder.imgWeatherRecycler.setAnimation(R.raw.partly_cloudy_02n);
                break;
            case "03d":
            case "03n":
                holder.imgWeatherRecycler.setAnimation(R.raw.cloudy_03);
                break;
            case "04d":
                holder.imgWeatherRecycler.setAnimation(R.raw.overcast_04d);
                break;
            case "04n":
                holder.imgWeatherRecycler.setAnimation(R.raw.overcast_04n);
                break;
            case "09d":
            case "09n":
                holder.imgWeatherRecycler.setAnimation(R.raw.drizzle_09);
                break;
            case "10d":
                holder.imgWeatherRecycler.setAnimation(R.raw.overcast_10d_rain);
                break;
            case "10n":
                holder.imgWeatherRecycler.setAnimation(R.raw.overcast_10n_rain);
                break;
            case "11d":
                holder.imgWeatherRecycler.setAnimation(R.raw.thunderstorms_11d);
                break;
            case "11n":
                holder.imgWeatherRecycler.setAnimation(R.raw.thunderstorms_11n);
                break;
            case "13d":
            case "13n":
                holder.imgWeatherRecycler.setAnimation(R.raw.snow_13);
                break;
            case "50d":
            case "50n":
                holder.imgWeatherRecycler.setAnimation(R.raw.mist_50);
                break;
            default:
                holder.imgWeatherRecycler.setAnimation(R.raw.not_available);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return modelmains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTime, txtTemp;
        LottieAnimationView imgWeatherRecycler;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txtTime);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            imgWeatherRecycler = itemView.findViewById(R.id.imgWeatherRecycler);
        }
    }
}
