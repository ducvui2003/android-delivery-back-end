/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 4:59 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import ProductService from "../service/product.service";
import {ResponseLocals} from "../type/response.type";
import {ProductDocument} from "../document/product.document";
import {ProductModel} from "../model/product.model";

const getById = async (req: Request, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    res.locals.data = await ProductService.getById(req.params.id)
    next()
}

const getAll = async (_: Request, res: Response<any, ResponseLocals<ProductModel[]>>, next: NextFunction) => {
    res.locals.data = await ProductService.getByAll()
    next()
}

const create = async (req: Request<any, any, ProductDocument>, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    res.locals.data = await ProductService.create(req.body)
    next();
}


export default {getById, getAll, create}
