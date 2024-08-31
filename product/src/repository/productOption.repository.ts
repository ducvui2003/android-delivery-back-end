/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import ProductOptionSchema from "../schema/productOption.schema";
import {GroupOptionModel, OptionModel} from "../model/productOption.model";

const ProductOptionCollection = mongoose.model<OptionModel | GroupOptionModel>("Option", ProductOptionSchema.ProductOptionSchema);

const save = async (option: OptionModel | GroupOptionModel): Promise<OptionModel | GroupOptionModel> => {
    let newData = new ProductOptionCollection(option);
    return await newData.save();
}

const findById = async (id: string) => {
    return ProductOptionCollection.findById(id).exec();
}

const findAll = () => {
    return ProductOptionCollection.find({}).exec();
}


export default {save, findById, findAll};
