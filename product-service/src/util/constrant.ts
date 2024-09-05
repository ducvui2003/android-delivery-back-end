/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 4:11 pm
 * User: ducvui2003
 **/
import {z} from "zod";

export const OBJECT_ID_REGEX = /^[a-fA-F0-9]{24}$/;

export const objectIdValidator = z.string().refine(value => OBJECT_ID_REGEX.test(value), {
    message: "Invalid ObjectId format",
});