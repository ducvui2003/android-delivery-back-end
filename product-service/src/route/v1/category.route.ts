/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express from "express";
import CategoryController from "../../controller/category.controller";
import validationMiddleware from "../../middleware/validation.middleware";
import CategoryValidationSchema from "../../util/schema/category.validation.schema";

const CATEGORY_ROUTE = express.Router();
CATEGORY_ROUTE
    .get("/:id", CategoryController.getById)
    .get("/", CategoryController.getAll)
    .post("/", validationMiddleware(CategoryValidationSchema.CreateCategory, "body"), CategoryController.create);

export default CATEGORY_ROUTE;
