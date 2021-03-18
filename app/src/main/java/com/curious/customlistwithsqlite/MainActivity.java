
package com.curious.customlistwithsqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Member> memberArrayList;
    DatabaseHelper databaseHelper;
    CustomAdapter customAdapter;

    public static Toast toast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewID);
        memberArrayList = new ArrayList<Member>();
        databaseHelper = new DatabaseHelper(MainActivity.this);

        Intent intent = getIntent();
        if (intent.hasExtra("name")){
            showToast(MainActivity.this, intent.getStringExtra("name")+" Added");
            String name = getIntent().getStringExtra("name");
            String address = getIntent().getStringExtra("address");
            Member member = new Member(name, address, 1);
            databaseHelper.addOne(member);
        }
        updateList();

        /* okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String address = addressEdittext.getText().toString().trim();
                if(!name.isEmpty()){
                    Member member = new Member(name, address, 1);
                    databaseHelper.addOne(member);
                    updateList();
                }else {
                    showToast(MainActivity.this, "Fill up fields");
                }
            }
        }); */

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = (Member) parent.getItemAtPosition(position);
                boolean b = databaseHelper.deleteOne(member);
                if (b){
                    //Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    showToast(MainActivity.this,"Deleted");
                }else {
                    //Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    showToast(MainActivity.this, "Failed");
                }
                updateList();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMemberId){
            Intent intent = new Intent(MainActivity.this, AddMember.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateList() {
        memberArrayList = databaseHelper.getAllMember();
        customAdapter = new CustomAdapter(MainActivity.this, memberArrayList);
        listView.setAdapter(customAdapter);
    }

    public static void showToast(Context context, String text){
        if (toast != null) {
            toast.cancel();
        }
        toast = toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}











