/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:17 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Schema} from "mongoose";
import {ProductModel} from "../model/product.model";
import {RequireInfoSchema} from "../util/require.info";

const ProductSchema: Schema = new Schema<ProductModel>({
    name: {type: String, required: true},
    amount: {type: Number, required: true},
    price: {type: Number, required: true},
    discountInfo: {
        discount: {type: Number},
        expired: {type: Date}
    },
    description: {type: String, required: true},
    category: {type: Schema.Types.ObjectId, ref: "Category"},
    options: [{type: Schema.Types.ObjectId, ref: "Option"}],
    nutritional: {
        name: {type: String, required: true},
        value: {type: Number, required: true},
        unit: {type: String, required: true}
    },
}, {versionKey: false});

ProductSchema.add(RequireInfoSchema.obj)

export default ProductSchema;
