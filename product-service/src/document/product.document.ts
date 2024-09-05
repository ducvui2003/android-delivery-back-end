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
import {GroupOptionDocument, OptionDocument} from "./productOption.document";
import {UNIT} from "../model/product.model";

interface ProductDocument extends RequireInfo, Document {
    name: string;
    amount: number;
    price: number;
    discountInfo: DiscountInfoDocument;
    description: string;
    category: CategoryDocument;
    options: (OptionDocument | GroupOptionDocument[]);
    nutritional: NutritionalDocument;
}

interface NutritionalDocument {
    name: string;
    value: number;
    unit: UNIT;
}

type DiscountInfoDocument = {
    discount: number;
    expired: Date
}

export {ProductDocument, NutritionalDocument, DiscountInfoDocument};
