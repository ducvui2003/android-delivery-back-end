/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 4:59 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import {ResponseLocals} from "../type/response.type";
import ProductOptionService from "../service/productOption.service";

const getById = async (req: Request<{
    id: string
}>, res: Response<any, ResponseLocals<any | null>>, next: NextFunction) => {
    res.locals.data = await ProductOptionService.getById(req.params.id)
    next()
}

const getAll = async (_: Request, res: Response<any, ResponseLocals<any | null>>, next: NextFunction) => {
    res.locals.data = await ProductOptionService.getAll()
    next()
}

const create = async (req: Request, res: Response<any, ResponseLocals<any | undefined>>, next: NextFunction) => {
    try {
        res.locals.data = await ProductOptionService.create(req.body)
        next()
    } catch (err) {
        next(err)
    }
}

export default {getById, getAll, create}
