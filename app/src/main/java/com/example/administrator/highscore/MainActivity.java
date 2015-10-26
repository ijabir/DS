package com.example.administrator.highscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView score;

    protected final static int DEFAULT = 0;
    int temp = DEFAULT, inputs = DEFAULT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText)findViewById(R.id.input);
        score = (TextView)findViewById(R.id.hs);

        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        temp = sharedPreferences.getInt("highScore",DEFAULT);
        if (temp==DEFAULT){
            Toast.makeText(this, "Database Empty\n", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Loading Successfull",Toast.LENGTH_LONG).show();
            score.setText("Till now the High Score is\n" + temp);
        }

    }

        public void button(View view) {

        if (input.getText().toString().trim().length()==0){
            showMessage("Error", "Please Enter a number");
            return;
        }
        else{
            inputs = Integer.parseInt(input.getText().toString());
            if(inputs>temp){
                temp = inputs;
            }
            SharedPreferences sharedPreferences =  getSharedPreferences("MyData",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("highScore", temp);
            editor.commit();

            input.setText("");
            score.setText("High Score Till Now is\n"+temp);
        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }

    public void reset(View view) {
        SharedPreferences sharedPreferences =  getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        temp = DEFAULT;
        score.setText("Database Empty\n");
    }
}
