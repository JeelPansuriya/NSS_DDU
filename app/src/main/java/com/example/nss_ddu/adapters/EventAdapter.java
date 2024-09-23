package com.example.nss_ddu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nss_ddu.R;
import com.example.nss_ddu.models.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events = new ArrayList<>();

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.description.setText(event.getDescription());
        holder.date.setText(event.getDate());
        holder.venue.setText(event.getVenue());
        holder.time.setText(event.getTime());
        holder.link.setText(event.getLink());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date, venue, time,link;

        EventViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.eventTitle);
            description = itemView.findViewById(R.id.eventDescription);
            date = itemView.findViewById(R.id.eventDate);
            venue = itemView.findViewById(R.id.eventVenue);
            time = itemView.findViewById(R.id.eventTime);
            link = itemView.findViewById(R.id.eventLink);
        }
    }
}
