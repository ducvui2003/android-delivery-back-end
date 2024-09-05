/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 2:59 pm
 * User: ducvui2003
 **/
import {GroupOptionModel, OptionModel} from "./productOption.model";
import CategoryModel from "./category.model";

interface ProductModel {
    id: string;
    image: string;
    name: string;
    quantity: number;
    price: number;
    discountInfo?: DiscountInfoModel;
    description: string;
    category?: CategoryModel;
    options?: (OptionModel | GroupOptionModel)[];
    nutritional?: NutritionalModel[];
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

type DiscountInfoModel = {
    discount: number;
    expired?: Date
}

export {ProductModel, NutritionalModel, UNIT, DiscountInfoModel};
