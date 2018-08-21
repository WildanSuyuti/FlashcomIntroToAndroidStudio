package id.co.flashcome.introandroidstudio.feature.inbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.co.flashcome.introandroidstudio.R;
import id.co.flashcome.introandroidstudio.model.Inbox;

public class AddDataActivity extends AppCompatActivity {

    private EditText etPengirim, etPesan, etJam;
    private Button btnSimpan;
    private int inboxPosition;
    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Data");

        etPengirim = findViewById(R.id.et_pengirim);
        etPesan = findViewById(R.id.et_pesan);
        etJam = findViewById(R.id.et_jam);
        btnSimpan = findViewById(R.id.btn_simpan);

        isEdit = getIntent().getBooleanExtra("is-edit", false);
        if (isEdit) {
            inboxPosition = getIntent().getIntExtra("inbox-position", 0);
            getSupportActionBar().setTitle("Ubah Data");
            Inbox inbox = getIntent().getParcelableExtra("detail-inbox");
            etPengirim.setText(inbox.getPengirim());
            etPesan.setText(inbox.getPesan());
            etJam.setText(inbox.getJam());
            btnSimpan.setText("Ubah Data");
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    public void addData(View view) {
        String pengirim = etPengirim.getText().toString();
        String pesan = etPesan.getText().toString();
        String jam = etJam.getText().toString();

        Intent intent = new Intent();
        Inbox inbox = new Inbox(pengirim, pesan, jam);
        intent.putExtra("inbox", inbox);
        if (isEdit) {
            intent.putExtra("inbox-position", inboxPosition);
            setResult(2, intent);
        } else {
            setResult(1, intent);
        }
        finish();
    }

}
