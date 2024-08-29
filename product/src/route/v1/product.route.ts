/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:02 pm
 * User: ducvui2003
 **/

import express, {Request, Response} from "express";
import validationMiddleware from "../../middleware/validation.middleware";
import {productSchema} from "../../util/schema/product.schema";
import {productController} from "../../controller/product.controller";

const router = express.Router();
router.route('/')
    .get((req: Request, res: Response) => {
        res.json({message: "Hello, TypeScript with Express!"})
    })
    .post(validationMiddleware(productSchema), productController);

export const ProductRouter = router