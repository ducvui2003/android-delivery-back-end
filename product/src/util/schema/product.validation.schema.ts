/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 12:42 pm
 * User: ducvui2003
 **/

import {z, ZodSchema} from "zod";
import {UNIT} from "../../model/product.model";

const DiscountCreateValidation: ZodSchema =  z.nullable(z.object({
    discount: z.number({message: "Discount is required"}),
    expired: z.string({message: "Expired date is required"}).date()
}))

const ProductOptionCreateValidation: ZodSchema = z.string({message: "Product option Id is required"}).array()

const NutritionalCreateValidation: ZodSchema = z.object({
    name: z.string({message: "Name is required"}),
    value: z.number({message: "Value is required"}),
    unit: z.nativeEnum(UNIT, {
        message: "Unit is required",
    })
}).array()

const CreateProduct: ZodSchema = z.object({
    name: z.string({message: "Name is required"}),
    price: z.number({message: "Price is required"}),
    quantity: z.number({message: "Amount is required"}),
    description: z.string({message: "Description is required"}),
    category: z.string({message: "Category ID is required"}),
    discountInfo: DiscountCreateValidation,
    options: ProductOptionCreateValidation,
    nutritional: NutritionalCreateValidation
})

export default {CreateProduct}
