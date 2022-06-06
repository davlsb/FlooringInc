package edu.qc.seclass.fim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dev.chrisbanes.insetter.Insetter;

public class categoriesAndSearch extends AppCompatActivity {

    private RecyclerView productsRv;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private ArrayList<ModelProduct> productList;
    private AdapterProduct adapaterProductSeller;

    private ConstraintLayout constraintLayout;

    private Button filterProductBtn;
    private EditText searchView;

    String store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_and_search);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        Insetter.builder()
                .padding(WindowInsetsCompat.Type.statusBars())
                .applyToView(findViewById(R.id.viewForReal));

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SearchView searchView = (SearchView) findViewById(R.id.searchViewer);
        EditText searchEditText = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.black));
        searchEditText.setHintTextColor(getResources().getColor(R.color.black));

        populateDropDown();


        productsRv = findViewById(R.id.productsRv);



        firebaseAuth = FirebaseAuth.getInstance();
        loadAllProducts();


        /*

        searchView = findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    constraintLayout = findViewById(R.id.categoriesLayout);
                    if(charSequence.length()>1){
                        constraintLayout.setVisibility(View.GONE);
                        productsRv.setVisibility(View.VISIBLE);
                    } else {
                        constraintLayout.setVisibility(View.VISIBLE);
                        productsRv.setVisibility(View.GONE);
                    }
                    adapaterProductSeller.getFilter().filter(charSequence);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

         */

       searchFunc();


        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(categoriesAndSearch.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void searchFunc() {

        SearchView searchView = findViewById(R.id.searchViewer);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
                String storeID = storeSpinner.getSelectedItem().toString().trim();
                Intent intent = new Intent(categoriesAndSearch.this, showFloors.class);
                Bundle extras = new Bundle();
                extras.putString("string-key", "Tile");
                extras.putString("store-id", storeID);
                extras.putString("search-value", query);
                extras.putString("searchBool","true");
                intent.putExtras(extras);
                startActivity(intent);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        /*

        final EditText searchView = (EditText) findViewById(R.id.searchViewer);
        TextView.OnEditorActionListener searchListener = new TextView.OnEditorActionListener(){
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
                    String storeID = storeSpinner.getSelectedItem().toString().trim();
                    Intent intent = new Intent(categoriesAndSearch.this, showFloors.class);
                    Bundle extras = new Bundle();
                    extras.putString("string-key", "Tile");
                    extras.putString("store-id", storeID);
                    extras.putString("search-value", searchView.getText().toString().trim());
                    extras.putString("searchBool","true");
                    intent.putExtras(extras);
                    startActivity(intent);
                }
                return true;
            }
        };

         */
    }

    private void populateDropDown() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference fDatabaseRoot = database.getReference();

        fDatabaseRoot.child("Stores").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> storeList = new ArrayList<String>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String store = snapshot.getKey().toString();
                    if (store != null) {
                        storeList.add(store);

                    }
                }
                if(storeList.size()==0){
                    storeList.add("No Stores in DB");
                }

                Spinner spinner = (Spinner) findViewById(R.id.storesDropdown);
                ArrayAdapter<String> addressAdapter = new ArrayAdapter<String>(categoriesAndSearch.this, android.R.layout.simple_spinner_item, storeList);
                addressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(addressAdapter);

                categoryButtonClickers();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void categoryButtonClickers() {
        Button allFloors = (Button) findViewById(R.id.allFloorsButton);
        ImageButton marble = findViewById(R.id.marbleButton);
        ImageButton wood = findViewById(R.id.woodButton);
        ImageButton vinyl = findViewById(R.id.vinylButton);
        ImageButton stone = findViewById(R.id.stoneButton);
        ImageButton tile = findViewById(R.id.tileButton);


        allFloors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFloorsFunction();
            }
        });

        marble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laminateButtonClick();
            }
        });

        wood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woodButtonClick();
            }
        });

        vinyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vinylButtonClick();
            }
        });

        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoneButtonClick();
            }
        });

        tile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tileButtonClick();
            }
        });

    }

    private void loadAllProducts() {
        productList = new ArrayList<>();

        //get all products
        if(firebaseAuth.getInstance().getCurrentUser()!=null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
            reference.child(firebaseAuth.getUid()).child("Products")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            productList.clear();
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                ModelProduct modelProduct = ds.getValue(ModelProduct.class);
                                productList.add(modelProduct);
                            }
                            adapaterProductSeller = new AdapterProduct(categoriesAndSearch.this, productList);
                            productsRv.setAdapter(adapaterProductSeller);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }


    }


    public void allFloorsFunction(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "All");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void tileButtonClick(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "Tile");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void woodButtonClick(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "Wood");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void vinylButtonClick(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "Vinyl");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void stoneButtonClick(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "Stone");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void laminateButtonClick(){
        Spinner storeSpinner = (Spinner) findViewById(R.id.storesDropdown);
        String storeID = storeSpinner.getSelectedItem().toString().trim();
        Intent intent = new Intent(this, showFloors.class);
        Bundle extras = new Bundle();
        extras.putString("string-key", "Laminate");
        extras.putString("store-id", storeID);
        intent.putExtras(extras);
        startActivity(intent);
    }

}