/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import ProductSchema from "../schema/product.schema";
import {DiscountInfoDocument, ProductDocument} from "../document/product.document";

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

const removeDiscount = async (id: string): Promise<ProductDocument | null> => {
    return await ProductCollection.findByIdAndUpdate(id, {$unset: {discountInfo: 1}}).exec()
}
const setDiscount = async (id: string, discountInfo: DiscountInfoDocument): Promise<ProductDocument | null> => {
    return await ProductCollection.findByIdAndUpdate(id, {$set: {discountInfo: discountInfo}}).exec()
}

const updateTime = (id: string) => {
    ProductCollection.updateOne({_id: id, updateAt: Date.now()}).exec().then();
}


export default {save, findById, findAll, removeDiscount, setDiscount, updateTime};
