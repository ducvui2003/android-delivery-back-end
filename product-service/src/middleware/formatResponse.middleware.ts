/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 6:52 pm
 * User: ducvui2003
 **/
import {NextFunction, Request, Response} from "express";
import {ResponseLocals} from "../type/response.type";

const FormatResponseMiddleware = (_: Request, res: Response<ResponseLocals<object>, ResponseLocals<object>>, next: NextFunction) => {
    // Controller cần trả về res.locals = {statusCode: number, message: string, data: any}
    // Middleware này sẽ format lại response trước khi trả về cho client
    // Nếu không có res.locals thì bỏ qua middleware này
    if (res.locals.data)
        res.json({
            statusCode: res.locals.statusCode || res.statusCode,
            message: res.locals.message || "Success",
            data: res.locals.data
        });
    else
        res.json({
            statusCode: res.locals.statusCode || res.statusCode,
            message: res.locals.message || "Success",
        });
}
export default FormatResponseMiddleware
