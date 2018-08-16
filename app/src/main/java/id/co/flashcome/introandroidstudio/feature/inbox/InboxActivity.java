package id.co.flashcome.introandroidstudio.feature.inbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.Inbox;
import id.co.flashcome.introandroidstudio.ui.SpacesItemDecoration;
import id.co.flashcome.introandroidstudio.utility.RecyclerTouchListener;
import id.co.flashcome.introandroidstudio.utility.Utils;

/**
 * Created by kakaroto on 16/08/18.
 */
public class InboxActivity extends AppCompatActivity {

    private RecyclerView rvInbox;
    private InboxAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        rvInbox = findViewById(R.id.rv_inbox);
        rvInbox.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rvInbox.addItemDecoration(new SpacesItemDecoration(new Utils(this).getPixelByDP(8)));

        List<Inbox> inboxes = new ArrayList<>();
        inboxes.add(new Inbox("Budi", "hai ... ! ", "19:08"));
        inboxes.add(new Inbox("Rudi", "ho ... ! ", "11:08"));
        inboxes.add(new Inbox("Aldi", "hei ... ! ", "12:08"));
        inboxes.add(new Inbox("Andi", "hii ... ! ", "10:08"));
        inboxes.add(new Inbox("Sandi", "hasssi ... ! ", "17:08"));
        inboxes.add(new Inbox("Ferdi", "haaai ... ! ", "16:08"));

        adapter = new InboxAdapter();
        adapter.addAll(inboxes);
        adapter.add(new Inbox("aasas", "sasa", "sas"));
        rvInbox.setAdapter(adapter);

        rvInbox.addOnItemTouchListener(new RecyclerTouchListener(this, rvInbox,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Toast.makeText(InboxActivity.this,
                                adapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

    }

    public void remove(View view) {
        adapter.remove(0);
    }
}
