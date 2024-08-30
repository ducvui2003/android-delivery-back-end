/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import {ProductModel} from "../model/product.model";
import ProductSchema from "../schema/product.schema";

const ProductCollection = mongoose.model<ProductModel>("Product", ProductSchema);

const save = async (product: ProductModel) => {
    return await ProductCollection.create(product);
}

const findById = (id: string) => {
    return ProductCollection.findById(id);
}

export default {save, findById};
