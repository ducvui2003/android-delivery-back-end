import {z, ZodSchema} from "zod";

/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 12:42 pm
 * User: ducvui2003
 **/
export const productSchema: ZodSchema = z.object({
    name: z.string({message: "Name is required"}),
    basePrice: z.number({message: "Base price is required"}),
    salePrice: z.number(),
    description: z.string()
})
