/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 1:41 pm
 * User: ducvui2003
 **/
import 'express';
import {ResponseLocals} from "./response.type";

export interface ApiResponse extends Express.Response {
    locals?: ResponseLocals
}