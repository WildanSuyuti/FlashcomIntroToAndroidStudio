package id.co.flashcome.introandroidstudio.feature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.main.NavigationActivity;
import id.co.flashcome.introandroidstudio.model.User;
import id.co.flashcome.introandroidstudio.utility.DatabaseHandler;
import id.co.flashcome.introandroidstudio.utility.SessionManager;

/**
 * Created by kakaroto on 06/09/18.
 */
public class ProfileFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
}
