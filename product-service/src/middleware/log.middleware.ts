/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 11:04 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {NextFunction, Request, Response} from "express";
import {ResponseLocals} from "../type/response.type";


const LogMiddleware = (req: Request, _: Response<ResponseLocals<object>>, next: NextFunction) => {
    const method = req.method;
    const url = req.url;
    const timestamp = new Date().toISOString();
    console.log("==========================================================================")
    console.log(`[${timestamp}] ${method} ${url}`);
    console.log(`Data request:`);
    console.log(`              - Param data: `, req.params);
    console.log(`              - Body data: `, req.body);
    console.log(`              - Query data: `, req.query);
    next();
}

export default LogMiddleware
