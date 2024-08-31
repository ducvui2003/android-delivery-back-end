/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:17 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Query, Schema} from "mongoose";
import {DiscountInfoModel, NutritionalModel, ProductModel, UNIT} from "../model/product.model";
import {RequireInfoSchema} from "../util/require.info";

const NutritionalSchema: Schema = new Schema<NutritionalModel>({
    name: {type: String, required: true},
    value: {type: Number, required: true},
    unit: {type: String, enum: UNIT, required: true}
});

const DiscountInfoSchema: Schema = new Schema<DiscountInfoModel>({
    discount: {type: Number},
    expired: {type: Date}
});

const ProductSchema: Schema = new Schema<ProductModel>({
    name: {type: String, required: true},
    quantity: {type: Number, required: true},
    price: {type: Number, required: true},
    description: {type: String, required: true},
    category: {
        type: Schema.Types.ObjectId, ref: "Category",
        required: true
    },
    options: {
        type: [{type: Schema.Types.ObjectId, ref: "Option"}],
        required: true
    },
    nutritional: {
        type: [NutritionalSchema.obj],
        required: true
    },
    discountInfo: {
        type: DiscountInfoSchema.obj,
        required: false
    }
}, {versionKey: false});

ProductSchema.add(RequireInfoSchema.obj)

ProductSchema.pre("findOne", function (next) {
    mapping(this);
    next();
})

ProductSchema.pre("find", function (next) {
    mapping(this);
    next();
})

const mapping = <T extends Query<any, any>>(query: T): void => {
    query.populate("category", {strictPopulate: false})
        .populate("options", {strictPopulate: false})
}

export default ProductSchema;
