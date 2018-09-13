package id.co.flashcome.introandroidstudio.feature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.main.NavigationActivity;
import id.co.flashcome.introandroidstudio.model.User;
import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

/**
 * Created by kakaroto on 06/09/18.
 */
public class ProfileFragment extends Fragment {
    private final String TAG = ProfileFragment.class.getSimpleName();

//    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*
        button = view.findViewById(R.id.btn_call_bottom_sheet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });
*/

        Log.d(TAG, "onViewCreated: is called");
        ((NavigationActivity) getActivity()).getSupportActionBar().setTitle("Halaman Profile");

        TextView tvId = view.findViewById(R.id.tv_id);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvEmail = view.findViewById(R.id.tv_email);

        DatabaseHandler db = DatabaseHandler.getInstance();
        if (!db.getAllUser().isEmpty()) {
            User user = DatabaseHandler.getInstance().getUser(SessionManager.getInstance().getEmail());
            if (user != null) {
                tvId.setText(String.valueOf(user.getId()));
                tvName.setText(user.getNama());
                tvEmail.setText(user.getEmail());
            }
        }
    }

    @OnClick(R.id.btn_call_bottom_sheet)
    public void callBottomSheet() {
        showBottom();
    }

    private void showBottom() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
