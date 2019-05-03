package apps.pp.endgame;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Votes extends AppCompatActivity {


    private TextView v_spiderman;
    private TextView v_ironman;
    private TextView v_america;
    private TextView v_marvel;
    private TextView v_hulk;
    private TextView v_viuda;
    private TextView v_thor;
    private TextView v_strange;
    private RadioGroup radioGroupVotos;
    private RadioButton filter;
    private Button button_filtro;
    private Button button_again;

    FirebaseDatabase rtdb;
    private ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        v_spiderman = findViewById(R.id.v_spiderman);
        v_ironman = findViewById(R.id.v_ironman);
        v_america = findViewById(R.id.v_america);
        v_marvel = findViewById(R.id.v_marvel);
        v_hulk = findViewById(R.id.v_hulk);
        v_viuda = findViewById(R.id.v_viuda);
        v_thor = findViewById(R.id.v_thor);
        v_strange = findViewById(R.id.v_strange);
        radioGroupVotos = findViewById(R.id.radioGroupVotos);
        button_filtro = findViewById(R.id.button_filtro);
        button_again=findViewById(R.id.button_again);


        users = new ArrayList<User>();

        rtdb = FirebaseDatabase.getInstance();

        rtdb.getReference().child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot hijo : dataSnapshot.getChildren()) {
                    User u = hijo.getValue(User.class);
                    users.add(u);
                }


                button_filtro.setOnClickListener((v) -> {
                    int idFilter = radioGroupVotos.getCheckedRadioButtonId();
                    filter = findViewById(idFilter);
                    //Toast.makeText(Votes.this,
                    //filter.getText(), Toast.LENGTH_SHORT).show();

                    ArrayList<User> aux = new ArrayList<User>();
                    aux = filterPeople(filter.getText().toString());

                    countVotes(aux);


                });

                button_again.setOnClickListener((v)->{
                   finish();
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void countVotes(ArrayList<User> aux) {

        int votosSpiderman = 0;
        int votosIronman = 0;
        int votosAmerica = 0;
        int votosMarvel = 0;
        int votosHulk = 0;
        int votosViuda = 0;
        int votosThor = 0;
        int votosStrange = 0;
        int total = 0;

        for (int i = 0; i < aux.size(); i++) {


            if (aux.get(i).getVote().equals("Spiderman")) {
                votosSpiderman += 1;
            } else if (aux.get(i).getVote().equals("Ironman")) {
                votosIronman += 1;
            } else if (aux.get(i).getVote().equals("Capitán América")) {
                votosAmerica += 1;
            } else if (aux.get(i).getVote().equals("Capitán Marvel")) {
                votosMarvel += 1;
            } else if (aux.get(i).getVote().equals("Hulk")) {
                votosHulk += 1;
            } else if (aux.get(i).getVote().equals("La viuda Negra")) {
                votosViuda += 1;
            } else if (aux.get(i).getVote().equals("Thor")) {
                votosThor += 1;
            } else {
                votosStrange += 1;
            }
        }

        total = votosSpiderman + votosIronman + votosAmerica + votosMarvel + votosHulk + votosViuda + votosThor + votosStrange;
        DecimalFormat f = new DecimalFormat("##.00");

        if (total != 0) {
            v_spiderman.setText("Spiderman: " + f.format(((double) votosSpiderman / total) * 100) + "%");
            v_ironman.setText("Ironman: " + f.format(((double) votosIronman / total) * 100) + "%");
            v_america.setText("Capitan America: " + f.format(((double) votosAmerica / total) * 100) + "%");
            v_marvel.setText("Capitan marvel: " + f.format(((double) votosMarvel / total) * 100) + "%");
            v_hulk.setText("Hulk: " + f.format(((double) votosHulk / total) * 100) + "%");
            v_viuda.setText("La viuda negra: " + f.format(((double) votosViuda / total) * 100) + "%");
            v_thor.setText("Thor: " + f.format(((double) votosThor / total) * 100) + "%");
            v_strange.setText("Doctor Strange: " + f.format(((double) votosStrange / total) * 100) + "%");
        } else {

            v_spiderman.setText("Spiderman" + "NO HAY DATOS");
            v_ironman.setText("Ironman: " + "NO HAY DATOS");
            v_america.setText("Capitan America: " + "NO HAY DATOS");
            v_marvel.setText("Capitan marvel: " + "NO HAY DATOS");
            v_hulk.setText("Hulk: " + "NO HAY DATOS");
            v_viuda.setText("La viuda negra: " + "NO HAY DATOS");
            v_thor.setText("Thor: " + "NO HAY DATOS");
            v_strange.setText("Doctor Strange: " + "NO HAY DATOS");


        }


    }


    public ArrayList<User> filterPeople(String criteria) {
        ArrayList<User> aux = new ArrayList<User>();
        if (criteria.equals("Niños")) {
            for (int i = 0; i < users.size(); i++) {
                int edad = Integer.parseInt(users.get(i).getAge());
                if (edad <= 12) {
                    aux.add(users.get(i));
                }
            }
            return aux;
        } else if (criteria.equals("Mujeres adultas")) {


            for (int i = 0; i < users.size(); i++) {
                int edad = Integer.parseInt(users.get(i).getAge());
                if (edad >= 18 && users.get(i).getSex().equals("F")) {
                    aux.add(users.get(i));
                }
            }
            return aux;


        } else if (criteria.equals("Hombres adultos")) {

            for (int i = 0; i < users.size(); i++) {
                int edad = Integer.parseInt(users.get(i).getAge());
                if (edad >= 18 && users.get(i).getSex().equals("M")) {
                    aux.add(users.get(i));
                }
            }
            return aux;


        } else if (criteria.equals("Mujeres adolescentes")) {


            for (int i = 0; i < users.size(); i++) {
                int edad = Integer.parseInt(users.get(i).getAge());
                if (edad > 12 && users.get(i).getSex().equals("F") && edad < 18) {
                    aux.add(users.get(i));
                }
            }
            return aux;


        } else if (criteria.equals("Hombres adolescentes")) {


            for (int i = 0; i < users.size(); i++) {
                int edad = Integer.parseInt(users.get(i).getAge());
                if (edad > 12 && users.get(i).getSex().equals("M") && edad < 18) {
                    aux.add(users.get(i));
                }
            }
            return aux;


        }


        return users;


    }


}
