/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 3:12 pm
 * User: ducvui2003
 **/
import {RequireInfo} from "../util/require.info";

interface BaseOptionModel extends Document {
    name: string;
    type: string;
}

interface GroupOptionModel extends RequireInfo, Document {
    name: string;
    options: Omit<OptionModel, "deleted" | "createdAt" | "updateAt">[];
}

interface OptionModel extends RequireInfo, Document {
    name: string;
    price: number;
}

export {BaseOptionModel, GroupOptionModel, OptionModel};
