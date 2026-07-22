package com.codewithme.southcampus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class SyllabusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        RecyclerView recyclerView = findViewById(R.id.syllabusRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> syllabusList = Arrays.asList(
                "B.Sc. Computer Science - Semester 1",
                "B.Sc. Computer Science - Semester 2",
                "B.A. English Hons - Semester 1",
                "B.A. English Hons - Semester 2",
                "B.Com Hons - Semester 1",
                "B.Com Hons - Semester 2",
                "Generic Electives",
                "Skill Enhancement Courses"
        );

        SyllabusAdapter adapter = new SyllabusAdapter(syllabusList, this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Syllabus");
        }
    }
}
