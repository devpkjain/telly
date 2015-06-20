package com.devpkjain.telly.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.devpkjain.telly.R;
import com.devpkjain.telly.model.CurrentTVShow;
import com.squareup.picasso.Picasso;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

  private List<CurrentTVShow> items;
  private OnItemClickListener onItemClickListener;

  public RecyclerViewAdapter(List<CurrentTVShow> items) {
    this.items = items;
  }

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.onItemClickListener = onItemClickListener;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
    v.setOnClickListener(this);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    CurrentTVShow item = items.get(position);
    holder.text.setText(item.getName());
    holder.text.setText(item.getAir());
    holder.image.setImageBitmap(null);
    Picasso.with(holder.image.getContext()).load(item.getImg()).into(holder.image);
    holder.itemView.setTag(item);
  }

  @Override public int getItemCount() {
    return items.size();
  }

  @Override public void onClick(final View v) {

    if (onItemClickListener != null) {
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          onItemClickListener.onItemClick(v, (CurrentTVShow) v.getTag());
        }
      }, 200);
    }
  }

  public interface OnItemClickListener {

    void onItemClick(View view, CurrentTVShow CurrentTVShow);
  }

  protected static class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView text;
    public TextView title;

    public ViewHolder(View itemView) {
      super(itemView);
      image = (ImageView) itemView.findViewById(R.id.image);
      title = (TextView) itemView.findViewById(R.id.title);
      text = (TextView) itemView.findViewById(R.id.text);
    }
  }
}
