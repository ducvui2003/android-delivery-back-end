/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 4:59 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import productService from "../service/product.service";

const getProduct = async (req: Request, res: Response, next: NextFunction) => {
    const product = await productService.getProduct(req.params.id)
    next()
}

const createProduct = async (req: Request, res: Response, next: NextFunction) => {
    // const response: ApiResponse = {
    //     status: 200,
    //     message: "Create product successfully!"
    // }
    // res.json(response)
}

const productController = {getProduct, createProduct}

export default productController
