/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 2:59 pm
 * User: ducvui2003
 **/
import {Document} from "mongoose";
import {RequireInfo} from "../util/require.info";
import CategoryDocument from "./category.document";
import {OptionModel} from "../model/productOption.model";
import {GroupOptionDocument, OptionDocument} from "./productOption.document";

interface ProductDocument extends RequireInfo, Document {
    name: string;
    amount: number;
    price: number;
    discountInfo: DiscountInfo;
    description: string;
    category: CategoryDocument;
    options: (OptionDocument | GroupOptionDocument[]);
    nutritional: NutritionalModel;
}

interface NutritionalModel {
    name: string;
    value: number;
    unit: UNIT;
}

enum UNIT {
    GRAM = 'g',
    MILLILITER = 'ml',
    PIECE = 'piece'
}

type DiscountInfo = {
    discount: number;
    expired: Date
}

export {ProductDocument, NutritionalModel, UNIT};
