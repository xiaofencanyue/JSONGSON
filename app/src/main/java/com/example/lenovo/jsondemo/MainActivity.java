package com.example.lenovo.jsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3;
    private TextView txt_Result;
    private String jsonStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {

        txt_Result = (TextView) findViewById(R.id.txt_Result);
        Student s1 = new Student(101, "张三", 20);
        Student s2 = new Student(102, "李四", 10);
        Student s3 = new Student(103, "王五", 30);
        final Student[] stus = {s1, s2, s3};

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray array = new JSONArray();
                for (int i = 0; i < stus.length; i++) {
                    JSONObject stuObj = getStudentJsonObj(stus[i]);
                    array.put(stuObj);
                }
                JSONObject obj = new JSONObject();
                try {
                    obj.put("stuList", array);
                } catch (JSONException e) {
                    Log.i("MainActivity", e.toString());
                }
                jsonStr = obj.toString();
                txt_Result.setText(obj.toString());
            }
        });


        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONObject obj = new JSONObject(jsonStr);
                    JSONArray array = obj.getJSONArray("stuList");
                    ArrayList<Student> stuList = new ArrayList<Student>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stuObj = array.getJSONObject(i);
                        int id = stuObj.getInt("id");
                        String name = stuObj.getString("name");
                        int age = stuObj.getInt("age");
                        Student s = new Student(id, name, age);
                        stuList.add(s);
                    }
                } catch (JSONException e) {
                    Log.i("MainActivity", e.toString());
                }
            }
        });


        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonStr = "{\"i\":101,\"name\":jack,\"age\":25}";
                Gson gson = new Gson();
                Student s = gson.fromJson(jsonStr, Student.class);
                txt_Result.setText(s.getName() + "  " + s.getAge());
            }
        });
    }

    private JSONObject getStudentJsonObj(Student stu) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", stu.getId());
            obj.put("name", stu.getName());
            obj.put("age", stu.getAge());
        } catch (JSONException e) {
            Log.i("MainActivity", e.toString());
        }
        return obj;
    }

}
