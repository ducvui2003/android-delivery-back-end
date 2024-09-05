/**
 * Author: Le Anh Duc
 * Email: ducvui2003@gmail.com
 * Phone number: +84 965809127
 * Created at: 29/8/24 - 3:12 pm
 * User: ducvui2003
 **/
import {RequireInfo} from "../util/require.info";

interface OptionDocument extends RequireInfo, Document {
    name: string;
    price: number;
}

interface GroupOptionDocument extends RequireInfo, Document {
    name: string;
    options: Omit<GroupOptionDocument, "deleted" | "createdAt" | "updateAt">[];
}

export {OptionDocument, GroupOptionDocument};
