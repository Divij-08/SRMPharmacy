package com.divij.srmpharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class secondactivity extends AppCompatActivity {
    TextView Heading;
    TextView Name;
    TextView description;
    TextView time_taken;
    TextView interaction;
    TextView time;
    TextView describe;
    TextView interact;
    ImageView img_benefit_1;
    ImageView img_avoid_1;
    ImageView img_benefit_2;
    private String link;
    private Firebase mref;
   // private FirebaseDatabase fdata=FirebaseDatabase.getInstance();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
       // Heading = findViewById(R.id.Heading);
        Name=findViewById(R.id.Name);
        time_taken=findViewById(R.id.Time_Taken);
        interaction=findViewById(R.id.Interaction);
        description=findViewById(R.id.Description);
        time = findViewById(R.id.Time);
        describe=findViewById(R.id.describe);
        interact=findViewById(R.id.interact);
        img_benefit_1=findViewById(R.id.imageView3);
        img_avoid_1=findViewById(R.id.img_avoid_1);
        img_benefit_2=findViewById(R.id.imageView4);




        //Benefits=findViewById(R.id.Benefits);
        mref=new Firebase("https://srm-pharmacy-a21dc.firebaseio.com/Users");
//

        final String selected =getIntent().getStringExtra("Listviewclickvalue");

        Name.setText(selected);
        ValueEventListener selectedListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //.setText(dataSnapshot.child(selected.toString()).child("Food_Avoided").getValue().toString());
                time_taken.setText(dataSnapshot.child(selected.toString()).child("When should take").getValue().toString());
                interaction.setText(dataSnapshot.child(selected.toString()).child("Interacting with").getValue().toString());
                description.setText(dataSnapshot.child(selected.toString()).child("Description of drug-food interaction effect").getValue().toString());
                link=dataSnapshot.child(selected.toString()).child("Food_Avoided").getValue().toString();
               // String link = databaseReference1.getKey();
                Picasso.get().load(link).into(img_avoid_1);
                // DatabaseReference databaseReference=fdata.getReference().child("Users").child("A");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        };
        mref.addListenerForSingleValueEvent(selectedListener);
    }
}