/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:46 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Schema} from "mongoose";
import {RequireInfoSchema} from "../util/require.info";
import CategoryModel from "../model/category.model";

const CategorySchema: Schema = new Schema<CategoryModel>({
    name: {type: String, required: true, unique: true},
}, {versionKey: false});

CategorySchema.add(RequireInfoSchema.obj)

export default CategorySchema;
