/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 9:46 AM - 30/08/2024
 * User: lam-nguyen
 **/
import {Schema} from "mongoose";
import {RequireInfoSchema} from "../util/require.info";
import {GroupOptionModel, OptionModel} from "../model/productOption.model";

const ProductOptionSchema: Schema = new Schema<GroupOptionModel | OptionModel>({
    name: {type: String, required: true, unique: true},
    price: {type: Number, required: false},
    options: {
        type: [{
            name: {type: String, required: true},
            price: {type: Number, required: true},
        }], required: false
    }
}, {
    versionKey: false,
})

ProductOptionSchema.add(RequireInfoSchema.obj)

ProductOptionSchema.pre("save", function (next) {
    if (this.$isEmpty("price") && this.$isEmpty("options"))
        console.log("Price or options are required")
    cleanDataEmpty(this)
    next()
})

const cleanDataEmpty = (data: any) => {
    if (data.$isEmpty("price"))
        data.updateOne({$unset: {"price": 1}}).exec()
    if (data.$isEmpty("options"))
        data.updateOne({$unset: {"options": 1}}).exec()
}

export default {ProductOptionSchema};
