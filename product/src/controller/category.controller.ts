/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 4:59 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import CategoryModel from "../model/category.model";
import CategoryService from "../service/category.service";
import {ResponseLocals} from "../type/response.type";

const getById = async (req: Request<{
    id: string
}>, res: Response<any, ResponseLocals<CategoryModel | null>>, next: NextFunction) => {
    res.locals.data = await CategoryService.getById(req.params.id)
    next()
}

const getAll = async (_: Request, res: Response<any, ResponseLocals<CategoryModel[] | null>>, next: NextFunction) => {
    res.locals.data = await CategoryService.getAll()
    next()
}

const create = async (req: Request<any, any, CategoryModel>, res: Response<any, ResponseLocals<CategoryModel | undefined>>, next: NextFunction) => {
    try {
        res.locals.data = await CategoryService.create(req.body)
        next()
    } catch (err) {
        next(err)
    }
}

export default {getById, getAll, create}
