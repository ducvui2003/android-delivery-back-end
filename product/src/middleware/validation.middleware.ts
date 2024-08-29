/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 28/8/24 - 5:53 pm
 * User: ducvui2003
 **/

import {NextFunction, Request, Response} from 'express';
import {ZodError, ZodSchema} from "zod";
import {StatusCodes} from "http-status-codes";


const validationMiddleware = (schema: ZodSchema<ZodSchema>) => {
    return (req: Request, res: Response, next: NextFunction) => {
        try {
            // Validate request body against the provided schema
            schema.parse(req.body);
            next(); // Proceed to the next middleware or route handler
        } catch (error) {
            if (error instanceof ZodError) {
                res.status(StatusCodes.BAD_REQUEST).json({
                    statusCode: StatusCodes.BAD_REQUEST,
                    error: error.errors.length > 1 ? error.errors.map(err => `${err.path}: ${err.message}`) : `${error.errors[0].path}: ${error.errors[0].message}` ,
                });
            }
            //??
        }
    }
}

export default validationMiddleware;