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

const limit = Number.parseInt(process.env.LIMIT_GET ?? "0");

const ProductCollection = mongoose.model<ProductDocument>("Product", ProductSchema);

const save = async (product: ProductDocument): Promise<ProductDocument> => {
    const data = await ProductCollection.create(product);
    return await findById(data._id as string) as ProductDocument;
}

const findById = async (id: string): Promise<ProductDocument | null> => {
    return await ProductCollection.findById(id)
        .exec();
}

const findAll = (page: number): Promise<ProductDocument[]> => {
    return ProductCollection.find({}).skip((page - 1) * limit).limit(limit).exec();
}

const removeDiscount = async (id: string): Promise<ProductDocument | null> => {
    return await ProductCollection.findByIdAndUpdate(id, {$unset: {discountInfo: 1}}).exec()
}

const setDiscount = async (id: string, discountInfo: DiscountInfoDocument): Promise<ProductDocument | null> => {
    return await ProductCollection.findByIdAndUpdate(id, {$set: {discountInfo: discountInfo}}).exec()
}

const updateTime = (id: string) => {
    ProductCollection.updateOne({_id: id}, {updateAt: Date.now()}).exec().then();
}

const updateUrlImage = async (id: string, url: string): Promise<boolean> => {
    const data = await ProductCollection.updateOne({_id: id}, {$set: {image: url}}).exec().then();
    return data.acknowledged;
}

const findByCategory = async (id: string, page: number): Promise<ProductDocument[]> => {
    return await ProductCollection.find({category: id})
        .skip((page - 1) * limit)
        .limit(limit)
        .exec();
}


export default {
    save,
    findById,
    findAll,
    removeDiscount,
    setDiscount,
    updateTime,
    updateUrlImage,
    findByCategory
};
