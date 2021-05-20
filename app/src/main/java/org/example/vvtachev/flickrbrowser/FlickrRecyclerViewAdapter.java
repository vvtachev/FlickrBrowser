package org.example.vvtachev.flickrbrowser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder> {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> photosList;
    private Context context;

    public FlickrRecyclerViewAdapter(List<Photo> photosList, Context context) {
        this.photosList = photosList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Called by the layout manager when it needs a new View
        Log.d(TAG, "onCreateViewHolder: new View requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new FlickrImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrImageViewHolder holder, int position) {
        // Called by the layout manager when it wants new data in an existing row
        Photo photoItem = photosList.get(position);
        Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + " --> " + position);
        Picasso.with(context).load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail);

        holder.title.setText(photoItem.getTitle());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        return (photosList != null) && (photosList.size() != 0) ? photosList.size() : 0;
    }

    void loadNewData(List<Photo> newPhotos) {
        photosList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return (photosList != null) && (photosList.size() != 0) ? photosList.get(position) : null;
    }

    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "FlickrImageViewHolder: starts");
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
            this.title = itemView.findViewById(R.id.imageTitle);
        }
    }
}
