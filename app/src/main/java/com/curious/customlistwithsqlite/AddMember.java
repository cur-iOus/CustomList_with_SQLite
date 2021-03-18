package com.curious.customlistwithsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

public class AddMember extends AppCompatActivity {

    EditText nameEditText, addressEdittext;
    Button okButton;
    Animation animation;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        nameEditText = findViewById(R.id.nameEditTextID);
        addressEdittext = findViewById(R.id.addressEditTextID);
        okButton = findViewById(R.id.okButtonID);
        
        intent = new Intent(AddMember.this, MainActivity.class);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String address = addressEdittext.getText().toString().trim();

                if (!name.isEmpty()){
                    intent.putExtra("name", name);
                    intent.putExtra("address", address);
                    startActivity(intent);
                    finish();
                }else {
                    MainActivity.showToast(AddMember.this, "Fill-up Name");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}