package com.narara.room_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.icu.text.Edits;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultView = findViewById(R.id.result_text);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class,"todo-db")
                .allowMainThreadQueries()
                .build();

        mResultView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
                mResultView.setText(db.todoDao().getAll().toString());
            }
        });

    }
}
