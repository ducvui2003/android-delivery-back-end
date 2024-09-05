/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import CategorySchema from "../schema/category.schema";
import CategoryModel from "../model/category.model";
import CategoryDocument from "../document/category.document";

const CategoryCollection = mongoose.model<CategoryDocument>("Category", CategorySchema);

const save = (category: CategoryModel): Promise<CategoryDocument> => {
    return new CategoryCollection(category).save();
}

const findById = (id: string): Promise<CategoryDocument | null> => {
    return CategoryCollection.findById(id).exec();
}

const findAll = (): Promise<CategoryDocument[]> => {
    return CategoryCollection.find({}).exec();
}

export default {save, findById, findAll};
