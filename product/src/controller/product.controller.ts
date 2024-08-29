/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 4:59 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import {ApiResponse} from "../type/response";

export const productController = (req: Request, res: Response, next: NextFunction) => {
    try {
        res.locals = {
            statusCode: 200,
            message: "Success",
            data: {
                name: "Product",
                price: 1000
            }
        }
        next();
    } catch (error) {
        next(error);
    }
}
