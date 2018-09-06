package id.co.flashcome.introandroidstudio.feature.inbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.main.NavigationActivity;
import id.co.flashcome.introandroidstudio.model.Inbox;
import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.RecyclerTouchListener;

/**
 * Created by kakaroto on 06/09/18.
 */
public class InboxFragment extends Fragment {

    private final String TAG = InboxActivity.class.getSimpleName();
    private RecyclerView rvInbox;
    private InboxAdapter adapter;
    private SwipeRefreshLayout swipe;
    private DatabaseHandler db;
    private List<Inbox> inboxes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((NavigationActivity) getActivity()).getSupportActionBar().setTitle("Halaman Inbox");

        inboxes = new ArrayList<>();
        db = DatabaseHandler.getInstance();

        rvInbox = view.findViewById(R.id.rv_inbox);
        swipe = view.findViewById(R.id.swipe_refresh);

        swipe.setColorSchemeColors(Color.RED, Color.YELLOW, Color.BLUE);
        swipe.setRefreshing(true);

        rvInbox.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        rvInbox.addItemDecoration(new SpacesItemDecoration(new Utils(this).getPixelByDP(8)));

/*        final List<Inbox> inboxes = new ArrayList<>();
        inboxes.add(new Inbox("Budi", "hai ... ! ", "19:08"));
        inboxes.add(new Inbox("Rudi", "ho ... ! ", "11:08"));
        inboxes.add(new Inbox("Aldi", "hei ... ! ", "12:08"));
        inboxes.add(new Inbox("Andi", "hii ... ! ", "10:08"));
        inboxes.add(new Inbox("Sandi", "hasssi ... ! ", "17:08"));
        inboxes.add(new Inbox("Ferdi", "haaai ... ! ", "16:08"));*/

        if (!db.getAllInbox().isEmpty()) {
            inboxes.addAll(db.getAllInbox());
            for (Inbox inbox : db.getAllInbox()) {
                Log.d(TAG, "onCreate inbox data: " + inbox.toString());
            }
        }
        adapter = new InboxAdapter();
        adapter.addAll(inboxes);
//        adapter.add(new Inbox("aasas", "sasa", "sas"));
        if (adapter.getItemCount() > 0) {
            swipe.setRefreshing(false);
        }
        rvInbox.setAdapter(adapter);
        rvInbox.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvInbox,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), AddDataActivity.class);
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
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Item Akan Terhapus");
                alertDialog.setMessage("Apakah anda yakin untuk menghapus !");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteInbox(inboxes.get(position));
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

        FloatingActionButton fab = view.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDataActivity.class);
                startActivityForResult(intent, 12);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            if (resultCode == 1) {
                Inbox inbox = data.getParcelableExtra("inbox");
                db.addInbox(inbox);
                adapter.add(inbox);
                swipe.setRefreshing(false);
            } else if (resultCode == 2) {
                Inbox inbox = data.getParcelableExtra("inbox");
                int inboxPosition = data.getIntExtra("inbox-position", 0);
                Log.d(TAG, "onActivityResult: inbox position : " + inboxPosition);
                db.updateInbox(inbox);
                adapter.remove(inboxPosition);
                adapter.add(inboxPosition, inbox);
            }
        }
    }
}
