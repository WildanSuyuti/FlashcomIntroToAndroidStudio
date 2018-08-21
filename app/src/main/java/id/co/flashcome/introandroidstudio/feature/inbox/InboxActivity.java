package id.co.flashcome.introandroidstudio.feature.inbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.Inbox;
import id.co.flashcome.introandroidstudio.utility.RecyclerTouchListener;

/**
 * Created by kakaroto on 16/08/18.
 */
public class InboxActivity extends AppCompatActivity {

    private final String TAG = InboxActivity.class.getSimpleName();
    private RecyclerView rvInbox;
    private InboxAdapter adapter;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        rvInbox = findViewById(R.id.rv_inbox);
        swipe = findViewById(R.id.swipe_refresh);

        swipe.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE);
        swipe.setRefreshing(true);

        rvInbox.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvInbox.addItemDecoration(new SpacesItemDecoration(new Utils(this).getPixelByDP(8)));

        final List<Inbox> inboxes = new ArrayList<>();
        inboxes.add(new Inbox("Budi", "hai ... ! ", "19:08"));
        inboxes.add(new Inbox("Rudi", "ho ... ! ", "11:08"));
        inboxes.add(new Inbox("Aldi", "hei ... ! ", "12:08"));
        inboxes.add(new Inbox("Andi", "hii ... ! ", "10:08"));
        inboxes.add(new Inbox("Sandi", "hasssi ... ! ", "17:08"));
        inboxes.add(new Inbox("Ferdi", "haaai ... ! ", "16:08"));

        adapter = new InboxAdapter();
        adapter.addAll(inboxes);
//        adapter.add(new Inbox("aasas", "sasa", "sas"));
        if (adapter.getItemCount() > 0) {
            swipe.setRefreshing(false);
        }
        rvInbox.setAdapter(adapter);
        rvInbox.addOnItemTouchListener(new RecyclerTouchListener(this, rvInbox,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(InboxActivity.this, AddDataActivity.class);
                        intent.putExtra("inbox-position", position);
                        intent.putExtra("is-edit", true);
                        intent.putExtra("detail-inbox", inboxes.get(position));
                        startActivityForResult(intent, 12);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        adapter.remove(position);
                    }
                }));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.setRefreshing(false);
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final AlertDialog alertDialog = new AlertDialog.Builder(InboxActivity.this).create();
                alertDialog.setTitle("Item Akan Terhapus");
                alertDialog.setMessage("Apakah anda yakin untuk menghapus !");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(position);
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });
                alertDialog.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvInbox);

    }

    public void openAddData(View view) {
        Intent intent = new Intent(InboxActivity.this, AddDataActivity.class);
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            if (resultCode == 1) {
                Inbox inbox = data.getParcelableExtra("inbox");
                adapter.add(inbox);
                swipe.setRefreshing(false);
            } else if (resultCode == 2) {
                Inbox inbox = data.getParcelableExtra("inbox");
                int inboxPosition = data.getIntExtra("inbox-position", 0);
                Log.d(TAG, "onActivityResult: inbox position : " + inboxPosition);
                adapter.remove(inboxPosition);
                adapter.add(inboxPosition, inbox);
            }
        }
    }
}
