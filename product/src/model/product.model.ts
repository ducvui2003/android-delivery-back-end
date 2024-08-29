/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 2:59 pm
 * User: ducvui2003
 **/
import {OptionModel} from "./option.model";
import {ObjectId} from "mongodb";
import {CategoryModel} from "./category.model";

interface ProductModel {
    _id: ObjectId;
    name: string;
    amount: number;
    basePrice: number;
    salePrice: number;
    description: string;
    category: CategoryModel;
    options: OptionModel[];
    nutritional: NutritionalModel;

    createdAt: Date;
    updateAt: Date;
    deleted: boolean
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

export {ProductModel, NutritionalModel, UNIT};
