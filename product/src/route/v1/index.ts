/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express, {Request, Response} from "express";
import {ProductRouter} from "./product.route";

const router = express.Router();
router.use("/v1/product", ProductRouter)
export const APIs_V1_PRODUCT = router;