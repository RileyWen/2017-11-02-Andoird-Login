package com.rileywen.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Dial_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial_);

        String[] info = (String[]) getIntent().getStringArrayExtra("info");
        Button button_call = (Button) findViewById(R.id.button_Call);

        TextView text = (TextView) findViewById(R.id.text_Info);

        text.setText(info[0]+"\n"+info[1]);

        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10010"));
                startActivity(intent);
            }
        });
    }
}
