/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:46 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Schema} from "mongoose";
import {RequireInfoSchema} from "../util/require.info";
import {BaseOptionModel, GroupOptionModel, OptionModel} from "../model/productOption.model";

const BaseOptionSchema: Schema = new Schema<BaseOptionModel>({
    name: { type: String, required: true },
    type: { type: String, required: true, enum: ['Option', 'GroupOption'] },
});

const OptionSchema: Schema = new Schema<OptionModel>({
    price: {type: Number, required: true},
}, {versionKey: false});


const GroupOptionSchema: Schema = new Schema<GroupOptionModel>({
    options: [OptionSchema.obj]
}, {versionKey: false});

OptionSchema.add(RequireInfoSchema.obj)
GroupOptionSchema.add(RequireInfoSchema.obj)

export default {BaseOptionSchema, OptionSchema, GroupOptionSchema};
