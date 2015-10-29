package Panthera.Services.Validators;

import Panthera.Models.Product;

public class ProductValidator {

    private final Product product;

    public ProductValidator(Product product) {
        this.product = product;
    }

    public boolean validate() throws Exception {
        if (product.naamProperty().isEmpty().get()) {
            throw new ProductNaamNotValidException("Naam mag niet leeg zijn.");
        } else if (product.typeProperty().isEmpty().get()) {
            throw new ProductTypeNotValidException("Type mag niet leeg zijn.");
        }
        return true;
    }

}
