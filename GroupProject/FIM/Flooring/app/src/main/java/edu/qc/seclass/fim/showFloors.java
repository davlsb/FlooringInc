package edu.qc.seclass.fim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showFloors extends AppCompatActivity {

    private RecyclerView productsRv;

    private ArrayList<ModelProduct> productList;
    private AdapterProduct adapaterProductSeller;

    private FirebaseAuth firebaseAuth;

    String store_id, search_value;

    private ArrayList<ModelProduct> productsList;
    private AdapterProductUser adapterProductUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_floors);

        Intent receivedIntent = getIntent();
        Bundle extras = receivedIntent.getExtras();
        String string_value = extras.getString("string-key", "");
        String search_bool = extras.getString("searchBool", "");
        store_id = extras.getString("store-id", "");
        search_value = extras.getString("search-value", "");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        firebaseAuth = FirebaseAuth.getInstance();
        productsRv = findViewById(R.id.productsRv);


        backButton();
        addItemButton();

        if(search_bool.equals("true")){
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Search Results");
            searchFunction();
        }
        else if(string_value.equals("Laminate")) {
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Laminate Floors");
            loadLaminateFloors();
        }
        else if(string_value.equals("Tile")) {
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Tile Floors");
            loadTileFloors();
        }
        else if(string_value.equals("Wood")) {
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Wood Floors");
            loadWoodFloors();
        }
        else if(string_value.equals("Vinyl")) {
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Vinyl Floors");
            loadVinylFloors();
        }
        else if(string_value.equals("Stone")) {
            TextView searchTitle = findViewById(R.id.floorSelectionTitle);
            searchTitle.setText("Stone Floors");
            loadStoneFloors();
        }
        else
            loadFloorsCategory();


    }

    private void searchFunction() {
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Tile")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            if(modelProduct.getProductTitle().toLowerCase().contains(search_value.toLowerCase()))
                                productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Wood")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            if(modelProduct.getProductTitle().toLowerCase().contains(search_value.toLowerCase()))
                                productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Vinyl")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            if(modelProduct.getProductTitle().toLowerCase().contains(search_value.toLowerCase()))
                                productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Stone")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            if(modelProduct.getProductTitle().toLowerCase().contains(search_value.toLowerCase()))
                                productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Laminate")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            if(modelProduct.getProductTitle().toLowerCase().contains(search_value.toLowerCase()))
                                productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }


    public void backButton(){
        ImageButton back = (ImageButton) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { backed(); }
        });
    }

    public void backed(){
        super.onBackPressed();
    }

    public void addItemButton(){
        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        if(!firebaseAuth.getInstance().getCurrentUser().isAnonymous()){
            FAB.setVisibility(View.VISIBLE);
        }

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemIntent();
            }
        });
    }

    private void loadFloorsCategory(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Tile")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Wood")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Vinyl")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Stone")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        reference.child("Laminate")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadLaminateFloors(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Laminate")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadTileFloors(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Tile")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadWoodFloors(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Wood")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadVinylFloors(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Vinyl")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void loadStoneFloors(){
        productsList = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores").child(store_id);
        productsList.clear();
        reference.child("Stone")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //productsList.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                            productsList.add(modelProduct);
                        }
                        adapterProductUser = new AdapterProductUser(showFloors.this, productsList);
                        //set Adapter
                        productsRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }



    public void addItemIntent(){
        Intent intent = new Intent(this, addFloor.class);
        startActivity(intent);
    }
}