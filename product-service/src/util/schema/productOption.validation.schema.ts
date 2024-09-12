/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 12:42 pm
 * User: ducvui2003
 **/
import {z, ZodSchema} from "zod";


const OptionSchema: ZodSchema = z.object({
    name: z.string({message: "Name is required"}),
    price: z.number({message: "Price is required"}),
})

const GroupOptionSchema: ZodSchema = z.object({
    name: z.string({message: "Name is required"}),
    options: z.array(OptionSchema)
})

const CreateProductOption = OptionSchema.or(GroupOptionSchema)

export default {CreateProductOption}
