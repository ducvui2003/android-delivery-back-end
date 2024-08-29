/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:58 pm
 * User: ducvui2003
 **/
import {Response, Request} from "express";
import AppError from "../util/error/AppError";
import {isDevelopment} from "../config/environment";

const errorHandlerMiddleware = (err: Error, req: Request, res: Response) => {
    if (err instanceof AppError)
        res.status(err.statusCode).json({
            statusCode: err.statusCode,
            error: err.message,
            stackTrace: isDevelopment() ? err.stack : undefined
        });
    else
        res.status(500).json({
            statusCode: 500,
            error: "Internal Server Error",
            stackTrace: isDevelopment() ? err.stack : undefined
        });
};

export default errorHandlerMiddleware