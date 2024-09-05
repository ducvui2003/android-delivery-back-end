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
import {DiscountInfoDocument, ProductDocument} from "../document/product.document";
import {ProductModel} from "../model/product.model";
import ApiPagingType from "../type/apiPaging.type";

const getById = async (req: Request, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    try {
        res.locals.data = await ProductService.getById(req.params.id)
        next()
    } catch (e) {
        next(e)
    }
}

const getAll = async (req: Request<{}, {}, {}, {
    page: string
}, {}>, res: Response<any, ResponseLocals<ApiPagingType<ProductModel>>>, next: NextFunction) => {
    const page = req.query.page ? Number.parseInt(req.query.page) : 1
    res.locals.data = await ProductService.getAll(page)
    next()
}

const create = async (req: Request<any, any, ProductDocument>, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    res.locals.data = await ProductService.create(req.body)
    next();
}

const removeDiscount = async (req: Request<{
    id: string
}>, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    try {
        res.locals.data = await ProductService.removeDiscount(req.params.id)
        next();
    } catch (e) {
        next(e)
    }
}

const setDiscount = async (req: Request<{ id: string }, any, DiscountInfoDocument>, res: Response<any, ResponseLocals<ProductModel>>, next: NextFunction) => {
    try {
        res.locals.data = await ProductService.setDiscount(req.params.id, req.body)
        next();
    } catch (e) {
        next(e)
    }
}

const updateUrlImage = async (req: Request<{ id: string }, {}, {
    url: string
}>, _: Response<any, ResponseLocals<boolean>>, next: NextFunction) => {
    try {
        await ProductService.updateUrlImage(req.params.id, req.body.url)
        next();
    } catch (e) {
        next(e)
    }
}

const getByCategory = async (req: Request<{ id: string }, {}, {}, {
    page: string
}, {}>, res: Response<any, ResponseLocals<ApiPagingType<ProductModel>>>, next: NextFunction) => {
    const page = req.query.page ? Number.parseInt(req.query.page) : 1
    res.locals.data = await ProductService.getByCategory(req.params.id, page)
    next()
}


export default {getById, getAll, create, removeDiscount, setDiscount, updateUrlImage, getByCategory}
