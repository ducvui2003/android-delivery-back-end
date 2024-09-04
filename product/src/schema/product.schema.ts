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
import ProductRepository from "../repository/product.repository";

const NutritionalSchema: Schema = new Schema<NutritionalModel>({
    name: {type: String, required: true},
    value: {type: Number, required: true},
    unit: {type: String, enum: UNIT, required: true}
}, {_id: false});

const DiscountInfoSchema: Schema = new Schema<DiscountInfoModel>({
    discount: {type: Number},
    expired: {type: Date}
}, {_id: false});

const ProductSchema: Schema = new Schema<ProductModel>({
    name: {type: String, required: true},
    image: {type: String},
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

ProductSchema.pre("find", function (next) {
    mapping(this);
    next();
})

ProductSchema.pre("findOne", function (next) {
    mapping(this);
    next();
})

ProductSchema.pre("findOneAndDelete", function (next) {
    mapping(this);
    next();
})

ProductSchema.pre("findOneAndReplace", function (next) {
    mapping(this);
    next();
})

ProductSchema.pre("findOneAndUpdate", function (next) {
    mapping(this);
    next();
})

const mapping = <T extends Query<any, any>>(query: T): void => {
    query.populate("category", {strictPopulate: false})
        .populate("options", {strictPopulate: false})
}

ProductSchema.post("findOneAndDelete", function (docs) {
    if (docs._id.toString())
        ProductRepository.updateTime(docs._id.toString())
})

ProductSchema.post("findOneAndReplace", function (docs) {
    if (docs._id.toString())
        ProductRepository.updateTime(docs._id.toString())
})

ProductSchema.post("findOneAndUpdate", function (docs) {
    if (docs._id.toString())
        ProductRepository.updateTime(docs._id.toString())
})


export default ProductSchema;
