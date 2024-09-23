package com.example.nss_ddu.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nss_ddu.R;
import com.example.nss_ddu.models.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    public EventAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the event card layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_layout, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event, listener);
    }

    @Override
    public int getItemCount() {
        return events == null ? 0 : events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateVenueTextView;

        public EventViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateVenueTextView = itemView.findViewById(R.id.dateVenueTextView);
        }

        public void bind(final Event event, final OnItemClickListener listener) {
            titleTextView.setText(event.getTitle());
            dateVenueTextView.setText(event.getDate() + ", " + event.getVenue());

            itemView.setOnClickListener(v -> listener.onItemClick(event));
        }
    }
}
