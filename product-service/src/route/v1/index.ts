/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express from "express";
import CategoryRoute from "./category.route";
import ProductRouter from "./product.route";
import ProductOptionRoute from "./productOption.route";

const APIs_V1 = express.Router();
APIs_V1.use("/v1/product", ProductRouter)
APIs_V1.use("/v1/category", CategoryRoute)
APIs_V1.use("/v1/product-option", ProductOptionRoute)

export default APIs_V1
