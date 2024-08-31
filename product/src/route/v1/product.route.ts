/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express from "express";
import ValidationMiddleware from "../../middleware/validation.middleware";
import ProductController from "../../controller/product.controller";
import ProductValidationSchema from "../../util/schema/product.validation.schema";

const ProductRouter = express.Router();
ProductRouter
    .get("/:id", ProductController.getById)
    .get("/", ProductController.getAll)
    .post("/", ValidationMiddleware(ProductValidationSchema.CreateProduct, "body"), ProductController.create);

export default ProductRouter
