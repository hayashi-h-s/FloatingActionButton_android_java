package com.haya.alertdialog_java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private Button button;
    private FloatingActionButton fab;
    private DialogFragment dialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.text_view);

        Button button = findViewById(R.id.button);

        fab = findViewById(R.id.fab_dashboard);


        // ボタンタップでAlertを表示させる
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager = getSupportFragmentManager();

                // DialogFragment を継承したAlertDialogFragmentのインスタンス
                dialogFragment = new AlertDialogFragment();
                // DialogFragmentの表示
                dialogFragment.show(fragmentManager, "test alert dialog");
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "ボタンクリック", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void setTextView(String message){
        textView.setText(message);
    }

    // DialogFragment を継承したクラス
    public static class AlertDialogFragment extends DialogFragment {
        // 選択肢のリスト
        private String[] menulist = {"選択(1)","選択(2)","選択(3)"};

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            // タイトル
            alert.setTitle("Test AlertDialog");
            alert.setItems(menulist, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idx) {
                    // 選択１
                    if (idx == 0) {
                        setMassage(menulist[0]);
                    }
                    // 選択２
                    else if (idx == 1) {
                        setMassage(menulist[1]);
                    }
                    // 選択３, idx == 2
                    else{
                        setMassage(menulist[2]);
                    }
                }
            });

            return alert.create();
        }

        private void setMassage(String message) {
            MainActivity mainActivity = (MainActivity) getActivity();
            if(mainActivity!= null) {
                mainActivity.setTextView(message);
            }
        }
    }
}