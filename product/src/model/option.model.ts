/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 3:12 pm
 * User: ducvui2003
 **/
import {ObjectId} from "mongodb";

interface OptionModel {
    _id: ObjectId;
    name: string;
    optionItems: OptionItemModel[];
}

interface OptionItemModel {
    _id: string;
    name: string;
    price: number;

    createdAt: Date;
    updateAt: Date;
    deleted: boolean
}

export { OptionModel, OptionItemModel };