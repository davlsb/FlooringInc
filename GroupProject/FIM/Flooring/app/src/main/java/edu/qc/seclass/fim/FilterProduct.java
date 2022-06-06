package edu.qc.seclass.fim;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterProduct extends Filter {

    private AdapterProduct adapter;
    private ArrayList<ModelProduct> filterList;

    public FilterProduct(AdapterProduct adapter, ArrayList<ModelProduct> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //validate data for search query
        if(charSequence != null && charSequence.length() > 0){
            //searching as not empty

            //change to uppercase to ignore case
            charSequence = charSequence.toString().toUpperCase();
            //store filtered list
            ArrayList<ModelProduct> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size(); i++){
                if(filterList.get(i).getProductTitle().toUpperCase().contains(charSequence)){
                    // add filtered data to list
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }

        else {
            //not searching
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
    adapter.productList = (ArrayList<ModelProduct>) filterResults.values;
    adapter.notifyDataSetChanged();
    }
}
