package edu.qc.seclass.fim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class editProductActivity extends AppCompatActivity {

    private String productID;
    private String productCategory;
    private String productStoreID;

    private TextView name, type, storesID, prodBrand, colorTv, size, price, material, materialLabel, species, speciesLabel;
    private Switch waterResistance;
    private Spinner spinner;
    private FloatingActionButton doneFAB;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference DataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        productID = getIntent().getStringExtra("productID");
        productCategory = getIntent().getStringExtra("productCategory");
        productStoreID = getIntent().getStringExtra("productStoreID");


        name = findViewById(R.id.floorName);
        type = findViewById(R.id.type);
        storesID = findViewById(R.id.storeID);
        colorTv = findViewById(R.id.color);
        size = findViewById(R.id.size);
        price = findViewById(R.id.priceID);
        spinner = (Spinner) findViewById(R.id.categoryDropdown);
        prodBrand = findViewById(R.id.brandTV);

        //specific
        material = findViewById(R.id.ifStoneOrTile);
        materialLabel = findViewById(R.id.ifStoneOrTileLabel);
        species = findViewById(R.id.speciesID);
        speciesLabel = findViewById(R.id.ifWoodLabel);
        waterResistance = findViewById(R.id.waterResistance);
        selectedCategory();


        doneFAB = (FloatingActionButton) findViewById(R.id.doneEditFloatingActionButton);

        firebaseAuth = FirebaseAuth.getInstance();
        loadProductDetails(); //setting on textviews

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        doneFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputData();
            }
        });

        backButton();
    }

    private void loadProductDetails() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Stores").child(productStoreID).child(productCategory);
        ref.child(productID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String productId = "" + snapshot.child(productID).getValue();
                        String productTitle = "" + snapshot.child("productTitle").getValue();
                        String productType = "" + snapshot.child("productType").getValue();
                        String storeID = "" + snapshot.child("storeID").getValue();
                        String category = "" + snapshot.child("category").getValue();
                        String color = "" + snapshot.child("color").getValue();
                        String priceS = "" + snapshot.child("price").getValue();
                        String sizeS = "" + snapshot.child("size").getValue();
                        String brandS = "" + snapshot.child("brand").getValue();
                        String materialS = "" + snapshot.child("material").getValue();
                        String speciesS = "" + snapshot.child("species").getValue();
                        String waterStatusS = "" + snapshot.child("waterStatus").getValue();

                        String timestamp = "" + snapshot.child("timestamp").getValue();
                        String uid = "" + snapshot.child("uid").getValue();


                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(editProductActivity.this,
                                R.array.categories, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        //load to textboxes
                        name.setText(productTitle);
                        type.setText(productType);
                        colorTv.setText(color);
                        size.setText(sizeS);
                        price.setText(priceS);
                        storesID.setText(storeID);
                        spinner.setSelection(adapter.getPosition(category));
                        prodBrand.setText(brandS);
                        material.setText(materialS);
                        species.setText(speciesS);
                        if(waterStatusS.equals("True")){
                            waterResistance.setChecked(true);
                        }



                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }


    private String floorName, floorCategory, employeeStoreID, floorType, colorName, sizeSqft, priceDollars, brand, floorWater, floorMaterial, floorSpecies;

    private void inputData() {
        floorName = name.getText().toString().trim(); //= null
        floorCategory = spinner.getSelectedItem().toString().trim();
        floorType = type.getText().toString().trim();
        employeeStoreID =  storesID.getText().toString().trim(); //= null
        colorName = colorTv.getText().toString().trim();
        sizeSqft = size.getText().toString().trim();
        priceDollars = price.getText().toString().trim();
        brand = prodBrand.getText().toString().trim();
        floorMaterial = material.getText().toString().trim();
        floorSpecies = species.getText().toString().trim();
        if(waterResistance.isChecked()){
            floorWater = "True";
        } else {
            floorWater = "False";
        }



        //validate
        if(TextUtils.isEmpty(floorName)){
            Toast.makeText(this, "Needs a Name", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(floorType)){
            Toast.makeText(this, "Needs Type", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(floorCategory)){
            Toast.makeText(this, "Needs Category", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(employeeStoreID)){
            Toast.makeText(this, "Needs Store ID", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(colorName)){
            Toast.makeText(this, "Needs Color", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(sizeSqft)){
            Toast.makeText(this, "Needs Size", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(TextUtils.isEmpty(priceDollars)){
            Toast.makeText(this, "Needs Price", Toast.LENGTH_SHORT).show();
            return; //don't add item
        }

        if(floorCategory.equals("Tile") || floorCategory.equals("Stone")){
            if(TextUtils.isEmpty(floorMaterial)){
                Toast.makeText(this, "This category needs a material", Toast.LENGTH_SHORT).show();
                return; //don't add item
            }
        }

        if(floorCategory.equals("Wood")){
            if(TextUtils.isEmpty(floorSpecies)){
                Toast.makeText(this, "This category needs a species", Toast.LENGTH_SHORT).show();
                return; //don't add item
            }
        }

        editProduct();
    }

    private void editProduct() {
        progressDialog.setMessage("Editing item in store!");
        progressDialog.show();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("productTitle",""+floorName);
        hashMap.put("productType",""+floorType);
        hashMap.put("storeID", ""+employeeStoreID);
        hashMap.put("category", ""+floorCategory);
        hashMap.put("color", ""+colorName);
        hashMap.put("price", ""+priceDollars);
        hashMap.put("size", ""+sizeSqft);
        hashMap.put("productID", ""+productID);
        hashMap.put("timestamp", ""+productID);
        hashMap.put("brand", ""+ brand);
        hashMap.put("material", ""+floorMaterial);
        hashMap.put("species", ""+floorSpecies);
        hashMap.put("waterStatus", ""+floorWater);
        hashMap.put("uid",""+firebaseAuth.getUid());


        //Delete item first

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Stores").child(productStoreID).child(productCategory);
        ref.child(productID).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed deletation
                        Toast.makeText(editProductActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Stores");
        reference.child(employeeStoreID).child(floorCategory).child(productID).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(editProductActivity.this, "Edit Floor", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(editProductActivity.this, categoriesAndSearch.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(editProductActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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


    public void selectedCategory(){
        spinner = (Spinner) findViewById(R.id.categoryDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int category, long id) {
                showTextView(category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void showTextView(int category) {
        if (category != 0 && category != 3){
            material.setVisibility(View.GONE);
            materialLabel.setVisibility(View.GONE);
        }
        else{
            material.setVisibility(View.VISIBLE);
            materialLabel.setVisibility(View.VISIBLE);
        }
        if(category == 1){
            species.setVisibility(View.VISIBLE);
            speciesLabel.setVisibility(View.VISIBLE);
        }
        else{
            species.setVisibility(View.GONE);
            speciesLabel.setVisibility(View.GONE);
        }
        if(category == 2 || category == 4){
            waterResistance.setVisibility(View.VISIBLE);
        }
        else{
            waterResistance.setVisibility(View.GONE);
        }
    }
}
