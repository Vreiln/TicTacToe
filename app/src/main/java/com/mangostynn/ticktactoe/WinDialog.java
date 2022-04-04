package com.mangostynn.ticktactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class WinDialog extends Dialog {

    public WinDialog(@NonNull Context context,String message,MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog_layout);

        dialogMessage = findViewById(R.id.dialogMessage);
        dialogMessage.setText(this.message);

        startAgainBtn = findViewById(R.id.startBtn);
        startAgainBtn.setOnClickListener(e->{
            mainActivity.restartMatch();
            dismiss();
        });
    }
    private final String message;
    private final MainActivity mainActivity;
    private TextView dialogMessage;
    private Button startAgainBtn;
}
