/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:11 PM - 29/08/2024
 * User: lam-nguyen
 **/
import mongoose from "mongoose";
import {GroupOptionModel, OptionModel} from "../model/productOption.model";
import ProductOptionSchema from "../schema/productOption.schema";

const BaseOptionCollection = mongoose.model<OptionModel>("Option", ProductOptionSchema.BaseOptionSchema);
const OptionCollection = BaseOptionCollection.discriminator("Option", ProductOptionSchema.OptionSchema);
const GroupOptionCollection = BaseOptionCollection.discriminator("GroupOption", ProductOptionSchema.GroupOptionSchema);

const save = async (option: OptionModel | GroupOptionModel) => {
    let newData;
    if ("options" in option)
        newData = new OptionCollection(option);
    else newData = new GroupOptionCollection(option);
    // return await OptionCollection.create(option);
    return newData.save();
}

const findById = (id: string) => {
    return BaseOptionCollection.findById(id);
}

const ProductRepository = {save, findById}

export default ProductRepository;
