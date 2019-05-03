package apps.pp.endgame;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Votes extends AppCompatActivity {


    private TextView v_spiderman;
    FirebaseDatabase rtdb;
    private ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votes);

        users = new ArrayList<User>();
        int totalVotes = 0;

        rtdb = FirebaseDatabase.getInstance();

        rtdb.getReference().child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot hijo : dataSnapshot.getChildren()) {
                    User u = hijo.getValue(User.class);
                    users.add(u);
                }

                int totalSpider = 0;
                int totalIron = 0;
                int totalAmerica = 0;
                int totalMarvel = 0;
                int totalHulk = 0;
                int totalViuda = 0;
                int totalThor = 0;
                int totalStrange = 0;


                for (int i = 0; i < users.size(); i++) {

                    String a = users.get(i).getVote();

                    if (a.equals("Spiderman")) {
                        totalSpider += 1;
                    } else if (a.equals("Ironman")) {
                        totalIron += 1;
                    } else if (a.equals("Capitán America")) {
                        totalAmerica += 1;
                    }
                     else if (a.equals("Capitán Marvel")) {
                        totalMarvel += 1;
                    } else if (a.equals("Hulk")) {
                        totalHulk += 1;
                    } else if (a.equals("La viuda Negra")) {
                        totalViuda += 1;
                    }

                    else if (a.equals("Thor")){
                        totalThor+=1;
                    }

                    else if (a.equals("Doctor Strange")){
                        totalStrange+=1;
                    }
                }

                int totalSum=totalSpider+totalIron+totalAmerica+totalMarvel+totalHulk+totalViuda+totalThor+totalStrange;


                v_spiderman = findViewById(R.id.v_spiderman);
                v_spiderman.setText(totalSpider/totalSum);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
