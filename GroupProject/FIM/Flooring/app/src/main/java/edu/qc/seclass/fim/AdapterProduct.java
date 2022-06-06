package edu.qc.seclass.fim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.HolderProductSeller> implements Filterable {

    private Context context;
    public ArrayList<ModelProduct> productList, filterList;
    private FilterProduct filter;

    public AdapterProduct(Context context, ArrayList<ModelProduct> productList){
        this.context = context;
        this.productList = productList;
        this.filterList = productList;
    }

    @NonNull
    @Override
    public HolderProductSeller onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_products,parent,false);
        return new HolderProductSeller((view));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductSeller holder, int position) {
        //get data
        ModelProduct modelProduct = productList.get(position);
        String id = modelProduct.getProductId();
        String uid = modelProduct.getUid();
        String title = modelProduct.getProductTitle();
        String timestamp = modelProduct.getTimestamp();
        String type = modelProduct.getProductType();

        //set data
        holder.titleTV.setText(title);
        holder.quantityTV.setText(type);
        //holder.price.setText(price);
        //holder.categoryTV.setText(category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //handle item click
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        if(filter==null){
            filter = new FilterProduct(this, filterList);
        }
        return filter;
    }

    class HolderProductSeller extends RecyclerView.ViewHolder {

        //holds the views of recycler view


        //private ImageView productIcon;
        private TextView titleTV, quantityTV, categoryTV, price;
        public HolderProductSeller(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            quantityTV = itemView.findViewById(R.id.quantityTV);
            categoryTV = itemView.findViewById(R.id.categoryTV);
            //price = itemView.findViewById(R.id.price);




        }
    }
}
