/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express from "express";
import ValidationMiddleware from "../../middleware/validation.middleware";
import {productCreateSchema, productGetSchema} from "../../util/schema/productCreateSchema";
import productController from "../../controller/product.controller";

const ProductRouter = express.Router();
ProductRouter
    .get("/:id", ValidationMiddleware(productGetSchema, "params"), productController.getProduct)
    .route('/')
    .post(ValidationMiddleware(productCreateSchema, "body"), productController.createProduct);

export default ProductRouter
