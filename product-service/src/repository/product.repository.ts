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
import ApiPagingType from "../type/apiPaging.type";

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

const findAll = async (page: number): Promise<ApiPagingType<ProductDocument>> => {
    const promiseContent = ProductCollection.find({}).skip((page - 1) * limit).limit(limit).exec();
    const promiseTotalDocument = ProductCollection.find({}).countDocuments().exec();
    return Promise.all([promiseContent, promiseTotalDocument]).then(([content, totalDocument]) => {
        return {
            content: content,
            current: page,
            size: content.length,
            totalPage: Math.ceil(totalDocument / limit)
        } as ApiPagingType<ProductDocument>
    });
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

const findByCategory = async (id: string, page: number): Promise<ApiPagingType<ProductDocument>> => {
    const promiseContent = ProductCollection.find({category: id})
        .skip((page - 1) * limit)
        .limit(limit)
        .exec();
    const promiseTotalDocument = ProductCollection.find({category: id}).countDocuments().exec();
    return Promise.all([promiseContent, promiseTotalDocument]).then(([content, totalDocument]) => {
        return {
            content: content,
            current: page,
            size: content.length,
            totalPage: Math.ceil(totalDocument / limit)
        } as ApiPagingType<ProductDocument>
    });
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
