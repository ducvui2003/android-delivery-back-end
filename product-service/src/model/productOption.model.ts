/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 3:12 pm
 * User: ducvui2003
 **/

interface OptionModel {
    id: string;
    name: string;
    price: number;
}

interface GroupOptionModel {
    id: string;
    name: string;
    options: OptionModel[];
}

export {GroupOptionModel, OptionModel};
