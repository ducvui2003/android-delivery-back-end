/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:58 pm
 * User: ducvui2003
 **/
import {Response, Request, NextFunction} from "express";
import AppError from "../util/error/AppError";
import {isDevelopment} from "../config/environment";
import {ErrorResponse} from "../type/error.type";

const ErrorHandlerMiddleware = (err: Error, _: Request, res: Response<ErrorResponse<any>>, __: NextFunction) => {
    if (err instanceof AppError)
        res.status(400).json({
            statusCode: err.statusCode,
            error: err.message,
            stackTrace: isDevelopment() ? err.stack : undefined
        });
    else
        res.status(500).send({
            statusCode: 500,
            error: "Internal Server Error",
            stackTrace: isDevelopment() ? err.stack : undefined
        });

};

export default ErrorHandlerMiddleware
