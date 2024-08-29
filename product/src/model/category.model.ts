/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 3:15 pm
 * User: ducvui2003
 **/

interface CategoryModel {
    _id: string;
    name: string;

    createdAt: Date;
    updateAt: Date;
    deleted: boolean
}

export { CategoryModel };