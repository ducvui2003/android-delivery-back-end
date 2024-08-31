/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 1:24 PM - 31/08/2024
 * User: lam-nguyen
 **/
import {NextFunction, Request, Response} from "express";
import {ResponseLocals} from "../type/response.type";


const ClearEmptyValueMiddleware = (_: Request, res: Response<ResponseLocals<object>, ResponseLocals<object>>, next: NextFunction) => {
    if (res.locals)
        res.locals.data = Object.fromEntries(Object.entries(res.locals.data).filter(([_, v]) => v && v.length !== 0));
    else
        next();
}

export default ClearEmptyValueMiddleware
