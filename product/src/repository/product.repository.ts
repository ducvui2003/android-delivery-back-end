/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import ProductSchema from "../schema/product.schema";
import {ProductDocument} from "../document/product.document";

const ProductCollection = mongoose.model<ProductDocument>("Product", ProductSchema);

const save = async (product: ProductDocument): Promise<ProductDocument> => {
    const data = await ProductCollection.create(product);
    return await findById(data._id as string) as ProductDocument;
}

const findById = async (id: string): Promise<ProductDocument | null> => {
    return await ProductCollection.findById(id)
        .exec();
}

const findAll = (): Promise<ProductDocument[]> => {
    return ProductCollection.find({}).exec();
}

export default {save, findById, findAll};
