package com.example.catsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    DatePicker picker;
    Button btnGet;
    TextView tvw;
    String result;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Log.d("Test", "Intent Called2");

        tvw=(TextView)findViewById(R.id.textView1);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        btnGet=(Button)findViewById(R.id.button);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                EditText edit = (EditText)findViewById(R.id.website_edittext);
                result = edit.getText().toString();

                intent.putExtra("taskName", result);
                intent.putExtra("taskDate", ""+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

//       Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                finish();
//            }
//        });

//       }


}
