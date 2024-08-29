/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 6:52 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";

const formatResponseMiddleware = (req: Request, res: Response, next: NextFunction) => {
    if (res.locals)
        res.json({
            statusCode: res.locals.statusCode || res.statusCode,
            message: res.locals.message || "Success",
            data: res.locals.data
        });
    else
        next();
}
export default formatResponseMiddleware
