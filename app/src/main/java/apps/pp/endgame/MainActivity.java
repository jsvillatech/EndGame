package apps.pp.endgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private RadioGroup radioGroupGen;
    private RadioGroup radioGroupSup;
    private EditText name;
    private EditText age;
    private Button vote;
    private RadioButton gen;
    private RadioButton sup;
    FirebaseDatabase rtdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupGen = findViewById(R.id.radioGroupGen);
        radioGroupSup = findViewById(R.id.radioGroupSup);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        vote = findViewById(R.id.vote);

        rtdb = FirebaseDatabase.getInstance();

        vote.setOnClickListener((v) -> {


            int idGen = radioGroupGen.getCheckedRadioButtonId();
            int idSup = radioGroupSup.getCheckedRadioButtonId();
            gen = findViewById(idGen);
            sup = findViewById(idSup);


            User user = new User(UUID.randomUUID().toString(), name.getText().toString(), gen.getText().toString(),
                    age.getText().toString(), sup.getText().toString());


            rtdb.getReference().child("vote").push().setValue(user);


            //Toast.makeText(MainActivity.this,
            //gen.getText()+" and "+sup.getText(), Toast.LENGTH_SHORT).show();


            Intent i =new Intent(MainActivity.this,Votes.class);
            startActivity(i);
        });


    }
}
