/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express from "express";
import ValidationMiddleware from "../../middleware/validation.middleware";
import ProductOptionValidationSchema from "../../util/schema/productOption.validation.schema";
import ProductOptionController from "../../controller/productOption.controller";

const APIs_V1_PRODUCT_OPTION = express.Router();
APIs_V1_PRODUCT_OPTION
    .get("/:id", ProductOptionController.getById)
    .get("/", ProductOptionController.getAll)
    .post("/", ValidationMiddleware(ProductOptionValidationSchema.CreateProductOption, "body"), ProductOptionController.create);

export default APIs_V1_PRODUCT_OPTION;
