package edu.qc.seclass.fim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterProductUser extends RecyclerView.Adapter<AdapterProductUser.HolderProductUser> implements Filterable {


    private Context context;
    public ArrayList<ModelProduct> productsList, filterList;
    private FilterProductUser filter;

    private FirebaseAuth firebaseAuth;

    public AdapterProductUser(Context context, ArrayList<ModelProduct> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public HolderProductUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_products, parent, false);
        return new HolderProductUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductUser holder, int position) {
        ModelProduct modelProduct = productsList.get(position);
        String productId = modelProduct.getProductId();
        String productType = modelProduct.getProductType();
        String productTitle = modelProduct.getProductTitle();
        String productTimestamp = modelProduct.getTimestamp();
        String productCategory = modelProduct.getCategory();
        String color = modelProduct.getColor();
        String price = modelProduct.getPrice();
        String size = modelProduct.getSize();

        String brand = modelProduct.getBrand();
        String material = modelProduct.getMaterial();
        String species = modelProduct.getSpecies();
        String water = modelProduct.getWaterStatus();

        //set data
        holder.titleTV.setText(productTitle);
        holder.quantityTV.setText("Type: " + productType);
        holder.categoryTV.setText("Category: " + productCategory);
        holder.colorTV.setText("Color: " + color);
        holder.price.setText("Price: $" + price);
        holder.sizeTV.setText("Size: " + size +"Sqft");
        holder.speciesTV.setText("Species: " + species);
        holder.brandTV.setText("Brand: " + brand);
        holder.materialTV.setText("Material: " + material);
        holder.waterTV.setText(("Water: " + water));

        holder.speciesTV.setVisibility(View.GONE);
        holder.materialTV.setVisibility(View.GONE);
        holder.waterTV.setVisibility(View.GONE);
        holder.sizeTV.setVisibility(View.GONE);
        holder.quantityTV.setVisibility(View.GONE);

        int[] images = new int[5];
        images[0] = R.drawable.wood;
        images[1] = R.drawable.marble;
        images[2] = R.drawable.tile;
        images[3] = R.drawable.vinyl;
        images[4] = R.drawable.stone;

        if(productCategory.toLowerCase().equals("wood"))
            holder.productIconIv.setImageResource(images[0]);
        else if(productCategory.toLowerCase().equals("laminate"))
            holder.productIconIv.setImageResource(images[1]);
        else if(productCategory.toLowerCase().equals("tile"))
            holder.productIconIv.setImageResource(images[2]);
        else if(productCategory.toLowerCase().equals("vinyl"))
            holder.productIconIv.setImageResource(images[3]);
        else
            holder.productIconIv.setImageResource(images[4]);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsBottomSheet(modelProduct);
            }
        });
    }

    private void detailsBottomSheet(ModelProduct modelProduct) {
        BottomSheetDialog btd = new BottomSheetDialog(context);
        //inflate view for bottomsheet
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_product_details, null);
        btd.setContentView(view);


        ImageButton backButton = view.findViewById(R.id.backButton);
        ImageButton deleteButton = view.findViewById(R.id.deleteButton);
        ImageButton editButton = view.findViewById(R.id.editButton);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getInstance().getCurrentUser().isAnonymous()){
            deleteButton.setVisibility(View.GONE);
            editButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

        ImageView productIcon = view.findViewById(R.id.productIconIv);
        TextView titleTv = view.findViewById(R.id.titleTextView);
        TextView categoryTv = view.findViewById(R.id.categoryTextView);
        TextView quantityTv = view.findViewById(R.id.quantityTextView);
        TextView colorTV = view.findViewById(R.id.colorTextView);
        TextView priceTV = view.findViewById(R.id.priceTextView);
        TextView sizeTV = view.findViewById(R.id.sizeTextView);
        TextView brandTV = view.findViewById(R.id.brandTextView);
        TextView materialTV = view.findViewById(R.id.materialTextView);
        TextView speciesTV = view.findViewById(R.id.speciesTextView);
        TextView waterTV = view.findViewById(R.id.waterTextView);


        String productId = modelProduct.getProductId();
        String storeIDs= modelProduct.getStoreID();

        String productType = modelProduct.getProductType();
        String productTitle = modelProduct.getProductTitle();
        String productTimestamp = modelProduct.getTimestamp();
        String productCategory = modelProduct.getCategory();
        String color = modelProduct.getColor();
        String price = modelProduct.getPrice();
        String size = modelProduct.getSize();


        String brand = modelProduct.getBrand();
        String material = modelProduct.getMaterial();
        String species = modelProduct.getSpecies();
        String water = modelProduct.getWaterStatus();

        titleTv.setText(productTitle);
        quantityTv.setText("Type: " +productType);
        categoryTv.setText("Category: " +productCategory);
        colorTV.setText("Color: " +color);
        sizeTV.setText("Size: " +size);
        priceTV.setText("Price: $" +price);
        brandTV.setText("Brand: " + brand);
        materialTV.setText("Material: " + material);
        speciesTV.setText("Species: " + species);
        waterTV.setText("Water Resistant: " + water);

        if(productCategory.equals("Tile") || productCategory.equals("Stone")){
            materialTV.setVisibility(View.VISIBLE);
        }
        else{
            materialTV.setVisibility(View.GONE);
        }

        if(productCategory.equals("Wood")){
            speciesTV.setVisibility(View.VISIBLE);
        }
        else{
            speciesTV.setVisibility(View.GONE);
        }

        if(productCategory.equals("Vinyl") || productCategory.equals("Laminate")){
            waterTV.setVisibility(View.VISIBLE);
            if(productCategory.equals("Vinyl")){
                if(water.equals("True")){
                    waterTV.setText("This product is Water Resistant");
                } else {
                    waterTV.setText("This product is Waterproof");
                }
            } else {
                if(water.equals("True")){
                    waterTV.setText("This product is Water Resistant");
                } else {
                    waterTV.setText("This product is regular Laminate");
                }
            }
        } else{
            waterTV.setVisibility(View.GONE);
        }






        int[] images = new int[5];
        images[0] = R.drawable.wood;
        images[1] = R.drawable.marble;
        images[2] = R.drawable.tile;
        images[3] = R.drawable.vinyl;
        images[4] = R.drawable.stone;

        if(productCategory.toLowerCase().equals("wood"))
            productIcon.setImageResource(images[0]);
        else if(productCategory.toLowerCase().equals("laminate"))
            productIcon.setImageResource(images[1]);
        else if(productCategory.toLowerCase().equals("tile"))
            productIcon.setImageResource(images[2]);
        else if(productCategory.toLowerCase().equals("vinyl"))
            productIcon.setImageResource(images[3]);
        else
            productIcon.setImageResource(images[4]);



        //show dialog
        btd.show();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open edit
                Intent intent = new Intent(context, editProductActivity.class);
                intent.putExtra("productID", productTimestamp);
                intent.putExtra("productCategory", productCategory);
                intent.putExtra("productStoreID", storeIDs);
                context.startActivity(intent);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show delete
                deleteProduct(productTimestamp, productCategory, storeIDs);
                btd.hide();

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btd.dismiss();
            }
        });

    }

    private void deleteProduct(String productId, String productCategory, String storeID) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Stores").child(storeID).child(productCategory);
        ref.child(productId).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Product deleted...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, categoriesAndSearch.class);
                        Bundle extras = new Bundle();
                        extras.putString("store-id", storeID);
                        context.startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //failed deletation
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new FilterProductUser(this, filterList);
        }
        return filter;
    }

    class HolderProductUser extends RecyclerView.ViewHolder{

        //uid views
        private ImageView productIconIv;
        private TextView titleTV, quantityTV, categoryTV, price, colorTV, sizeTV, brandTV, waterTV, materialTV, speciesTV;

        public HolderProductUser(@NonNull View itemView) {
            super(itemView);

            //init ui views
            productIconIv = itemView.findViewById(R.id.productIcon);
            titleTV = itemView.findViewById(R.id.titleTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            categoryTV = itemView.findViewById(R.id.categoryTV);
            price = itemView.findViewById(R.id.price);
            colorTV = itemView.findViewById(R.id.colorTV);
            sizeTV = itemView.findViewById(R.id.sizeTV);
            brandTV = itemView.findViewById(R.id.brandText);
            waterTV = itemView.findViewById(R.id.waterText);
            materialTV = itemView.findViewById(R.id.materialText);
            speciesTV = itemView.findViewById(R.id.speciesText);

        }
    }
}

