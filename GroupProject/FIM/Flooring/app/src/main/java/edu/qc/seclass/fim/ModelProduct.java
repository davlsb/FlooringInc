package edu.qc.seclass.fim;

import android.widget.ImageView;

public class ModelProduct {
    private String productId, productTitle, productType, timestamp, storeID, category, color, price, size, uid, material, species, waterStatus, brand;
    private ImageView imageView;

    public ModelProduct() {
    }

    public ModelProduct(String productId, String productTitle, String productType, String timestamp,
                        String storeID, String category, String color, String price, String size, String uid,
                        String material, String species, String waterStatus, String brand, ImageView imageView) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productType = productType;
        this.timestamp = timestamp;
        this.storeID = storeID;
        this.category = category;
        this.color = color;
        this.price = price;
        this.size = size;
        this.uid = uid;
        this.material = material;
        this.species = species;
        this.waterStatus = waterStatus;
        this.brand = brand;
        this.imageView = imageView;
    }

    /*
    public ModelProduct(ImageView imageView, String productId, String productTitle, String productType, String timestamp, String storeID, String category, String color, String price, String size, String uid, String productMaterial) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productType = productType;
        this.timestamp = timestamp;
        this.storeID = storeID;
        this.category = category;
        this.color = color;
        this.price = price;
        this.size = size;
        this.uid = uid;
        this.imageView = imageView;
        this.material = productMaterial;
        this.species = productMaterial;
    }
     */

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWaterStatus() {
        return waterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        this.waterStatus = waterStatus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productID) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
