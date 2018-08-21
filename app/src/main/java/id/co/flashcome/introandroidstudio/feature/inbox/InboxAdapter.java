package id.co.flashcome.introandroidstudio.feature.inbox;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.Inbox;

/**
 * Created by kakaroto on 16/08/18.
 */
public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {

    private List<Inbox> dataset;

    public InboxAdapter() {
        this.dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inbox inbox = dataset.get(position);
        holder.tvPengirim.setText(inbox.getPengirim());
        holder.tvPesan.setText(inbox.getPesan());
        holder.tvJam.setText(inbox.getJam());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPengirim, tvPesan, tvJam;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPengirim = itemView.findViewById(R.id.tv_pengirim);
            tvPesan = itemView.findViewById(R.id.tv_pesan);
            tvJam = itemView.findViewById(R.id.tv_jam);
        }
    }

    public void addAll(List<Inbox> dataset) {
        this.dataset = dataset;
        notifyDataSetChanged();
    }

    public void add(Inbox inbox) {
        this.dataset.add(inbox);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        this.dataset.remove(index);
        notifyDataSetChanged();
    }

    public void add(int positon, Inbox inbox) {
        this.dataset.add(positon, inbox);
        notifyDataSetChanged();
    }

    public void clear() {
        this.dataset.clear();
        notifyDataSetChanged();
    }

    public Inbox getItem(int position) {
        return dataset.get(position);
    }
}
