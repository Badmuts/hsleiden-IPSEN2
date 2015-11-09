package Panthera.Services.Validators;

import Panthera.Models.Product;

/**
 * Product validator used to check if all the necessary fields of a product are present.
 *
 * @author Daan Rosbergen
 */
public class ProductValidator {

    private final Product product;

    public ProductValidator(Product product) {
        this.product = product;
    }

    /**
     * Validates the required fields of a product and throws an exception when these fields are
     * incorrect.
     *
     * @return
     * @throws ProductNaamNotValidException
     * @throws ProductTypeNotValidException
     */
    public boolean validate() throws ProductNaamNotValidException, ProductTypeNotValidException {
        if (product.naamProperty().isEmpty().get()) {
            throw new ProductNaamNotValidException("Naam mag niet leeg zijn.");
        } else if (product.typeProperty().isEmpty().get()) {
            throw new ProductTypeNotValidException("Type mag niet leeg zijn.");
        }
        return true;
    }

}
