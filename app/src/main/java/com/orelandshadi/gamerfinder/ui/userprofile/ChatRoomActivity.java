package com.orelandshadi.gamerfinder.ui.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orelandshadi.gamerfinder.R;

public class ChatRoomActivity extends AppCompatActivity {

    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendMessageButton = (Button) findViewById(R.id.sendMessageId);


        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageButton.setSelected(!sendMessageButton.isSelected());
                if(sendMessageButton.isSelected()) {
                    Toast.makeText(ChatRoomActivity.this,"The message sent ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
